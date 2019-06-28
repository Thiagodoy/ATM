package br.com.agencialove.tpa.view;

import java.net.URL;
import java.text.MessageFormat;
import java.util.Optional;
import java.util.ResourceBundle;

import br.com.agencia.tpa.rest.request.DestinatarioRequest;
import br.com.agencia.tpa.rest.request.NacionalRequest;
import br.com.agencia.tpa.rest.request.PrePostagemRequest;
import br.com.agencia.tpa.rest.request.RemetenteRequest;
import br.com.agencia.tpa.rest.response.CepResponse;
import br.com.agencialove.tpa.model.ZipType;
import br.com.agencialove.tpa.utils.MaskField;
import br.com.agencialove.tpa.workflow.Session;
import br.com.agencialove.tpa.workflow.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

//TODO a validação não está funcionando corretamente para o campo número
public class FulfillAddressController implements IController {

	private ZipType type;
	private CepResponse cep;

	private DestinatarioRequest destinatario;
	private RemetenteRequest remetente;

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
	private TextField txtCEP;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtCPF;

	@FXML
	private MaskField txtCelular;

	@FXML
	private TextField txtEmail;

	@FXML
	private Button btnNext;

	// Postagem do destinatário Confirmação

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
		final SearchZipController controller = (SearchZipController) scene.getUserData();
		controller.setType(this.type);
		Session.setScene(scene);
	}

	@FXML
	private void btnNextAction(final ActionEvent e) {
		
		PrePostagemRequest request = (PrePostagemRequest) Session.getSession().get(Session.PRE_POSTAGEM);
		
		if(this.type.equals(ZipType.RECEIVER)) {
			DestinatarioRequest destinatarioRequest = this.destinatario();
			request.getObjetoPostalRequest().get(0).setDestinatario(destinatarioRequest);
			request.getObjetoPostalRequest().get(0).setNacionalRequest(this.nacional());
		}else {
			RemetenteRequest remetenteRequest = this.remetente();
			request.setRemetenteRequest(remetenteRequest);
		}		
		
		final Scene scene = Windows.CONFIRM_ADDRESS.getScene();
		final ConfirmAddressController controller = (ConfirmAddressController) scene.getUserData();
		controller.setType(this.type);
		Session.setScene(scene);
	}

	@FXML
	private void mandatoryTextFieldReleased(final KeyEvent e) {
		if (this.areMandatoriesFilled())
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
		 //validator.validateCelullar(this.txtCelular, true);

		if (this.type == ZipType.SENDER)
			validator.validateCPF(this.txtCPF, true);

		return validator.isEmpty();
	}
	
	private RemetenteRequest remetente() {

		RemetenteRequest remetente = new RemetenteRequest();
		remetente.setNome(this.txtNome.getText().trim());
		remetente.setCpf(this.txtCPF.getText().trim());

		String celular = this.txtCelular.getText().trim().replaceAll("([^0-9])*", "");

		if (celular.length() > 0)
			remetente.setCelular(Long.parseLong(celular));

		remetente.setEmail(this.txtEmail.getText().trim());
		remetente.setNumero(this.txtNumero.getText().trim());
		remetente.setComplemento(this.txtComplemento.getText().trim());
		remetente.setCep(this.txtCEP.getText());
		remetente.setCidade(this.cep.getCidade());
		String logradouro = MessageFormat.format("{0} {1} {2} ",cep.getRua(), cep.getBairro(), cep.getCidade());
		remetente.setLogradouro(logradouro);		

		return remetente;
	}

	private NacionalRequest nacional() {
		
		NacionalRequest nacionalRequest = new NacionalRequest();
		CepResponse c = (CepResponse) Session.getSession().get(this.type.name() + "_ADDRESS");
		nacionalRequest.setBairro(c.getBairro());
		nacionalRequest.setCep(c.getCep());
		nacionalRequest.setCidade(c.getCidade());
		nacionalRequest.setEstado(c.getEstado());
		nacionalRequest.setCentroCustoCliente("0069950016");
		return nacionalRequest;
		
		
	}
	private DestinatarioRequest destinatario() {

		DestinatarioRequest destinatarioRequest = new DestinatarioRequest();
		destinatarioRequest.setNome(this.txtNome.getText().trim());
		destinatarioRequest.setCpf(this.txtCPF.getText().trim());

		String celular = this.txtCelular.getText().trim().replaceAll("([^0-9])*", "");

		if (celular.length() > 0)
			destinatarioRequest.setCelular(Long.parseLong(celular));

		destinatarioRequest.setEmail(this.txtEmail.getText().trim());
		destinatarioRequest.setNumero(this.txtNumero.getText().trim());
		destinatarioRequest.setComplemento(this.txtComplemento.getText().trim());
		destinatarioRequest.setCep(this.txtCEP.getText());
		destinatarioRequest.setCidade(this.cep.getCidade());
		String logradouro = MessageFormat.format("{0} {1} {2} ",cep.getRua(), cep.getBairro(), cep.getCidade());
		destinatarioRequest.setLogradouro(logradouro);		

		return destinatarioRequest;
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

		PrePostagemRequest request = (PrePostagemRequest) Session.getSession().get(Session.PRE_POSTAGEM);
		String nome = "";
		String cpf = "";
		String email = "";
		String celular = "";

		switch (this.type) {
		case RECEIVER:
			this.lbTitle.setText("Complemente os dados do destinatário.");
			this.destinatario = request.getObjetoPostalRequest().get(0).getDestinatario() != null
					? request.getObjetoPostalRequest().get(0).getDestinatario()
					: new DestinatarioRequest();

			nome = destinatario.getNome();
			cpf = destinatario.getCpf();
			email = destinatario.getEmail();
			celular = destinatario.getCelular() == 0 ? "" : String.valueOf(destinatario.getCelular());

			break;
		case SENDER:
			this.lbTitle.setText("Complemente os dados do remetente.");
			this.remetente = request.getRemetente() != null ? request.getRemetente()
					: new RemetenteRequest();
			nome = remetente.getNome();
			cpf = remetente.getCpf();
			email = remetente.getEmail();
			celular = remetente.getCelular() == 0 ? "" : String.valueOf(remetente.getCelular());
		}

		CepResponse c = (CepResponse) Session.getSession().get(this.type.name() + "_ADDRESS");
		this.cep = c;

		c = Optional.ofNullable(c).isPresent() ? c : new CepResponse();

		this.txtLogradouro.setText(c.getRua());
		this.txtLogradouro.setDisable(("".equals(this.txtLogradouro.getText().trim())) ? false : true);
		this.txtBairro.setDisable(("".equals(this.txtBairro.getText().trim())) ? false : true);

		this.txtBairro.setText(c.getBairro());
		this.txtCEP.setText(c.getCep());

		this.txtNome.setText(nome);
		this.txtCPF.setText(cpf);
		this.txtCelular.setText(celular);
		this.txtEmail.setText(email);

	}
	
	@Override
	public void clear() {

		if (Optional.ofNullable(this.remetente).isPresent())
			this.remetente = null;
		if (Optional.ofNullable(this.destinatario).isPresent())
			this.destinatario = null;

		if (this.txtLogradouro != null)
			this.txtLogradouro.setText("");
		// if(this.txtNumero != null) {this.txtNumero.setText("");
		// this.txtNumero.setPlainText(""); this.txtNumero.clear();
		// this.txtNumero.requestFocus();};
		if (this.txtComplemento != null)
			this.txtComplemento.setText("");
		// if(this.lblCidade != null) this.lblCidade.getItems().clear();
		// if(this.lblEstado != null) this.lblEstado.getItems().clear();
		if (this.txtCEP != null)
			this.txtCEP.setText("");
		if (this.txtNome != null)
			this.txtNome.setText("");
		if (this.txtCPF != null)
			this.txtCPF.setText("");
		// if(this.txtCelular != null) {this.txtCelular.setText("");
		// this.txtCelular.setPlainText(""); this.txtCelular.clear();};
		if (this.txtEmail != null)
			this.txtEmail.setText("");
		if (this.btnNext != null)
			this.btnNext.setDisable(true);
	}
}
