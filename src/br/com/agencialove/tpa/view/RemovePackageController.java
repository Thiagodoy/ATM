package br.com.agencialove.tpa.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.agencialove.tpa.hardware.IHardwareService;
import br.com.agencialove.tpa.hardware.arduino.Dispenser;
import br.com.agencialove.tpa.hardware.arduino.DispenserState;
import br.com.agencialove.tpa.model.Pack;
import br.com.agencialove.tpa.workflow.Session;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;

public class RemovePackageController implements IController {


	private Scene nextScene;

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {

	}

	@Override
	public void clear() {
		this.nextScene = null;
	}

	public void inject(final Pack pack) {

		final IHardwareService hardwareService = Session.getHardwareService();

		try {
			final DispenserState dState = hardwareService.dispensePack(Dispenser.valueOf(pack.getDispenser()));

			switch (dState) {
			case IDLE:
				break;
			case READY_TO_REMOVE:
				break;
			case WORKING:
				break;
			case EMPTY:
				hardwareService.alarmError();
				break;
			case LOW_LEVEL:
				hardwareService.alarmWarning();
				break;
			case ERROR_BLOCKED:
				Logger.getAnonymousLogger().log(Level.SEVERE, "O dispenser está bloqueado: " + pack.getCodigo());
				Session.error();
				return;
			case COMMUNICATION_ERROR:
				Logger.getAnonymousLogger().log(Level.SEVERE, "Um erro de comunicação com o hardware ocorreu ao tentar injetar o pacote: " + pack.getCodigo());
				Session.error();
				return;
			default:
				Logger.getAnonymousLogger().log(Level.SEVERE, "O sistema atingiu um estado inconsistente após tentar injetar o pacote:" +  pack.getCodigo());
				Session.error();
				return;
			}

		} catch (final IOException e) {
			Logger.getAnonymousLogger().log(Level.SEVERE, "Erro ao injetar o envelope", e);
			Session.error();
		}

		final TimerTask task = new TimerTask() {
			@Override
			public void run() {
				Platform.runLater(()->{
					if(RemovePackageController.this.nextScene == Windows.FINISH.getScene()) {
						final FinishController controller = (FinishController) RemovePackageController.this.nextScene.getUserData();
						controller.finish();
						Session.setScene(RemovePackageController.this.nextScene);
					}else if (RemovePackageController.this.nextScene == Windows.MEASURES.getScene()) {
						final MeasuresController controller = (MeasuresController) RemovePackageController.this.nextScene.getUserData();
						controller.clear();
						Session.setScene(RemovePackageController.this.nextScene);
					}else {
						throw new IllegalStateException("Erro no fluxo do sistema ao navegar de " + Windows.REMOVE_PACKAGE.getLabel());
					}
				});
			}
		};

		final Timer timer = new Timer();
		timer.schedule(task, 8000);
	}
	
	@FXML
	@Override
	public void cancel() {
		Session.reset();		
	}

}
