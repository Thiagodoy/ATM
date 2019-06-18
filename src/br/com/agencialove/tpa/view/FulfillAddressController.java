package br.com.agencialove.tpa.view;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.agencialove.tpa.model.Address;
import br.com.agencialove.tpa.model.ZipType;
import br.com.agencialove.tpa.utils.MaskField;
import br.com.agencialove.tpa.workflow.Session;
import br.com.agencialove.tpa.workflow.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

//TODO a validação não está funcionando corretamente para o campo número
public class FulfillAddressController implements IController {

	private ZipType type;
	private Address address;

	@FXML
	private Label lbTitle;

	@FXML
	private TextField txtLogradouro;

	@FXML
	private TextField txtNumero;

	@FXML
	private TextField txtBairro;

	@FXML
	private TextField txtComplemento;

	@FXML
	private ComboBox lblCidade;

	@FXML
	private ComboBox lblEstado;

	@FXML
	private TextField txtCEP;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtCPF;

	@FXML
	private TextField txtCelular;

	@FXML
	private TextField txtEmail;

	@FXML
	private Button btnNext;

	//    Postagem do destinatário Confirmação

	@FXML
	private Label lbLogradouroConfirmacao;

	@FXML
	private Label lbNumeroConfirmacao;

	@FXML
	private Label lbComplementoConfirmacao;

	@FXML
	private Label lbEstadoConfirmacao;

	@FXML
	private Label lbCidadeConfirmacao;

	@FXML
	private Label lbCEPConfirmacao;

	@FXML
	private Label lbNomeDestinatarioConfirmacao;

	@FXML
	private Label lbCelularDestinatarioConfirmacao;

	@FXML
	private Label lbEmailConfirmacao;

	@FXML
	private void btnBackAction(final ActionEvent e) {
		final Scene scene = Windows.SEARCH_ZIP.getScene();
		final SearchZipController controller = (SearchZipController)scene.getUserData();
		controller.setType(this.type);
		Session.setScene(scene);
	}

	@FXML
	private void btnNextAction(final ActionEvent e) {
		this.fulfillAddress();
		Session.getSession().put(this.type.name() + "_ADDRESS", this.address);
		final Scene scene = Windows.CONFIRM_ADDRESS.getScene();
		final ConfirmAddressController controller = (ConfirmAddressController)scene.getUserData();
		controller.setType(this.type);
		Session.setScene(scene);
	}

	@FXML
	private void mandatoryTextFieldReleased(final KeyEvent e) {
		if(this.areMandatoriesFilled())
			this.btnNext.setDisable(false);
		else
			this.btnNext.setDisable(true);
	}

	private boolean areMandatoriesFilled() {
		final Validator validator = new Validator();
		validator.validateNotEmpty(this.txtLogradouro, true);
		validator.validateNotEmpty(this.txtNumero, true);
		validator.validateStringNotEmpty(this.txtNome,true, 3, 100);
		validator.validateNotEmpty(this.txtCEP, true);
		validator.validateCelullar(this.txtCelular, true);

		if(this.type == ZipType.SENDER)
			validator.validateCPF(this.txtCPF, true);


		return validator.isEmpty();
	}

	private void fulfillAddress() {
		this.address.getPerson().setType(this.type);
		this.address.getPerson().setName(this.txtNome.getText().trim());
		this.address.getPerson().setCPF_CPNJ(this.txtCPF.getText().trim());
		this.address.getPerson().setCellPhone(this.txtCelular.getText().trim());
		//this.address.getPerson().setCellPhone(this.txtCelular.getPlainText().trim());
		this.address.getPerson().setEmail(this.txtEmail.getText().trim());
		//this.address.setNumber(this.txtNumero.getPlainText().trim());
		this.address.setNumber(this.txtNumero.getText().trim());
		this.address.setComplement(this.txtComplemento.getText().trim());
	}

	public void setTitle(final String str) {
		this.lbTitle.setText(str);
	}

	@Override
	public void initialize(final URL url, final ResourceBundle resourceBundle) {

	}

	public ZipType getType() {
		return this.type;
	}

	public void setType(final ZipType type) {
		this.type = type;
		switch(this.type) {
		case RECEIVER:
			this.lbTitle.setText("Complemente os dados do destinatário.");
			break;
		case SENDER:
			this.lbTitle.setText("Complemente os dados do remetente.");
		}

		Address address = (Address) Session.getSession().get(this.type.name() + "_ADDRESS");
		if(address == null)
			address = new Address();
		this.setAddress(address);
	}

	public Address getAddress() {
		return this.address;
	}

	private void setAddress(final Address pAddress) {
		this.address = pAddress;
		this.txtLogradouro.setText(this.get(this.address.getStreet()));;
		this.txtBairro.setText(this.address.getNeighborhood());
		this.lblCidade.getItems().add(this.get(this.address.getCity()));
		this.lblCidade.getSelectionModel().select(0);
		this.lblEstado.getItems().add(this.get(this.address.getState()));
		this.lblEstado.getSelectionModel().select(0);
		this.txtCEP.setText(this.get(this.address.getZip()));
		this.txtNome.setText(this.get(this.address.getPerson().getName()));
		this.txtCPF.setText(this.get(this.address.getPerson().getCPF_CPNJ()));;
		this.txtCelular.setText(this.get(this.address.getPerson().getCellPhone()));;
		this.txtEmail.setText(this.get(this.address.getPerson().getEmail()));

		this.txtLogradouro.setDisable(("".equals(this.txtLogradouro.getText().trim())) ? false : true);
		this.txtBairro.setDisable(("".equals(this.txtBairro.getText().trim())) ? false : true);
	}

	private String get(final String str) {
		return (str == null) ? "" : str;
	}

	@Override
	public void clear() {
		if(this.txtLogradouro != null) this.txtLogradouro.setText("");
		//if(this.txtNumero != null) {this.txtNumero.setText(""); this.txtNumero.setPlainText(""); this.txtNumero.clear(); this.txtNumero.requestFocus();};
		if(this.txtComplemento != null) this.txtComplemento.setText("");
		if(this.lblCidade != null) this.lblCidade.getItems().clear();
		if(this.lblEstado != null) this.lblEstado.getItems().clear();
		if(this.txtCEP != null) this.txtCEP.setText("");
		if(this.txtNome != null) this.txtNome.setText("");
		if(this.txtCPF != null) this.txtCPF.setText("");
		//if(this.txtCelular != null) {this.txtCelular.setText(""); this.txtCelular.setPlainText(""); this.txtCelular.clear();};
		if(this.txtEmail != null) this.txtEmail.setText("");
		if(this.btnNext != null) this.btnNext.setDisable(true);
	}
}
