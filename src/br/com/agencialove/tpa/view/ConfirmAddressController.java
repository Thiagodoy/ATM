package br.com.agencialove.tpa.view;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import br.com.agencia.tpa.rest.request.DestinatarioRequest;
import br.com.agencia.tpa.rest.request.PrePostagemRequest;
import br.com.agencia.tpa.rest.request.RemetenteRequest;
import br.com.agencia.tpa.rest.response.CepResponse;
import br.com.agencialove.tpa.model.ZipType;
import br.com.agencialove.tpa.workflow.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public class ConfirmAddressController implements IController {

	private ZipType type;	
	
	
	private DestinatarioRequest destinatario;
	private RemetenteRequest remetente;
	

	@FXML
	private Label lbTitle;

	@FXML
	private Label lbLogradouroConfirmacao;

	@FXML
	private Label lbNumeroConfirmacao;

	@FXML
	private Label lbBairro;

	@FXML
	private Label lbComplementoConfirmacao;

	@FXML
	private Label lbEstadoConfirmacao;

	@FXML
	private Label lbCEPConfirmacao;

	@FXML
	private Label lbCidadeConfirmacao;

	@FXML
	private Label lbNomeDestinatarioConfirmacao;

	@FXML
	private Label lbCPFDestinatario;

	@FXML
	private Label lbCelularDestinatarioConfirmacao;

	@FXML
	private Label lbEmailConfirmacao;


	@FXML
	private void btnBackAction(final ActionEvent e) {
		final Scene scene = Windows.FULFILL_ADDRESS.getScene();
		final FulfillAddressController controller = (FulfillAddressController)scene.getUserData();
		controller.setType(this.type);
		Session.setScene(scene);
	}

	@FXML
	private void btnNextAction(final ActionEvent e) {
		Scene scene = null;
		switch(this.type) {
		case RECEIVER:
			scene = Windows.SEARCH_ZIP.getScene();
			final SearchZipController controller = (SearchZipController) scene.getUserData();
			controller.clear();
			controller.setType(ZipType.SENDER);
			break;
		case SENDER:
			scene = Windows.ADDITIONAL_SERVICES1.getScene();
			break;
		}
		Session.setScene(scene);
	}


	public void setTitle(final String str) {
		this.lbTitle.setText(str);
	}

	@Override
	public void initialize(final URL url, final ResourceBundle resourceBundle) {

		//        MaskFieldUtil.foneField(this.txtCelular);
		//        MaskFieldUtil.cepField(this.txtCEP);

		//this.tipoPessoa = ContentNavigator.getController().getTipoPessoa();

		//ContentNavigator.getController().setButtonActionPerformet(event -> ContentNavigator.loadContent(ContentNavigator.CONSULTA_CEP_WINDOW));
	}

	public ZipType getType() {
		return this.type;
	}

	public void setType(final ZipType type) {
		this.type = type;
		
		
		PrePostagemRequest request = (PrePostagemRequest) Session.getSession().get(Session.PRE_POSTAGEM);
		String lbLogradouroConfirmacao = "";
		String lbNumeroConfirmacao = "";
		String lbBairro = "";
		String lbComplementoConfirmacao = "";
		String lbEstadoConfirmacao = "";
		String lbCEPConfirmacao = "";
		String lbCidadeConfirmacao = "";
		String lbNomeDestinatarioConfirmacao = "";
		String lbCPFDestinatario = "";
		String lbCelularDestinatarioConfirmacao = "";
		String lbEmailConfirmacao = "";
		CepResponse cep = (CepResponse) Session.getSession().get(this.type.name() + "_ADDRESS");
		cep = Optional.ofNullable(cep).isPresent() ? cep : new CepResponse();
		switch (this.type) {
		case RECEIVER:
			this.lbTitle.setText("Complemente os dados do destinatário.");
			this.destinatario = request.getObjetoPostalRequest().get(0).getDestinatario() != null
					? request.getObjetoPostalRequest().get(0).getDestinatario()
					: new DestinatarioRequest();

					 lbLogradouroConfirmacao = this.destinatario.getLogradouro();
					 lbNumeroConfirmacao = this.destinatario.getNumero();
					 lbBairro = cep.getBairro();
					 lbComplementoConfirmacao = this.destinatario.getComplemento();
					 lbEstadoConfirmacao = cep.getEstado();
					 lbCEPConfirmacao = this.destinatario.getCep();
					 lbCidadeConfirmacao = this.destinatario.getCidade();
					 lbNomeDestinatarioConfirmacao = this.destinatario.getNome();
					 lbCPFDestinatario = this.destinatario.getCpf();
					 lbCelularDestinatarioConfirmacao = this.destinatario.getCelular() == 0 ? "" : String.valueOf(this.destinatario.getCelular()) ;
					 lbEmailConfirmacao = this.destinatario.getEmail();

			break;
		case SENDER:
			this.lbTitle.setText("Complemente os dados do remetente.");
			this.remetente = request.getRemetente() != null ? request.getRemetente()
					: new RemetenteRequest();
			
			lbLogradouroConfirmacao = this.remetente.getLogradouro();
			 lbNumeroConfirmacao = this.remetente.getNumero();
			 lbBairro = cep.getBairro();
			 lbComplementoConfirmacao = this.remetente.getComplemento();
			 lbEstadoConfirmacao = cep.getEstado();
			 lbCEPConfirmacao = this.remetente.getCep();
			 lbCidadeConfirmacao = this.remetente.getCidade();
			 lbNomeDestinatarioConfirmacao = this.remetente.getNome();
			 lbCPFDestinatario = this.remetente.getCpf();
			 lbCelularDestinatarioConfirmacao = this.remetente.getCelular() == 0 ? "" : String.valueOf(this.remetente.getCelular()) ;
			 lbEmailConfirmacao = this.remetente.getEmail();

		}
		
		this.lbLogradouroConfirmacao.setText(lbLogradouroConfirmacao);
		this.lbNumeroConfirmacao.setText(lbNumeroConfirmacao);
		this.lbBairro.setText(lbBairro);
		this.lbComplementoConfirmacao.setText(lbComplementoConfirmacao);
		this.lbEstadoConfirmacao.setText(lbEstadoConfirmacao);
		this.lbCEPConfirmacao.setText(lbCEPConfirmacao);
		this.lbCidadeConfirmacao.setText(lbCidadeConfirmacao);
		this.lbNomeDestinatarioConfirmacao.setText(lbNomeDestinatarioConfirmacao);
		this.lbCPFDestinatario.setText(lbCPFDestinatario);
		this.lbCelularDestinatarioConfirmacao.setText(lbCelularDestinatarioConfirmacao);
		this.lbEmailConfirmacao.setText(lbEmailConfirmacao);
	
	}

	@Override
	public void clear() {
		
		if (Optional.ofNullable(this.remetente).isPresent())
			this.remetente = null;
		if (Optional.ofNullable(this.destinatario).isPresent())
			this.destinatario = null;
		
		if(this.lbLogradouroConfirmacao != null) this.lbLogradouroConfirmacao.setText("");
		if(this.lbNumeroConfirmacao != null) this.lbNumeroConfirmacao.setText(""); //$NON-NLS-1$
		if(this.lbBairro != null) this.lbBairro.setText(""); //$NON-NLS-1$
		if(this.lbComplementoConfirmacao != null) this.lbComplementoConfirmacao.setText(""); //$NON-NLS-1$
		if(this.lbEstadoConfirmacao != null) this.lbEstadoConfirmacao.setText(""); //$NON-NLS-1$
		if(this.lbCEPConfirmacao != null) this.lbCEPConfirmacao.setText(""); //$NON-NLS-1$
		if(this.lbCidadeConfirmacao != null) this.lbCidadeConfirmacao.setText(""); //$NON-NLS-1$
		if(this.lbNomeDestinatarioConfirmacao != null) this.lbNomeDestinatarioConfirmacao.setText(""); //$NON-NLS-1$
		if(this.lbNomeDestinatarioConfirmacao != null) this.lbNomeDestinatarioConfirmacao.setText(""); //$NON-NLS-1$
		if(this.lbCelularDestinatarioConfirmacao != null) this.lbCelularDestinatarioConfirmacao.setText(""); //$NON-NLS-1$
		if(this.lbEmailConfirmacao != null) this.lbEmailConfirmacao.setText(""); //$NON-NLS-1$
	}
}
