package br.com.agencialove.tpa.view;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.agencialove.tpa.workflow.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;

public class RemoveStickController implements IController {

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {

	}

	@FXML
	private void btnNextAction(final ActionEvent e) {
		final Scene scene = Windows.OPEN_BOX.getScene();
		final OpenBoxController controller = (OpenBoxController) scene.getUserData();
		controller.startCollectProcess();
		Session.setScene(scene);
	}



	@Override
	public void clear() {

	}


}
