package br.com.agencialove.tpa.view;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.agencialove.tpa.workflow.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class SendNfeEmailController implements IController {
	
	@FXML
	private Button btnYes;

	@FXML
	private Button btnNo;		

	@FXML
	private void btnYesAction(final ActionEvent e) {
		final Scene scene = Windows.BUYER_INFORMATION.getScene();		
		Session.setScene(scene);
	}

	@FXML
	private void btnNoAction(final ActionEvent e) {
		final Scene scene = Windows.FINISH.getScene();
		FinishController control = (FinishController)Windows.FINISH.getScene().getUserData();
		control.finish();
		Session.setScene(scene);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}
	
	@FXML
	@Override
	public void cancel() {
		Session.reset();		
	}

}
