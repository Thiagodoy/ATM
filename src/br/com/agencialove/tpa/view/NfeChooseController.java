package br.com.agencialove.tpa.view;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.agencialove.tpa.workflow.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class NfeChooseController implements IController {


	@FXML
	private Button btnYes;

	@FXML
	private Button btnNo;

	@FXML
	private void btnYesAction(final ActionEvent e) {
		final Scene scene = Windows.CHECKOUT.getScene();
		final CheckoutController controller = (CheckoutController) scene.getUserData();
		controller.clear();
		controller.loadInfo();
		Session.setScene(scene);
	}

	@FXML
	private void btnNoAction(final ActionEvent e) {
		final Scene scene = Windows.CONTENT_DECLARATION.getScene();
		final ContentDeclarationController controller = (ContentDeclarationController) scene.getUserData();
		controller.clear();
		controller.setBackScene(Windows.NFE_CHOOSE.getScene());
		Session.setScene(scene);
	}

	@Override
	public void initialize(final URL arg0, final ResourceBundle arg1) {
	}

	@Override
	public void clear() {

	}

}