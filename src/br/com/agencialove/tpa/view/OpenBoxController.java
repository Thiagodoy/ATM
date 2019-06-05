package br.com.agencialove.tpa.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.agencialove.tpa.hardware.IHardwareService;
import br.com.agencialove.tpa.workflow.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;

public class OpenBoxController implements IController {

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {

	}

	@FXML
	private void btnNextAction(final ActionEvent e) {
		final IHardwareService hs = Session.getHardwareService();
		try {

			hs.closeBox();

		} catch (final IOException ex) {
			Logger.getGlobal().log(Level.SEVERE, "Erro ao abrir a gaveta coletora", ex); //$NON-NLS-1$
			Session.error();
		}

		final Scene scene = Windows.TEST_SURVEY.getScene();
		Session.setScene(scene);
	}



	@Override
	public void clear() {
	}

	public void startCollectProcess() {
		final IHardwareService hs = Session.getHardwareService();
		try {

			hs.openBox();

		} catch (final IOException ex) {
			Logger.getGlobal().log(Level.SEVERE, "Erro ao abrir a gaveta coletora", ex); //$NON-NLS-1$
			Session.error();
		}

	}


}
