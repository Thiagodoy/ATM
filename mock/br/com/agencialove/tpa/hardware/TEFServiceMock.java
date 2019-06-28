package br.com.agencialove.tpa.hardware;

import br.com.agencialove.tpa.model.PaymentData;
import br.com.agencialove.tpa.model.PaymentResult;
import javafx.concurrent.Task;
import javafx.stage.Stage;

public class TEFServiceMock implements ITEFService {

	
	
	public static boolean payment = true;
	public static long time = 5000;
	
	public TEFServiceMock(final Stage pStage) {
		
		
	}
	
	@Override
	public void charge(final String value, final String code, final String description, final TEFCallback callback) {

		
		
		new Thread(new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				// TODO Auto-generated method stub
				
				Thread.sleep(time);
				
				if(payment) {


					final PaymentData ret1 = new PaymentData();
					ret1.setId("1234567");
					ret1.setValue(value);
					ret1.setCode(code);
					ret1.setCurrency("0");
					ret1.setBandeira("0");
					ret1.setCnpjCpf("6468787654688");
					ret1.setCurrency("10.00");
					ret1.setNsu("wqeqwe");
					ret1.setValue("10.00");
					callback.charged(PaymentResult.SUCCESS, ret1,
							"                 AMEX\n" +
									"\n" +
									"         COMPROVANTE DE OPERACAO\n" +
									"\n" +
									"  AMEX - 376400000000016 - 10/02\n" +
									"\n" +
									"  010101010101010\n" +
									"\n" +
									"  PDV = SW000001          NSU = 6  ONL-D\n" +
									"  16/06/1998  10:00:44  AUTORIZ.= 160006\n" +
									"  VENDA CREDITO A VISTA\n" +
									"\n" +
									"         VALOR VENDA:           260,70\n" +
									"         VALOR FINAL:           260,70\n" +
									"\n" +
									"\n" +
									"  RECONHECO E PAGAREI IMPORTANCIA ACIMA\n" +
									"\n" +
									"  ______________________________________\n" +
									"\n" +
									"                     \n" +
									"\n" +
									"\n" +
									"          CONFIRA A ASSINATURA\n" +
									"\n" +
									"             000000000000000\n" +
									"\n" +
									"                                 (SiTef)\n" +
							"    TRANSACAO OK");
				}else {
					final PaymentData ret2 = new PaymentData();
					ret2.setId("1234567");
					ret2.setValue(value);
					ret2.setCode(code);
					ret2.setCurrency("0");
					ret2.setBandeira("0");
					ret2.setCnpjCpf("6468787654688");
					ret2.setCurrency("10.00");
					ret2.setNsu("wqeqwe");
					ret2.setValue("10.00");
					callback.charged(PaymentResult.FAIL, ret2, "Não foi possível cobrar");
				}
				return null;
			}
		}).start();
		
	

	}
}
