package br.com.agencialove.tpa.view;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.agencialove.tpa.Messages;
import br.com.agencialove.tpa.workflow.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class OverAttemptsController implements IController {


	@FXML
	private Label lbText;

	public void setText(final String text) {
		this.lbText.setText(text);
	}

	@Override
	public void initialize(final URL arg0, final ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clear() {
		this.lbText.setText(Messages.getString("OverAttemptsController.0")); //$NON-NLS-1$
	}
	
	@FXML
	@Override
	public void cancel() {
		Session.reset();		
	}

}
