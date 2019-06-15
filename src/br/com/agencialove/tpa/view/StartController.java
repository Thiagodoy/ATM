/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.agencialove.tpa.view;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;



import br.com.agencialove.tpa.utils.Url;
import br.com.agencialove.tpa.workflow.Session;
import br.com.agencialove.tpa.workflow.SessionType;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author José
 */
public class StartController implements IController {

	private Timer timer;
	

	@FXML
	private StackPane stake;
	
	@FXML
	private void btnConsultingAction(final ActionEvent e) {		
		Scene scene = Windows.WEB_BROWSER.getScene();	
		this.cancelTime();
		Session.setScene(Windows.WEB_BROWSER.getScene());
		WebBrowserController controller = (WebBrowserController) scene.getUserData();
		controller.loadUrl(Url.CORREIOS);
	}

	@FXML
	private void btnPostAction(final ActionEvent e) {	
		
		Session.setScene(Windows.TEST_CONFIRMATION.getScene());
		this.cancelTime();
		Session.getSession().put(Session.SESSION_TYPE, SessionType.SERVICE);
	}

	@FXML
	private void btnPrePostingAction(final ActionEvent e) {	
		this.cancelTime();
		Session.setScene(Windows.PRE_POSTING.getScene());
		Session.getSession().put(Session.SESSION_TYPE, SessionType.PRE_POSTING);
	}

	@FXML
	private void btnBuyPackageAction(final ActionEvent e) {
		
		final Scene scene = Windows.SELECT_PACKAGE.getScene();
		final SelectPackageController controller = (SelectPackageController) scene.getUserData();
		controller.setPreviousScene(Windows.START.getScene());
		controller.loadPacksFromDB();
		Session.getSession().put(Session.SESSION_TYPE, SessionType.PACKAGE);
		Session.setScene(scene);
	}
	
	private void cancelTime() {
		this.timer.cancel();
	}

	public void activeRest() {	
		
		timer = new Timer();
		
		timer.schedule(new TimerTask() {			
			@Override
			public void run() {
				Platform.runLater(()-> Session.setScene( Windows.DESCANSO.getScene()));				
			}
		}, 30000);

	}

	@Override
	public void initialize(final URL arg0, final ResourceBundle arg1) {

	}

	@Override
	public void clear() {

	}

}
