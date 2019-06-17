package br.com.agencialove.tpa.view;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.agencialove.tpa.utils.Url;
import br.com.agencialove.tpa.workflow.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class SearchSatisfactionController implements IController {

	@FXML
	private Button btnYes;

	@FXML
	private Button btnNo;

	@FXML
	private void btnYesAction(final ActionEvent e) {
		final Scene scene = Windows.WEB_BROWSER.getScene();
		WebBrowserController controller = (WebBrowserController) scene.getUserData();
		controller.loadUrl(Url.SATISFACAO);
		Session.setScene(scene);
	}

	@FXML
	private void btnNoAction(final ActionEvent e) {
		final Scene scene = Windows.FINISH.getScene();
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