package br.com.agencialove.tpa.view;

import java.net.URL;
import java.util.ResourceBundle;

import org.w3c.dom.html.HTMLFormElement;

import com.itextpdf.text.Element;

import br.com.agencialove.tpa.utils.Url;
import br.com.agencialove.tpa.workflow.Session;
import br.com.agencialove.tpa.workflow.SessionType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
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
	public Button btnFinalize;

	@FXML
	private Label titleLabel;

	private Url url;
	
	private Scene nextScene;
	
	@Override
	public void initialize(final URL url, final ResourceBundle resourceBundle) {
		
	}

	@FXML
	private void btnBackToStartWindow(final ActionEvent e) {
		Session.setScene(Windows.START.getScene());
	}
	
	@FXML
	private void btnFinalize(final ActionEvent e) {
		Session.setScene(nextScene);
	}

	@Override
	public void clear() {
		if(this.webView != null) this.webView.getEngine().load("http://www.correios.com.br");
	}
	
	private void loadStatus(Url url){
		switch (url) {
			case CORREIOS:
				this.btnBackToStartWindow.setVisible(true);
				this.btnBackToStartWindow.setDisable(false);
			break;
			case SATISFACAO:
				this.btnFinalize.setVisible(true);
				this.btnFinalize.setDisable(false);
	
		}
		
		
		final SessionType type = (SessionType) Session.getSession().get(Session.SESSION_TYPE);
		
		if(type != null) {
			
			if(type == SessionType.PACKAGE) {
								
			}else if(type ==SessionType.PRE_POSTING || type == SessionType.SERVICE) {
				
			}
			
			
			
		}
		
		
		
	}

	
	public void loadUrl(Url url) {
		
		this.loadStatus(url);
		
		final WebEngine webEngine = this.webView.getEngine();
		
		
		 final String username = "324jlk23j4l2klj34l2kj34l2jk34l2kj3lkjlajksdflkj@gmail.com";
	        final String password = "password";
		
//		webEngine.getLoadWorker().stateProperty().addListener(
//                new ChangeListener<Worker.State>() {
//                    @Override
//                    public void changed( ObservableValue<? extends Worker.State> ov,
//                                         Worker.State oldState, Worker.State newState ) {
//                        if ( newState == Worker.State.SUCCEEDED ) {
//                            Element emailField = (Element) webEngine.getDocument().getElementById( "Email" );
//                            if ( emailField != null ) {
//                                ((org.w3c.dom.Element) emailField).setAttribute( "Value", "" );
//                            }
//                            Element passwordField = (Element) webEngine.getDocument().getElementById( "Passwd" );
//                            if ( emailField != null ) {
//                                ((org.w3c.dom.Element) passwordField).setAttribute( "Value", password );
//                            }
//                            HTMLFormElement gaia_loginform = ( HTMLFormElement ) webEngine.getDocument().getElementById( "gaia_loginform" );
////                            if ( gaia_loginform != null ) {
////                                if ( !submitted.get() ) {
////                                    submitted.set( true );
////                                    gaia_loginform.submit();
////                                }
////                            }
//                        }
//                    }
//                }
//        );
		
		webEngine.load(url.getUrl());
		
		
	}
	
	@FXML
	@Override
	public void cancel() {
		Session.reset();		
	}
	
}
