package br.com.agencialove.tpa.view;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.agencialove.tpa.model.AdditionalServices;
import br.com.agencialove.tpa.model.ZipType;
import br.com.agencialove.tpa.workflow.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

public class AdditionalServices1Controller implements IController {

	@FXML
	private CheckBox chkNone;

	@FXML
	private CheckBox chkOwnHand;

	@FXML
	private CheckBox chkNotice;

	@FXML
	private CheckBox chkValueDeclaration;

	@FXML
	private Button btnBack;

	@FXML
	private Button btnNext;

	@FXML
	private void btnBackAction(final ActionEvent e) {
		final Scene scene = Windows.CONFIRM_ADDRESS.getScene();
		final ConfirmAddressController controller = (ConfirmAddressController) scene.getUserData();
		controller.setType(ZipType.SENDER);
		Session.setScene(scene);
	}

	@FXML
	private void btnNextAction(final ActionEvent e) {
		final AdditionalServices services = new AdditionalServices();
		services.setOnwHands(this.chkOwnHand.isSelected());
		services.setDeliveryNotice(this.chkNotice.isSelected());
		services.setValueDeclaration(this.chkValueDeclaration.isSelected());
		Session.getSession().put(Session.ADDITIONAL_SERVICES, services);

		Scene scene = null;
		if(services.isValueDeclaration()) {
			scene = Windows.ADDITIONAL_SERVICES2.getScene();
			final AdditionalServices2Controller controller = (AdditionalServices2Controller) scene.getUserData();
			controller.clear();
			controller.loadValues();
		}else {
			scene = Windows.MEASURES.getScene();
			final MeasuresController controller = (MeasuresController)scene.getUserData();
			controller.setPreviousScene(Windows.ADDITIONAL_SERVICES1.getScene());
			controller.clear();
		}
		Session.setScene(scene);
	}

	@FXML
	public void chkNoneAction() {
		if(this.chkNone.isSelected()) {
			this.chkOwnHand.setSelected(false);
			this.chkOwnHand.setDisable(true);
			this.chkNotice.setSelected(false);
			this.chkNotice.setDisable(true);
			this.chkValueDeclaration.setSelected(false);
			this.chkValueDeclaration.setDisable(true);
		}else {
			this.chkOwnHand.setDisable(false);
			this.chkNotice.setDisable(false);
			this.chkValueDeclaration.setDisable(false);
		}

	}

	@Override
	public void initialize(final URL url, final ResourceBundle resourceBundle) {

	}

	public void loadValues() {
		final AdditionalServices services = (AdditionalServices) Session.getSession().get(Session.ADDITIONAL_SERVICES);
		if(services != null){
			this.chkOwnHand.setSelected(services.isOnwHands());
			this.chkNotice.setSelected(services.isDeliveryNotice());
			this.chkValueDeclaration.setSelected(services.isValueDeclaration());
		}else {
			this.chkOwnHand.setSelected(false);
			this.chkNotice.setSelected(false);
			this.chkValueDeclaration.setSelected(false);
		}
	}

	@Override
	public void clear() {
		if(this.chkNone != null) this.chkNone.setSelected(false);
		if(this.chkOwnHand != null) this.chkOwnHand.setSelected(false);
		if(this.chkNotice != null) this.chkNotice.setSelected(false);
		if(this.chkValueDeclaration != null) this.chkValueDeclaration.setSelected(false);
	}

}
