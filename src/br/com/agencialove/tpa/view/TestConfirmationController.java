package br.com.agencialove.tpa.view;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.agencialove.tpa.model.ZipType;
import br.com.agencialove.tpa.workflow.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class TestConfirmationController implements IController {


	@FXML
	private Button btnYes;

	@FXML
	private Button btnNo;

	@FXML
	private void btnYesAction(final ActionEvent e) {
		final Scene scene = Windows.SEARCH_ZIP.getScene();
		final SearchZipController controller = (SearchZipController) scene.getUserData();
		controller.clear();
		controller.setType(ZipType.RECEIVER);
		Session.setScene(scene);
	}

	@FXML
	private void btnNoAction(final ActionEvent e) {
		Session.reset();
	}

	@Override
	public void initialize(final URL arg0, final ResourceBundle arg1) {
	}

	@Override
	public void clear() {

	}
	
	@FXML
	@Override
	public void cancel() {
		Session.reset();		
	}

}