package br.com.agencialove.tpa.view;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.agencialove.tpa.workflow.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class WebBrowserController implements IController {

	@FXML
	private WebView webView;

	@FXML
	public Button btnBackToStartWindow;

	@FXML
	private Label titleLabel;


	@Override
	public void initialize(final URL url, final ResourceBundle resourceBundle) {
		final WebEngine webEngine = this.webView.getEngine();
		webEngine.load("http://www.correios.com.br");
	}

	@FXML
	private void btnBackToStartWindow(final ActionEvent e) {
		Session.setScene(Windows.START.getScene());
	}

	@Override
	public void clear() {
		if(this.webView != null) this.webView.getEngine().load("http://www.correios.com.br");
	}
}
