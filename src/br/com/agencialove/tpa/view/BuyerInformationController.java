package br.com.agencialove.tpa.view;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.agencialove.tpa.workflow.Session;
import br.com.agencialove.tpa.workflow.Validator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class BuyerInformationController implements IController {

	@FXML
	private TextField cnpfCnpj;

	@FXML
	private TextField email;

	@FXML
	private TextField nome;

	@FXML
	private Button btnYesAction;

	@FXML
	private TextField telefone;

	@FXML
	private TextField cep;

	@FXML
	private TextField endereco;

	@FXML
	public void btnCancelAction() {
		Session.setScene(Windows.SEND_NFE_EMAIL.getScene());
	}

	@FXML
	public void btnYesAction() {
		Session.setScene(Windows.SEARCH_SATISFACTION.getScene());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		this.email.textProperty().addListener((a, b, c) -> {
			this.validateFields();
		});

		this.cnpfCnpj.textProperty().addListener((a, b, c) -> {
			this.validateFields();
		});

		this.nome.textProperty().addListener((a, b, c) -> {
			this.validateFields();
		});
		
		this.btnYesAction.setDisable(true);

	}

	private void validateFields() {
		Validator validator = new Validator();
		validator.validateNotEmpty(this.cnpfCnpj, true);
		validator.validateNotEmpty(this.nome, true);
		validator.validateEmail(email, true);

		if (validator.isEmpty()) {
			btnYesAction.setDisable(false);
		} else {
			btnYesAction.setDisable(true);
		}

	}

	@Override
	public void clear() {

		this.cnpfCnpj.setText("");
		this.email.setText("");
		this.nome.setText("");
		this.btnYesAction.setDisable(true);
		this.telefone.setText("");
		this.cep.setText("");
		this.endereco.setText("");
	}

}
