package br.com.agencialove.tpa.view;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.agencialove.tpa.workflow.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;

public class SatisfactionSurveyController implements IController {
	@FXML
	private void btnNextAction(final ActionEvent e) {
		final Scene scene = Windows.REMOVE_STICK.getScene();
		Session.setScene(scene);
	}

	@FXML
	private void btnBackAction(final ActionEvent e) {
		final Scene scene = Windows.REMOVE_STICK.getScene();
		Session.setScene(scene);
	}

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}


}
