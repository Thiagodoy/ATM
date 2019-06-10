package br.com.agencialove.tpa.view;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.agencialove.tpa.utils.Url;
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

	private Url url;
	
	@Override
	public void initialize(final URL url, final ResourceBundle resourceBundle) {
		
	}

	@FXML
	private void btnBackToStartWindow(final ActionEvent e) {
		Session.setScene(Windows.START.getScene());
	}

	@Override
	public void clear() {
		if(this.webView != null) this.webView.getEngine().load("http://www.correios.com.br");
	}
	
	public void loadUrl(Url url) {
		final WebEngine webEngine = this.webView.getEngine();
		webEngine.load(url.getUrl());
	}
	
}
