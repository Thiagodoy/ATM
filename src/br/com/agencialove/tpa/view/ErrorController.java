package br.com.agencialove.tpa.view;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.agencialove.tpa.workflow.Session;
import javafx.fxml.FXML;

public class ErrorController implements IController {

	@Override
	public void clear() {
	}

	@Override
	public void initialize(final URL arg0, final ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
	
	@FXML
	@Override
	public void cancel() {
		Session.reset();		
	}
}
