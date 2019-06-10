/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.agencialove.tpa.view;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.agencialove.tpa.utils.Url;
import br.com.agencialove.tpa.workflow.Session;
import br.com.agencialove.tpa.workflow.SessionType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;

/**
 * FXML Controller class
 *
 * @author José
 */
public class StartController implements IController {

	@FXML
	private void btnConsultingAction(final ActionEvent e) {
		Scene scene = Windows.WEB_BROWSER.getScene();
		Session.setScene(Windows.WEB_BROWSER.getScene());
		WebBrowserController controller = (WebBrowserController) scene.getUserData();
		controller.loadUrl(Url.CORREIOS);
	}

	@FXML
	private void btnPostAction(final ActionEvent e) {
		Session.setScene(Windows.TEST_CONFIRMATION.getScene());
		Session.getSession().put(Session.SESSION_TYPE, SessionType.SERVICE);
	}

	@FXML
	private void btnPrePostingAction(final ActionEvent e) {
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

	@Override
	public void initialize(final URL arg0, final ResourceBundle arg1) {

	}

	@Override
	public void clear() {

	}

}
