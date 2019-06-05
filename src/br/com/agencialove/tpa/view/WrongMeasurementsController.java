package br.com.agencialove.tpa.view;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.agencialove.tpa.Messages;
import br.com.agencialove.tpa.model.AdditionalServices;
import br.com.agencialove.tpa.model.Address;
import br.com.agencialove.tpa.model.PackageMeasures;
import br.com.agencialove.tpa.model.rest.PrePost;
import br.com.agencialove.tpa.model.rest.PrePostResponse;
import br.com.agencialove.tpa.model.rest.ServicesResponse;
import br.com.agencialove.tpa.webservices.IWebService;
import br.com.agencialove.tpa.workflow.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class WrongMeasurementsController implements IController {


	@FXML
	private Label lbTitle;

	@FXML
	private Button btnBack;

	public void setText(final String text) {
		this.lbTitle.setText(text);
	}

	@Override
	public void initialize(final URL arg0, final ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clear() {
		this.lbTitle.setText(Messages.getString("WrongMeasurements.0")); //$NON-NLS-1$
	}

	@FXML
	private void btnBackAction(final ActionEvent e) {
		final Address sender = (Address) Session.getSession().get(Session.SENDER_ADDRESS);
		final Address receiver = (Address) Session.getSession().get(Session.RECEIVER_ADDRESS);
		final AdditionalServices services = (AdditionalServices) Session.getSession().get(Session.ADDITIONAL_SERVICES);
		final PackageMeasures measures = (PackageMeasures) Session.getSession().get(Session.MEASURES);
		final ServicesResponse serviceResponse = (ServicesResponse) Session.getSession().get(Session.SELECTED_SERVICE);

		//gera PLP [IWebService.serviceRegisterResponse]
		final IWebService webService = Session.getWebService();
		final PrePost req = webService.getPrePostRequest(sender, receiver, services, measures, serviceResponse);
		final PrePostResponse resp = webService.serviceRegisterResponseUnpaid(req);

		final String plp = resp.getNumeroPLP();

		Session.getSession().put(Session.ID_PLP, plp);

		final Scene scene = Windows.PRE_POSTAGEM_CONFIRMATION_CONTROLLER.getScene();
		final PrePostagemConfirmationController controller = (PrePostagemConfirmationController) scene.getUserData();
		controller.clear();
		Session.setScene(scene);
	}

}
