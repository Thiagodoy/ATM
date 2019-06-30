package br.com.agencialove.tpa.view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.agencia.rest.CorreiosPreAtendimentoApi;
import br.com.agencia.rest.CorreiosPreAtendimentoImpl;
import br.com.agencia.tpa.rest.request.DeclaracaoConteudoRequest;
import br.com.agencia.tpa.rest.request.DestinatarioRequest;
import br.com.agencia.tpa.rest.request.PrePostagemRequest;
import br.com.agencia.tpa.rest.request.PrecoPrazoRequest;
import br.com.agencia.tpa.rest.request.RemetenteRequest;
import br.com.agencia.tpa.rest.request.ServicoAdicionalRequest;
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
import javafx.scene.layout.Pane;

public class MeasuresController implements IController {

	@FXML
	private Button btnBack;

	@FXML
	private Button btnNext;

	 @FXML
	private Button btnMeasures; 

	@FXML
	private Pane btnPackageP;

	@FXML
	private Pane btnPackageM;

	@FXML
	private Pane btnPackageG;

	@FXML
	private Pane btnPackageE;

	private PackageMeasures measures;
	private ObjectType objectType;
	private Scene previousScene;

	@FXML
	private void btnBackAction(final ActionEvent e) {

		if (this.previousScene == Windows.ADDITIONAL_SERVICES2.getScene()) {
			final AdditionalServices2Controller controller = (AdditionalServices2Controller) this.previousScene
					.getUserData();
			controller.loadValues();
		}

		Session.setScene(this.previousScene);
	}

//	@FXML
//	private void btnPackageAction(final ActionEvent e) {
//		if (this.btnPackageP.isArmed()) {
//			this.objectType = ObjectType.SMALL;
//			final String dimensions = Configuration.getConfigurations().get("package.template.small");
//			Session.getHardwareService().setVolumeMeasured(dimensions);
//			this.setSelectedButton(this.btnPackageP);
//		}
//
//		else if (this.btnPackageM.isArmed()) {
//			this.objectType = ObjectType.MID;
//			final String dimensions = Configuration.getConfigurations().get("package.template.mid");
//			Session.getHardwareService().setVolumeMeasured(dimensions);
//			this.setSelectedButton(this.btnPackageM);
//		}
//
//		else if (this.btnPackageG.isArmed()) {
//			this.objectType = ObjectType.BIG;
//			final String dimensions = Configuration.getConfigurations().get("package.template.big");
//			Session.getHardwareService().setVolumeMeasured(dimensions);
//			this.setSelectedButton(this.btnPackageG);
//		}
//
//		if (this.objectType != null)
//			this.btnMeasures.setDisable(false);
//	}

	private void setSelectedButton(final Pane armed) {
		final Pane[] buttons = new Pane[4];
		buttons[0] = this.btnPackageP;
		buttons[1] = this.btnPackageM;
		buttons[2] = this.btnPackageG;
		buttons[3] = this.btnPackageE;

		for (final Pane b : buttons)
			if (b.equals(armed))
				b.getStyleClass().add("add-border-caixa");
			else
				b.getStyleClass().remove("add-border-caixa");
	}

	@FXML
	private void btnNextAction(final ActionEvent e) {

		this.measures.setType(Integer.toString(this.objectType.ordinal()));
		AdditionalServices services = (AdditionalServices) Session.getSession().get(Session.ADDITIONAL_SERVICES);
		PrePostagemRequest prePostagemRequest = (PrePostagemRequest) Session.getSession().get(Session.PRE_POSTAGEM);

		// seta o peso do objeto
		double peso = Utils.parse(this.measures.getWeight());
		prePostagemRequest.getObjetoPostalRequest().get(0).setPeso(peso);

		RemetenteRequest remetente = prePostagemRequest.getRemetente();
		DestinatarioRequest destinatario = prePostagemRequest.getObjetoPostalRequest().get(0).destinatario;

		DeclaracaoConteudoRequest declaracaoConteudoRequest = (DeclaracaoConteudoRequest) Session.getSession()
				.get(Session.CONTEUDO_DECLARADO);
		String value = "0";

		if (services.isValueDeclaration()) {
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

		// Adiciona os Serviços adicionais
		ServicoAdicionalRequest servicosAdicionais = new ServicoAdicionalRequest();

		if (precoPrazoRequest.getMaoPropria().contentEquals("S")) {
			servicosAdicionais.getCodigos().add("045");
		}
		if (precoPrazoRequest.getAvisoRecebimento().contentEquals("S")) {
			servicosAdicionais.getCodigos().add("046");
		}
		if (precoPrazoRequest.getValorDeclarado() != null && !precoPrazoRequest.getValorDeclarado().equals("0")) {
			servicosAdicionais.setValor(Utils.parse(precoPrazoRequest.getValorDeclarado()));
			servicosAdicionais.getCodigos().add("064");
		}

		prePostagemRequest.getObjetoPostalRequest().get(0).setServico(servicosAdicionais);

		CorreiosPreAtendimentoApi correiosApi = Session.getCorreiosPreAtentimentoWebService();
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
		if (this.objectType != null) {
			try {
				this.measures = Session.getHardwareService().getMeasures();
			} catch (final Exception ioe) {
				Logger.getAnonymousLogger().log(Level.SEVERE, Messages.getString("MeasuresController.4"), ioe); //$NON-NLS-1$
				Session.error();
			}
		}

		Session.getSession().put(Session.MEASURES, this.measures);

		if (this.objectType != null)
			this.btnNext.setDisable(false);
	}

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {

		
		btnPackageP.setOnMouseClicked((event)->{
			final String dimensions = Configuration.getConfigurations().get("package.template.small");
			Session.getHardwareService().setVolumeMeasured(dimensions);
			this.objectType = ObjectType.SMALL;
			this.setSelectedButton(btnPackageP);
		});
		
		btnPackageM.setOnMouseClicked((event)->{
			final String dimensions = Configuration.getConfigurations().get("package.template.mid");
			Session.getHardwareService().setVolumeMeasured(dimensions);
			this.objectType = ObjectType.MID;
			this.setSelectedButton(btnPackageM);
			this.btnMeasures.setDisable(false);
		});
		
		btnPackageG.setOnMouseClicked((event)->{
			final String dimensions = Configuration.getConfigurations().get("package.template.big");
			Session.getHardwareService().setVolumeMeasured(dimensions);
			this.objectType = ObjectType.BIG;
			this.setSelectedButton(btnPackageG);
			this.btnMeasures.setDisable(false);
		});
		
		btnPackageE.setOnMouseClicked((event)->{
			final String dimensions = Configuration.getConfigurations().get("package.template.env");
			Session.getHardwareService().setVolumeMeasured(dimensions);
			this.objectType = ObjectType.ENVELOPE;
			this.setSelectedButton(btnPackageE);
			this.btnMeasures.setDisable(false);
		});
		
		
		
		
	}

	@Override
	public void clear() {
		if (this.measures != null)
			this.measures = null;
		if (this.objectType != null)
			this.objectType = null;
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
	
	@FXML
	@Override
	public void cancel() {
		Session.reset();		
	}
	
	
}
