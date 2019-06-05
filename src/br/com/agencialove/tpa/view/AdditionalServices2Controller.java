package br.com.agencialove.tpa.view;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.agencialove.tpa.model.AdditionalServices;
import br.com.agencialove.tpa.workflow.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AdditionalServices2Controller implements IController {

	@FXML
	private TextField txtValue;

	@FXML
	private TextArea txtContentDeclaration;

	@FXML
	private Button btnBack;

	@FXML
	private Button btnNext;

	@FXML
	private void btnBackAction(final ActionEvent e) {
		final Scene scene = Windows.ADDITIONAL_SERVICES1.getScene();
		final AdditionalServices1Controller controller = (AdditionalServices1Controller)scene.getUserData();
		controller.loadValues();
		Session.setScene(scene);
	}

	@FXML
	private void btnNextAction(final ActionEvent e) {
		final AdditionalServices services = (AdditionalServices) Session.getSession().get(Session.ADDITIONAL_SERVICES);
		services.setContentValue(this.txtValue.getText());
		services.setContent(this.txtContentDeclaration.getText());

		final Scene scene = Windows.MEASURES.getScene();
		final MeasuresController controller = (MeasuresController)scene.getUserData();
		controller.setPreviousScene(Windows.ADDITIONAL_SERVICES1.getScene());
		controller.clear();
		Session.setScene(scene);
	}


	public void loadValues() {
		final AdditionalServices services = (AdditionalServices) Session.getSession().get(Session.ADDITIONAL_SERVICES);
		if(services != null) {
			this.txtValue.setText(services.getContentValue());
			this.txtContentDeclaration.setText(services.getContent());
		}
	}

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clear() {
		if(this.txtValue != null) this.txtValue.setText("");
		if(this.txtContentDeclaration != null) this.txtContentDeclaration.setText("");
	}

}
