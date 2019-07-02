package br.com.agencialove.tpa.view;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.agencia.tpa.rest.request.DestinatarioRequest;
import br.com.agencia.tpa.rest.request.NacionalRequest;
import br.com.agencia.tpa.rest.request.PrePostagemRequest;
import br.com.agencia.tpa.rest.request.RemetenteRequest;
import br.com.agencialove.tpa.model.AdditionalServices;
import br.com.agencialove.tpa.model.rest.PrePost;
import br.com.agencialove.tpa.workflow.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public class ConfirmPrePostController implements IController {

	AdditionalServices as;
	private PrePost prePost;

	@FXML
	private Label lbTitle;

	@FXML
	private Label lbTelefone;

	@FXML
	private Label lbNome;

	@FXML
	private Label lbCEP;

	@FXML
	private Label lbNumero;

	@FXML
	private Label lbLogradouro;

	@FXML
	private Label lbEmail;


	@FXML
	private Label lbTelefoneRemetente;

	@FXML
	private Label lbNomeRemetente;

	@FXML
	private Label lbCEPRemetente;

	@FXML
	private Label lbNumeroRemetente;

	@FXML
	private Label lbLogradouroRemetente;

	@FXML
	private Label lbEmailRemetente;



	@FXML
	private void btnBackAction(final ActionEvent e) {
		final Scene scene = Windows.PRE_POSTING.getScene();

		Session.setScene(scene);
	}

	@FXML
	private void btnNextAction(final ActionEvent e) {
		Session.getSession().put(Session.PRE_POST, this.prePost);

		final Scene scene = Windows.MEASURES_PRE_POST.getScene();
		Session.setScene(scene);
	}

	//plp: 161468491
	//plp: 161468755
	//plp: 161475922
	//plp: 161480022
	//plp: 161491084
	//plp: 161493382 > Falta arrumar "numero_remetente": "null",
	//plp: 161544230 > Falta arrumar "numero_remetente": "null",
	public void setPrePostDestinatario(final String nomeDestinatario, final String cepDestinatario, final String numeroEndDestinatario,
			final String logradouroDestinatario, final String emailDestinatario, final String telefoneDestinatario,
			//Remetente
			final String nomeRemetente, final String cepRemetente, final String numeroRemetente,
			final String logradouroRemetente, final String emailRemetente, final String telefoneRemetente) {

		this.lbNome.setText(this.get(nomeDestinatario));
		this.lbCEP.setText(this.get(cepDestinatario));
		this.lbNumero.setText(this.get(numeroEndDestinatario));
		this.lbLogradouro.setText(this.get(logradouroDestinatario));
		this.lbEmail.setText(this.get(emailDestinatario));
		this.lbTelefone.setText(this.get(telefoneDestinatario));

		this.lbNomeRemetente.setText(this.get(nomeRemetente));
		this.lbCEPRemetente.setText(this.get(cepRemetente));
		this.lbNumeroRemetente.setText(this.get(numeroRemetente));
		this.lbLogradouroRemetente.setText(this.get(logradouroRemetente));
		this.lbEmailRemetente.setText(this.get(emailRemetente));
		this.lbTelefoneRemetente.setText(this.get(telefoneRemetente));
	}

	public void loadInfo() {
		
		PrePostagemRequest request = (PrePostagemRequest) Session.getSession().get(Session.PRE_POSTAGEM);
		setPrePostRemetente(request.getRemetente());
		setPrePostDestinatario(request.getObjetoPostalRequest().get(0).getDestinatario(),request.getObjetoPostalRequest().get(0).getNacionalRequest());	
		
	}
	
public void setPrePostDestinatario(DestinatarioRequest request, NacionalRequest nacionalRequest) {
		
		this.lbNomeRemetente.setText(this.get(request.getNome()));
		this.lbCEPRemetente.setText(this.get(nacionalRequest.getCep()));
		this.lbNumeroRemetente.setText(this.get(request.getNumero()));
		this.lbLogradouroRemetente.setText(this.get(request.getLogradouro()));
		this.lbEmailRemetente.setText(this.get(request.getEmail()));
		this.lbTelefoneRemetente.setText(this.get(String.valueOf(request.getCelular())));
		
	}
	
	public void setPrePostRemetente(RemetenteRequest request) {
		
		this.lbNomeRemetente.setText(this.get(request.getNome()));
		this.lbCEPRemetente.setText(this.get(request.getCep()));
		this.lbNumeroRemetente.setText(this.get(request.getNumero()));
		this.lbLogradouroRemetente.setText(this.get(request.getLogradouro()));
		this.lbEmailRemetente.setText(this.get(request.getEmail()));
		this.lbTelefoneRemetente.setText(this.get(String.valueOf(request.getCelular())));
		
	}



	public void setTitle(final String str) {
		this.lbTitle.setText(str);
	}

	@Override
	public void initialize(final URL url, final ResourceBundle resourceBundle) {}



	/*public void getPrePost() {
		this.lbNome.setText(this.get(this.prePost.getNomeDestinatario()));
		this.lbCEP.setText(this.get(this.prePost.getNumeroEndDestinatario()));
		this.lbNumero.setText(this.get(this.prePost.getLogradouroDestinatario()));
		this.lbLogradouro.setText(this.get(this.prePost.getCepDestinatario()));
		//this.lbEmail.setText(this.get(this.prePost.getTelefoneDestinatario()));
	}*/

	private String get(final String str) {
		return (str == null) ? "" : str; //$NON-NLS-1$
	}

	@Override
	public void clear() {
		if(this.lbNome != null) {this.lbNome.setText(""); this.lbNome.getStyleClass().add("label-confirmation");} //$NON-NLS-1$
		if(this.lbCEP != null)this.lbCEP.setText(""); //$NON-NLS-1$
		if(this.lbNumero != null)this.lbNumero.setText(""); //$NON-NLS-1$
		if(this.lbLogradouro != null)this.lbLogradouro.setText(""); //$NON-NLS-1$
		if(this.lbEmail != null)this.lbEmail.setText(""); //$NON-NLS-1$
	}
	
	@FXML
	@Override
	public void cancel() {
		Session.reset();		
	}
}
