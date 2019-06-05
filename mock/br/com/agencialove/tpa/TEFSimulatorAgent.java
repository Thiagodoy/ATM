package br.com.agencialove.tpa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.TreeMap;

public class TEFSimulatorAgent {

	private static String TEF_REQ_PATH = "/home/mendes/tmp/tef/req";
	private static String TEF_RESP_PATH = "/home/mendes/tmp/tef/resp";

	private static String CHARGE_FILE = "IntPos.001";
	private static String CONFRIMATION_TEMP_FILE = "IntPos.Sts~";
	private static String RESPONSE_TEMP_FILE = "IntPos.001~";
	private static String CONFRIMATION_FILE = "IntPos.Sts";
	private static String RESPONSE_FILE = "IntPos.001";
	public static void main(final String[] args) throws FileNotFoundException, IOException, InterruptedException {
		new File(TEFSimulatorAgent.TEF_REQ_PATH + "/" + TEFSimulatorAgent.CHARGE_FILE).delete();
		new File(TEFSimulatorAgent.TEF_RESP_PATH + "/" + TEFSimulatorAgent.CONFRIMATION_FILE).delete();
		new File(TEFSimulatorAgent.TEF_RESP_PATH + "/" + TEFSimulatorAgent.RESPONSE_FILE).delete();

		final File chargeFile = new File(TEFSimulatorAgent.TEF_REQ_PATH + "/" + TEFSimulatorAgent.CHARGE_FILE);
		while(true) {
			if(chargeFile.exists()) {
				final Map<String,String> fields = TEFSimulatorAgent.parse(new FileInputStream(chargeFile));

				chargeFile.delete();

				final File confirmatinoTempFile = new File(TEFSimulatorAgent.TEF_RESP_PATH + "/" + TEFSimulatorAgent.CONFRIMATION_TEMP_FILE);
				confirmatinoTempFile.createNewFile();
				final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(confirmatinoTempFile)));
				bw.write("000-000 = CRT\r\n");
				bw.write("001-000 = " + fields.get("001-000") + "\r\n");
				bw.write("999-999 = 0");
				bw.flush();
				bw.close();

				Thread.sleep(2000);
				confirmatinoTempFile.renameTo(new File(TEFSimulatorAgent.TEF_RESP_PATH + "/" + TEFSimulatorAgent.CONFRIMATION_FILE));


				final File responseTempFile = new File(TEFSimulatorAgent.TEF_RESP_PATH + "/" + TEFSimulatorAgent.RESPONSE_TEMP_FILE);
				responseTempFile.createNewFile();
				final FileOutputStream fos = new FileOutputStream(responseTempFile);


				String out;
				System.out.println("Paid: " + fields.get("001-000"));
				out = TEFSimulatorAgent.sucess(fields.get("001-000"), fields.get("002-000"), fields.get("003-000"), fields.get("004-000"));
				fos.write(out.getBytes());
				fos.flush();
				fos.close();

				Thread.sleep(3000);
				responseTempFile.renameTo(new File(TEFSimulatorAgent.TEF_RESP_PATH + "/" + TEFSimulatorAgent.RESPONSE_FILE));
			}

			Thread.sleep(500);
		}

	}

	private static String sucess(final String id, final String code, final String value, final String currency) {
		return TEFSimulatorAgent.sucessTemplate.replace("[id]", id).replace("[code]", code).replace("[value]", value).replace("[currency]", currency);
	}

	//	private static String fail(final String id, final String code, final String value, final String currency) {
	//		return TEFSimulatorAgent.failTemplate.replace("[id]", id).replace("[code]", code).replace("[value]", value).replace("[currency]", currency);
	//	}

	private static Map<String, String> parse(final FileInputStream fis) throws IOException {
		final Map<String,String> ret = new TreeMap<>();

		final BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		String line;
		while((line = br.readLine()) != null) {
			final String[] fields = line.split(" = ");
			ret.put(fields[0], fields[1]);
		}

		return ret;
	}

	//	private static final String failTemplate =
	//			"000-000 = CRT"
	//					+"\r\n001-000 = [id]"
	//					+"\r\n002-000 = [code]"
	//					+"\r\n003-000 = [value]"
	//					+"\r\n004-000 = [currency]"
	//					+"\r\n009-000 = 1"
	//					+"\r\n010-000 = AMERICAN EXPRESS"
	//					+"\r\n010-001 = 06"
	//					+"\r\n011-000 = 48"
	//					+"\r\n012-000 = 000000006"
	//					+"\r\n013-000 = 160006"
	//					+"\r\n015-000 = 1606100044"
	//					+"\r\n018-000 = 01"
	//					+"\r\n022-000 = 16061998"
	//					+"\r\n023-000 = 100044"
	//					+"\r\n027-000 = 061616000606";

	private static final String sucessTemplate =
			"000-000 = CRT"
					+"\r\n001-000 = [id]"
					+"\r\n002-000 = [code]"
					+"\r\n003-000 = [value]"
					+"\r\n004-000 = [currency]"
					+"\r\n009-000 = 0"
					+"\r\n010-000 = AMERICAN EXPRESS"
					+"\r\n010-001 = 06"
					+"\r\n011-000 = 48"
					+"\r\n012-000 = 000000006"
					+"\r\n013-000 = 160006"
					+"\r\n015-000 = 1606100044"
					+"\r\n018-000 = 01"
					+"\r\n022-000 = 16061998"
					+"\r\n023-000 = 100044"
					+"\r\n027-000 = 061616000606"
					+"\r\n028-000 = 29"
					+"\r\n029-001 = "
					+"\r\n029-002 =                  AMEX"
					+"\r\n029-003 = "
					+"\r\n029-004 =          COMPROVANTE DE OPERACAO"
					+"\r\n029-005 = "
					+"\r\n029-006 =   AMEX - 376400000000016 - 10/02"
					+"\r\n029-007 = "
					+"\r\n029-008 =   010101010101010"
					+"\r\n029-009 = "
					+"\r\n029-010 =   PDV = SW000001          NSU = 6  ONL-D"
					+"\r\n029-011 =   16/06/1998  10:00:44  AUTORIZ.= 160006"
					+"\r\n029-012 =   VENDA CREDITO A VISTA"
					+"\r\n029-013 = "
					+"\r\n029-014 =          VALOR VENDA:           260,70"
					+"\r\n029-015 =          VALOR FINAL:           260,70"
					+"\r\n029-016 = "
					+"\r\n029-017 = "
					+"\r\n029-018 =   RECONHECO E PAGAREI IMPORTANCIA ACIMA"
					+"\r\n029-019 = "
					+"\r\n029-020 =   ______________________________________"
					+"\r\n029-021 = "
					+"\r\n029-022 =  "
					+"\r\n029-023 = "
					+"\r\n029-024 = "
					+"\r\n029-025 =           CONFIRA A ASSINATURA"
					+"\r\n029-026 = "
					+"\r\n029-027 =              000000000000000"
					+"\r\n029-028 = "
					+"\r\n029-029 =                                  (SiTef)"
					+"\r\n030-000 =     TRANSACAO OK"
					+"\r\n999-999 = 0";
}
