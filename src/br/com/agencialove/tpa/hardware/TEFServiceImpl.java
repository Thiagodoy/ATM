package br.com.agencialove.tpa.hardware;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TimerTask;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.agencialove.tpa.model.PaymentData;
import br.com.agencialove.tpa.model.PaymentResult;
import javafx.concurrent.Task;

public class TEFServiceImpl implements ITEFService {
	private static String TEF_REQ_PATH = "/home/mendes/tmp/tef/req";
	private static String TEF_RESP_PATH = "/home/mendes/tmp/tef/resp";

	private static String CHARGE_FILE = "IntPos.001";
	private static String CONFRIMATION_FILE = "IntPos.Sts";
	private static String RESPONSE_FILE = "IntPos.001";

	private static File counterFile = new File("/home/mendes/tmp/tefCounter");

	private static String CHARGE_CONTENT =
			"000-000 = CRT\r\n" +
					"001-000 = [counter]\r\n" +
					"002-000 = [code]\r\n" +
					"003-000 = [value]\r\n" +
					"004-000 = 0\r\n"+
					"565-005 = [info]"+
					"999-999 = 0";


	@Override
	public void charge(final String value, final String code, final String description, final TEFCallback callback) {
		try {
			final String counter = this.getNextCounter();
			final String fileName = TEFServiceImpl.TEF_REQ_PATH + "/" + TEFServiceImpl.CHARGE_FILE;

			//creates and write a temporary file
			final File chargeFile = new File( fileName + "~" );
			chargeFile.createNewFile();
			final FileOutputStream fos = new FileOutputStream(chargeFile);

			String fileContent = TEFServiceImpl.CHARGE_CONTENT.replace("[counter]", counter);
			fileContent = fileContent.replace("[code]", code);
			fileContent = fileContent.replace("[value]", value);
			fileContent = fileContent.replace("[info]", description);


			fos.write(fileContent.getBytes());
			fos.flush();
			fos.close();

			//rename file
			final File renamed = new File(fileName);
			chargeFile.renameTo(renamed);

			//starts a new timer till 1 minute (60.000 milliseconds) with intervals of 100 milliseconds
			final Thread t = new Thread(new TEFTimer(value, code, callback, System.currentTimeMillis() + 60000, 500L));
			t.start();

		} catch (final IOException e) {
			Logger.getAnonymousLogger().log(Level.SEVERE, "Erro ao enviar solicitacao de sobrança para o TEF", e);
			callback.error(e);;
		}

	}

	private String getNextCounter() throws IOException {
		int ret = 0;
		if(!TEFServiceImpl.counterFile.exists()) {
			TEFServiceImpl.counterFile.createNewFile();
		}else {
			final FileInputStream fis = new FileInputStream(TEFServiceImpl.counterFile);
			final byte[] buff = new byte[1024];
			final int readed = fis.read(buff);
			ret = Integer.parseInt(new String(buff,0,readed));
			fis.close();
		}

		ret++;

		final FileOutputStream fos = new FileOutputStream(TEFServiceImpl.counterFile);
		fos.write(Integer.toString(ret).getBytes());
		fos.flush();
		fos.close();

		return String.valueOf(ret);
	}

	private class TEFTimer extends Task<Void>{

		private boolean confirmed = false;

		private final TEFCallback callback;
		private final long timeout;
		private final String code;

		private final String value;

		public TEFTimer(final String pValue, final String pCode, final TEFCallback pCallback, final long pTimeout, final long pInterval) {
			this.code = pCode;
			this.value = pValue;
			this.callback = pCallback;
			this.timeout = pTimeout;
		}

		@Override
		public  Void call() {

			boolean finished = false;
			while(!finished) {

				final String confirmationFileName = TEFServiceImpl.TEF_RESP_PATH + "/" + TEFServiceImpl.CONFRIMATION_FILE;
				final File confirmationFile = new File(confirmationFileName);

				if(!this.confirmed && confirmationFile.exists()) {
					this.confirmed = true;
					confirmationFile.delete();
				}

				if(this.confirmed) {
					final String responseFileName = TEFServiceImpl.TEF_RESP_PATH + "/" + TEFServiceImpl.RESPONSE_FILE;
					final File responseFile = new File(responseFileName);
					if(responseFile.exists()) {
						try {
							final FileInputStream fis = new FileInputStream(responseFile);

							final Map<String,String> fields = this.parse(fis);
							responseFile.delete();

							final String header = fields.get("000-000");
							final String lCode = fields.get("002-000");
							final String lValue = fields.get("003-000");
							final String status = fields.get("009-000");

							if(header == null || "".equals(header.trim()) || !"CRT".equals(header)) {
								System.out.println("Expected: <CRT> - Got: <" + header + "> for payment" + lCode);
								Logger.getAnonymousLogger().log(Level.SEVERE, "O TEF retornou um campo 000-000 (header) inválido!");
								finished = true;
								this.callback.error(new IllegalStateException("O TEF retornou um campo 000-000 (header) inválido!"));
							}

							if(lCode == null || "".equals(lCode.trim()) || !this.code.equals(lCode)) {
								Logger.getAnonymousLogger().log(Level.SEVERE, "O TEF retornou um campo 002-000 (código) inválido!");
								this.callback.error(new IllegalStateException("O TEF retornou um campo 002-000 (código) inválido!"));
								finished = true;;
							}

							if(lValue == null || "".equals(lValue.trim()) || !this.value.equals(lValue)) {
								Logger.getAnonymousLogger().log(Level.SEVERE, "O TEF retornou um campo 003-000 (valor) inválido!");
								finished = true;;
								this.callback.error(new IllegalStateException("O TEF retornou um campo 003-000 (valor) inválido!"));
							}

							if(status == null || "".equals(status.trim())) {
								Logger.getAnonymousLogger().log(Level.SEVERE, "O TEF retornou um campo 009-000 (status) inválido!");
								finished = true;;
								this.callback.error(new IllegalStateException("O TEF retornou um campo 009-000 (status) inválido!"));
							}else {
								PaymentResult result;
								PaymentData data = null;
								String toPrint = null;
								if("0".equals(status)) {
									result = PaymentResult.SUCCESS;
									data = this.parsePaymentData(fields);
									toPrint = this.parseToPrint(fields);
								}else {
									result = PaymentResult.FAIL;
								}

								finished = true;
								this.callback.charged(result, data, toPrint);
							}

						} catch (final IOException e) {
							Logger.getAnonymousLogger().log(Level.SEVERE, "Erro ao ler arquivo de retorno do TEF.",e);
							this.callback.error(e);
						}
					}
				}

				//Timeout
				if(System.currentTimeMillis() > this.timeout) {
					this.callback.timeout();
					finished = true;;
				}
			}
			
			return null;

		}


		private Map<String, String> parse(final FileInputStream fis) throws IOException {
			final Map<String,String> ret = new TreeMap<>();

			final BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String line;
			while((line = br.readLine()) != null) {
				final String[] fields = line.split(" = ");
				if(fields.length >= 2)
					ret.put(fields[0], fields[1]);
				else
					ret.put(fields[0], "");
			}

			br.close();
			return ret;
		}

		private String parseToPrint(final Map<String, String> fields) {
			final StringBuilder ret = new StringBuilder();

			for(final Map.Entry<String, String> entry : fields.entrySet())
				if(entry.getKey().startsWith("029"))
					ret.append(entry.getValue()).append("\r\n");

			return ret.toString();
		}

		private PaymentData parsePaymentData(final Map<String, String> fields) {
			final PaymentData ret = new PaymentData();
			ret.setId(fields.get("001-000"));
			ret.setCode(fields.get("002-000"));
			ret.setValue(fields.get("003-000"));
			ret.setCurrency("0".equals(fields.get("004-000"))?"REAL":"DOLAR");			
			ret.setBandeira(fields.get("010-003"));
			ret.setCnpjCpf(fields.get("007-000"));
			ret.setNsu(fields.get("012-000"));
			ret.setNumeroCartao(fields.get("010-004"));
			ret.setHoraTransacao(fields.get("023-000"));
			ret.setDataTransacao(fields.get("022-000"));
			ret.setFormaPagamento(fields.get("210-023"));

			return ret;
		}

		
	}
}
