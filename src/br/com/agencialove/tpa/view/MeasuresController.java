package br.com.agencialove.tpa.view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.agencia.rest.CorreiosPreAtendimentoImpl;
import br.com.agencia.tpa.rest.request.DeclaracaoConteudoRequest;
import br.com.agencia.tpa.rest.request.DestinatarioRequest;
import br.com.agencia.tpa.rest.request.PrePostagemRequest;
import br.com.agencia.tpa.rest.request.PrecoPrazoRequest;
import br.com.agencia.tpa.rest.request.RemetenteRequest;
import br.com.agencia.tpa.rest.response.PrecoPrazoResponse;
import br.com.agencialove.tpa.Configuration;
import br.com.agencialove.tpa.Messages;
import br.com.agencialove.tpa.model.AdditionalServices;
import br.com.agencialove.tpa.model.ObjectType;
import br.com.agencialove.tpa.model.PackageMeasures;
import br.com.agencialove.tpa.utils.Utils;
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
		
		this.measures.setType(Integer.toString(this.objectType.ordinal()));	
		AdditionalServices services = (AdditionalServices) Session.getSession().get(Session.ADDITIONAL_SERVICES);		
		PrePostagemRequest prePostagemRequest = (PrePostagemRequest) Session.getSession().get(Session.PRE_POSTAGEM);
		RemetenteRequest remetente = prePostagemRequest.getRemetenteRequest();
		DestinatarioRequest destinatario = prePostagemRequest.getObjetoPostalRequest().get(0).destinatario;
		
		DeclaracaoConteudoRequest declaracaoConteudoRequest = (DeclaracaoConteudoRequest) Session.getSession().get(Session.CONTEUDO_DECLARADO);
		String value = "0";
		
		if(services.isValueDeclaration()) {
			value = Utils.format(declaracaoConteudoRequest.getTotal());
		}
		
		PrecoPrazoRequest precoPrazoRequest = new PrecoPrazoRequest();
		precoPrazoRequest.setCepDestino(destinatario.getCep());
		precoPrazoRequest.setCepOrigem(remetente.getCep());
		precoPrazoRequest.setPeso(this.measures.getWeight());
		precoPrazoRequest.setFormato("1");
		precoPrazoRequest.setComprimento(this.measures.getDepth());
		precoPrazoRequest.setAltura(this.measures.getHeight());
		precoPrazoRequest.setLargura(this.measures.getWidth());
		precoPrazoRequest.setDiametro(this.measures.getDiameter());
		precoPrazoRequest.setMaoPropria(services.isOnwHands() ? "S" : "N"); 
		precoPrazoRequest.setValorDeclarado(value); 
		precoPrazoRequest.setAvisoRecebimento(services.isDeliveryNotice() ? "S" : "N"); 
		
		CorreiosPreAtendimentoImpl correiosApi = Session.getCorreiosPreAtentimentoWebService();
		List<PrecoPrazoResponse> list = correiosApi.servicosDisponiveis(precoPrazoRequest);
		
		
		Session.getSession().put(Session.AVAILABLE_SERVICES, list);
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
