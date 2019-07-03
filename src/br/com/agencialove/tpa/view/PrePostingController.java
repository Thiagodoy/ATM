package br.com.agencialove.tpa.view;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.agencia.rest.ApiException;
import br.com.agencia.rest.CorreiosPreAtendimentoApi;
import br.com.agencia.rest.CorreiosPreAtendimentoImpl;
import br.com.agencia.rest.CorreiosPreAtendimentoService;
import br.com.agencia.tpa.rest.request.DeclaracaoConteudoRequest;
import br.com.agencia.tpa.rest.request.EmiteRequest;
import br.com.agencia.tpa.rest.request.PrePostagemRequest;
import br.com.agencia.tpa.rest.request.ServicoAdicionalRequest;
import br.com.agencia.tpa.rest.response.PrePostagemResponse;
import br.com.agencialove.tpa.model.AdditionalServices;
import br.com.agencialove.tpa.model.rest.PrePost;
import br.com.agencialove.tpa.model.rest.PrePostResponse;
import br.com.agencialove.tpa.utils.Utils;
import br.com.agencialove.tpa.webservices.IWebService;
import br.com.agencialove.tpa.workflow.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PrePostingController implements IController {

	@FXML
	private TextField txtPrePost;
	
	
	@FXML
	private Button btnNext;

	private PrePost prePost;


	@FXML
	private void btnBackAction(final ActionEvent e) {
		final Scene scene = Windows.START.getScene();
		Session.setScene(scene);
	}

	@FXML
	private void btnNextAction(final ActionEvent e) {
		
		
		
		
		CorreiosPreAtendimentoApi service = Session.getCorreiosPreAtentimentoWebService();
		EmiteRequest request = new EmiteRequest();
		request.setNumeroPlp(this.txtPrePost.getText());
		
		PrePostagemResponse response = null;
		try {
			response = service.informacaoPlp(request);
		} catch (ApiException e1) {
			System.out.println(e1.getMessage());
			e1.printStackTrace();
		}
		PrePostagemRequest prePostagemRequest = new PrePostagemRequest(response);
		
		AdditionalServices services = (AdditionalServices) Session.getSession().get(Session.ADDITIONAL_SERVICES);
		
		AdditionalServices se = new AdditionalServices();
		ServicoAdicionalRequest re = prePostagemRequest.getObjetoPostalRequest().get(0).getServico();
		
		if(re.getCodigos().contains("046")){
			se.setDeliveryNotice(true);
		}
		
		if(re.getCodigos().contains("045")){
			se.setOnwHands(true);
		}
		
		if(re.getCodigos().contains("064")){
			se.setValueDeclaration(true);
			DeclaracaoConteudoRequest dc = new DeclaracaoConteudoRequest();
			dc.setTotal(re.getValor());
			Session.getSession().put(Session.CONTEUDO_DECLARADO, dc);
		}		
		
		Session.getSession().put(Session.ADDITIONAL_SERVICES, se);
		
		
		
		Session.getSession().put(Session.PRE_POSTAGEM, prePostagemRequest);
		Session.getSession().put(Session.ID_PLP, this.txtPrePost.getText());
		final Scene scene = Windows.CONFIRM_PRE_POST.getScene();

		final ConfirmPrePostController controller = (ConfirmPrePostController)scene.getUserData();
		controller.loadInfo();
		Session.setScene(scene);
	}	

	public TextField getTxtPrePost() {
		return this.txtPrePost;
	}

	public void setTxtPrePost(final TextField txtPrePost) {
		this.txtPrePost = txtPrePost;
	}




	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		
		this.txtPrePost.textProperty().addListener((event)->{
			if(this.txtPrePost.getText().length() > 0) {
				this.btnNext.setDisable(false);
			}else {
				this.btnNext.setDisable(true);
			}
		});
		this.btnNext.setDisable(true);
	}

	@Override
	public void clear() {
		this.btnNext.setDisable(true);
	}
	
	@FXML
	@Override
	public void cancel() {
		Session.reset();		
	}
}
