package br.com.agencialove.tpa.view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.agencialove.tpa.Configuration;
import br.com.agencialove.tpa.Messages;
import br.com.agencialove.tpa.model.AdditionalServices;
import br.com.agencialove.tpa.model.Address;
import br.com.agencialove.tpa.model.ObjectType;
import br.com.agencialove.tpa.model.PackageMeasures;
import br.com.agencialove.tpa.model.rest.ServicesRequest;
import br.com.agencialove.tpa.model.rest.ServicesResponse;
import br.com.agencialove.tpa.webservices.IWebService;
import br.com.agencialove.tpa.workflow.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class MeasuresController implements IController {


	@FXML
	private Button btnBack;

	@FXML
	private Button btnNext;

	@FXML
	private Button btnMeasures;

	@FXML
	private Button btnPackageP;

	@FXML
	private Button btnPackageM;

	@FXML
	private Button btnPackageG;

	@FXML
	private Button btnPackageL;

	private PackageMeasures measures;
	private ObjectType objectType;
	private Scene previousScene;

	@FXML
	private void btnBackAction(final ActionEvent e) {

		if(this.previousScene == Windows.ADDITIONAL_SERVICES2.getScene()) {
			final AdditionalServices2Controller controller = (AdditionalServices2Controller) this.previousScene.getUserData();
			controller.loadValues();
		}

		Session.setScene(this.previousScene);
	}


	@FXML
	private void btnPackageAction(final ActionEvent e) {
		if(this.btnPackageP.isArmed()) {
			this.objectType = ObjectType.SMALL;
			final String dimensions = Configuration.getConfigurations().get("package.template.small");
			Session.getHardwareService().setVolumeMeasured(dimensions);
			this.setSelectedButton(this.btnPackageP);
		}

		else if(this.btnPackageM.isArmed()) {
			this.objectType = ObjectType.MID;
			final String dimensions = Configuration.getConfigurations().get("package.template.mid");
			Session.getHardwareService().setVolumeMeasured(dimensions);
			this.setSelectedButton(this.btnPackageM);
		}

		else if(this.btnPackageG.isArmed()) {
			this.objectType = ObjectType.BIG;
			final String dimensions = Configuration.getConfigurations().get("package.template.big");
			Session.getHardwareService().setVolumeMeasured(dimensions);
			this.setSelectedButton(this.btnPackageG);
		}

		if(this.objectType != null)
			this.btnMeasures.setDisable(false);
	}

	private void setSelectedButton(final Button armed) {
		final Button[] buttons = new Button[3];
		buttons[0] = this.btnPackageP;
		buttons[1] = this.btnPackageM;
		buttons[2] = this.btnPackageG;

		for(final Button b : buttons)
			if(b == armed)
				b.getStyleClass().add("selectedButton");
			else
				b.getStyleClass().remove("selectedButton");
	}


	@FXML
	private void btnNextAction(final ActionEvent e) {
		
		
		
		final Address sender = (Address) Session.getSession().get(Session.SENDER_ADDRESS);
		final Address receiver = (Address) Session.getSession().get(Session.RECEIVER_ADDRESS);
		final AdditionalServices services = (AdditionalServices) Session.getSession().get(Session.ADDITIONAL_SERVICES);
		this.measures.setType(Integer.toString(this.objectType.ordinal()));

		final ServicesRequest req = Session.getWebService().getServiceRequest(sender, receiver, services, this.measures);

		final IWebService ws = Session.getWebService();
		final List<ServicesResponse> resp = ws.getAvailableServices(req);
		//07773716501
		Session.getSession().put(Session.AVAILABLE_SERVICES, resp);

		final Scene scene = Windows.SELECT_SERVICE.getScene();
		final SelectServiceController controller = (SelectServiceController) scene.getUserData();
		controller.clear();
		controller.loadInfo();
		Session.setScene(scene);
	}


	@FXML
	private void btnMeasureAction(final ActionEvent e) {
		if(this.objectType != null) {
			try {
				this.measures = Session.getHardwareService().getMeasures();
			} catch (final Exception ioe) {
				Logger.getAnonymousLogger().log(Level.SEVERE, Messages.getString("MeasuresController.4"), ioe); //$NON-NLS-1$
				Session.error();
			}
		}

		Session.getSession().put(Session.MEASURES, this.measures);

		if(this.objectType != null)
			this.btnNext.setDisable(false);
	}



	@Override
	public void initialize(final URL location, final ResourceBundle resources) {

	}

	@Override
	public void clear() {
		if(this.measures != null) this.measures = null;
		if(this.objectType != null) this.objectType = null;
		Session.getHardwareService().setVolumeMeasured(null);
		this.btnNext.setDisable(true);
		this.btnMeasures.setDisable(true);
	}

	public Scene getPreviousScene() {
		return this.previousScene;
	}

	public void setPreviousScene(final Scene previousScene) {
		this.previousScene = previousScene;
	}
}
