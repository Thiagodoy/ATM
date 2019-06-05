package br.com.agencialove.tpa.hardware;

import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import br.com.agencialove.tpa.model.PaymentData;
import br.com.agencialove.tpa.model.PaymentResult;
import javafx.application.Platform;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;

public class TEFServiceMock implements ITEFService {

	public TEFServiceMock(final Stage pStage) {
	}

	@Override
	public void charge(final String value, final String code, final String description, final TEFCallback callback) {

		final Timer timer = new Timer();

		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				Platform.runLater(() -> {
					final ButtonType btnSuccess = new ButtonType("Sucesso", ButtonData.YES);
					final ButtonType btnFail = new ButtonType("Falha", ButtonData.NO);
					final Dialog<ButtonType> dialog = new Dialog<ButtonType>();
					dialog.setContentText("O pagamento foi realizado com sucesso?");
					dialog.getDialogPane().getButtonTypes().add(btnSuccess);
					dialog.getDialogPane().getButtonTypes().add(btnFail);

					final Optional<ButtonType> result = dialog.showAndWait();
					if(result.isPresent() && result.get() == btnSuccess) {

						final PaymentData ret1 = new PaymentData();
						ret1.setId("1234567");
						ret1.setValue(value);
						ret1.setCode(code);
						ret1.setCurrency("0");
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
						callback.charged(PaymentResult.FAIL, ret2, "Não foi possível cobrar");
					}
				});
			}}, 2000);
	}
}
