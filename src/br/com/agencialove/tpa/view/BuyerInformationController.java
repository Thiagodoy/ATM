package br.com.agencialove.tpa.view;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.agencialove.tpa.workflow.Session;
import javafx.fxml.FXML;

public class BuyerInformationController implements IController {	
	
	
	@FXML
	public void btnCancelAction() {
		Session.reset();
	}
	
	@FXML
	public void btnYesAction() {		
		
		//:TODO IMPLEMENTAR A VALIDAÇão das informaçoes		
		Session.setScene(Windows.PAYMENT.getScene());
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
	}

}
