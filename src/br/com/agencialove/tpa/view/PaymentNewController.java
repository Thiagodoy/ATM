package br.com.agencialove.tpa.view;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.print.PrintException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import br.com.agencialove.tpa.Messages;
import br.com.agencialove.tpa.dao.AgenciaDao;
import br.com.agencialove.tpa.dao.EmbalagemDao;
import br.com.agencialove.tpa.dao.PostagemDao;
import br.com.agencialove.tpa.hardware.IPrinterService;
import br.com.agencialove.tpa.hardware.ITEFService;
import br.com.agencialove.tpa.hardware.ITEFService.TEFCallback;
import br.com.agencialove.tpa.model.AdditionalServices;
import br.com.agencialove.tpa.model.Address;
import br.com.agencialove.tpa.model.Agencia;
import br.com.agencialove.tpa.model.Embalagem;
import br.com.agencialove.tpa.model.OverAttemptsType;
import br.com.agencialove.tpa.model.Pack;
import br.com.agencialove.tpa.model.PackageMeasures;
import br.com.agencialove.tpa.model.PaymentData;
import br.com.agencialove.tpa.model.PaymentResult;
import br.com.agencialove.tpa.model.Postagem;
import br.com.agencialove.tpa.model.Relatorio;
import br.com.agencialove.tpa.model.Relatorio.RelatorioType;
import br.com.agencialove.tpa.model.rest.EmiteEtiquetaRequest;
import br.com.agencialove.tpa.model.rest.PrePost;
import br.com.agencialove.tpa.model.rest.PrePostResponse;
import br.com.agencialove.tpa.model.rest.ServicesResponse;
import br.com.agencialove.tpa.utils.Utils;
import br.com.agencialove.tpa.webservices.IWebService;
import br.com.agencialove.tpa.workflow.Session;
import br.com.agencialove.tpa.workflow.SessionType;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PaymentNewController implements IController {	
	
	@FXML
	private Pane  panelProsseguir;
	@FXML
	private Pane panelSucesso;
	@FXML
	private Pane panelErro;
	@FXML
	private Pane panelLoading ;	
	
	@FXML
	private Button btnErroProsseguir;
	
	@FXML
	private Button btnErroCancelar;
	
	@FXML
	private Button btnProsseguir;
	
	@FXML
	private Button btnCancelar;
	
	@FXML
	private StackPane stake;	
	
	@FXML
	private  ProgressBar progressBar;
	
	private Scene nextScene;
	private Address sender;
	private Address receiver;
	private AdditionalServices services;
	private PackageMeasures measures;
	private ServicesResponse selectedService;
	public static final int MAX_PAYMENT_TRY = 5;
	private String value;
	private String code;
	private String description;
	private int tryCounter;
	private boolean canceled;
	
	public enum StageStatus{LOADING,PAYMENT,ERROR,SUCCESS}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {		
		
		
		
		this.btnCancelar.setOnAction((event)->{
			this.cancelar(event);
		});
		
		this.btnErroCancelar.setOnAction((event)->{
			JFXButton buttonYes = new JFXButton("Sim");
			JFXButton buttonNo = new JFXButton("Não");

			// :FIXME Alinhar com o Luis Para definir as classes dos botoes
			// buttonNo.setStyle("");
			// buttonYes.setStyle("");

			JFXDialogLayout layout = new JFXDialogLayout();
			layout.setBody(new Text("Deseja realmente cancelar a operação ?"));
			JFXDialog dialog = new JFXDialog(stake, layout, JFXDialog.DialogTransition.TOP);
			buttonYes.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, (e) -> {
				dialog.close();
				Session.reset();
			});

			buttonNo.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, (e) -> {
				dialog.close();
			});

			layout.setActions(buttonNo, buttonYes);
			dialog.setFocusTraversable(true);
			dialog.show();
		});
		
		this.btnErroProsseguir.setOnAction((event)->{
			this.prosseguir(event);
		});
		
		this.btnProsseguir.setOnAction((event)->{
			this.prosseguir(event);
		});
		
		
	}

	@Override
	public void clear() {		
		this.changeStage(StageStatus.PAYMENT);
		this.progressBar.setProgress(0.0);
	}
	
	private void cancelar(final ActionEvent e) {

		final SessionType type = (SessionType) Session.getSession().get(Session.SESSION_TYPE);

		switch (type) {
		case PACKAGE:

			JFXButton buttonYes = new JFXButton("Sim");
			JFXButton buttonNo = new JFXButton("Não");

			// :FIXME Alinhar com o Luis Para definir as classes dos botoes
			// buttonNo.setStyle("");
			// buttonYes.setStyle("");

			JFXDialogLayout layout = new JFXDialogLayout();
			layout.setBody(new Text("Deseja realmente cancelar a operação ?"));
			JFXDialog dialog = new JFXDialog(stake, layout, JFXDialog.DialogTransition.TOP);
			buttonYes.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, (event) -> {
				dialog.close();
				Session.reset();
			});

			buttonNo.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, (event) -> {
				dialog.close();
			});

			layout.setActions(buttonNo, buttonYes);
			dialog.setFocusTraversable(false);
			dialog.show();

			break;
		case PRE_POSTING:
		case SERVICE:

			this.sender = (Address) Session.getSession().get(Session.SENDER_ADDRESS);
			this.receiver = (Address) Session.getSession().get(Session.RECEIVER_ADDRESS);
			this.services = (AdditionalServices) Session.getSession().get(Session.ADDITIONAL_SERVICES);
			this.measures = (PackageMeasures) Session.getSession().get(Session.MEASURES);
			final ServicesResponse selectedService = (ServicesResponse) Session.getSession()
					.get(Session.SELECTED_SERVICE);

			// gera PLP [IWebService.serviceRegisterResponse]
			final IWebService webService = Session.getWebService();
			final PrePost req = webService.getPrePostRequest(PaymentNewController.this.sender,
																PaymentNewController.this.receiver, 
																PaymentNewController.this.services, 
																PaymentNewController.this.measures,
																selectedService);

			final PrePostResponse resp = webService.serviceRegisterResponseUnpaid(req);

			final String plp = resp.getNumeroPLP();
			Session.getSession().put(Session.ID_PLP, plp);

			this.nextScene = Windows.PRE_POSTAGEM_CONFIRMATION_CONTROLLER.getScene();
			final PrePostagemConfirmationController controller = (PrePostagemConfirmationController) this.nextScene
					.getUserData();
			controller.clear();
			Session.setScene(this.nextScene);

			break;

		default:
			break;
		}

	}
	
	
	public void prosseguir(final ActionEvent e) {
		
		final Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		final SessionType type = (SessionType) Session.getSession().get(Session.SESSION_TYPE);
		
		switch (type) {
			case SERVICE:
				this.nextScene = Windows.REMOVE_STICK.getScene();
				break;
			case PACKAGE:
				this.nextScene = Windows.SEND_NFE_EMAIL.getScene();
				Pack pack = (Pack) Session.getSession().get(Session.SELECTED_PACKAGE);
				ServicesResponse selectedService = new ServicesResponse();
				selectedService.setCodigoServico("04022");
				Double valor = Utils.parse(pack.getValor());
				selectedService.setValor(Utils.formatCurrency(valor));
				selectedService.setDescricaoServico("Venda de embalagem");
				Session.getSession().put(Session.SELECTED_SERVICE, selectedService);
				break;
			case PRE_POSTING:
				this.nextScene = Windows.REMOVE_STICK.getScene();
				break;
		}

		this.sender = (Address) Session.getSession().get(Session.SENDER_ADDRESS);
		this.receiver = (Address) Session.getSession().get(Session.RECEIVER_ADDRESS);
		this.services = (AdditionalServices) Session.getSession().get(Session.ADDITIONAL_SERVICES);
		this.measures = (PackageMeasures) Session.getSession().get(Session.MEASURES);
		final ServicesResponse selectedService = (ServicesResponse) Session.getSession().get(Session.SELECTED_SERVICE);		
		this.selectedService = selectedService;
		
		this.changeStage(StageStatus.LOADING);		
		
		this.startPaymentProcess(selectedService.getValor(), selectedService.getCodigoServico(),
			selectedService.getDescricaoServico(), stage);		
		
	}
	
	public void changeStage(StageStatus stage) {
			
		
		switch (stage) {
		case LOADING:
			
			this.panelProsseguir.setVisible(false);
			this.panelSucesso.setVisible(false);
			this.panelErro.setVisible(false);
			this.panelLoading.setVisible(true);	
			this.progressBar.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
			break;
		case ERROR:			
			this.panelProsseguir.setVisible(false);
			this.panelSucesso.setVisible(false);
			this.panelErro.setVisible(true);
			this.panelLoading.setVisible(false);
			
			break;
		case PAYMENT:
			this.panelProsseguir.setVisible(true);
			this.panelSucesso.setVisible(false);
			this.panelErro.setVisible(false);
			this.panelLoading.setVisible(false);
			
			break;
		case SUCCESS:
			this.panelProsseguir.setVisible(false);
			this.panelSucesso.setVisible(true);
			this.panelErro.setVisible(false);
			this.panelLoading.setVisible(false);
			
			break;
		}
		
		
	}
	
	private void startPaymentProcess(final String pValue, final String pCode, final String pDescription,
			final Stage stage) {
		
		this.value = pValue;
		this.code = pCode;
		this.description = pDescription;		
		this.changeStage(StageStatus.LOADING);
		final ITEFService tefService = Session.getTEFService();		
		tefService.charge(this.value, this.code, this.description, new PaymentTEFCallback(stage));
	}
	
	public void chargeSuccess(final PaymentData paymentData, final String toPrint, final Stage stage) {
		
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		final IPrinterService printerService = Session.getPrinterService();

		// imprime recibo do TEF
		// TODO exitir alert e continuar	
		
		try {
			printerService.printTicket(toPrint);
		} catch (final PrintException e) {
			
		}	

		Relatorio relatorio = null;
		Agencia agencia = AgenciaDao.getAgencia();
		final SessionType type = (SessionType) Session.getSession().get(Session.SESSION_TYPE);

		switch (type) {
		case PACKAGE:				
			relatorio = new Relatorio();
			relatorio.setType(RelatorioType.EMBALAGEM);
			relatorio.setSender(this.sender);
			relatorio.setAgencia(agencia);
			relatorio.setPaymentData(paymentData);
			break;
		case PRE_POSTING:
		case SERVICE:
			// gera PLP [IWebService.serviceRegisterResponse]
			final IWebService webService = Session.getWebService();
			final PrePost req = webService.getPrePostRequest(
					PaymentNewController.this.sender,
					PaymentNewController.this.receiver, 
					PaymentNewController.this.services, 
					PaymentNewController.this.measures,
					PaymentNewController.this.selectedService);
			
			 PrePostResponse resp = null;
			try {
				 resp = webService.serviceRegisterResponse(req);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//final PrePostResponse resp = webService.serviceRegisterResponse(req);

			final String plp = resp.getNumeroPLP();
			final String nEtq = resp.getNumeroEtiqueta();

			Session.getSession().put(Session.ID_PLP, plp);

			ServicesResponse service = (ServicesResponse) Session.getSession().get(Session.SELECTED_SERVICE);
			
			relatorio = new Relatorio();
			relatorio.setType(RelatorioType.POSTAGEM);
			relatorio.setSender(this.sender); 
			relatorio.setReceiver(this.receiver); 
			relatorio.setAdditionalServices(this.services); 
			relatorio.setMeasures(this.measures);
			relatorio.setServicesResponse(service);
			relatorio.setAgencia(agencia);
			relatorio.setPaymentData(paymentData);
			relatorio.setEtiqueta(nEtq);
			relatorio.setPlp(plp);			

			// prepara objeto para as requisições ao WebService
			final EmiteEtiquetaRequest eer = new EmiteEtiquetaRequest();
			eer.setNumeroPLP(plp);
			eer.setNumeroEtiqueta(nEtq);

			// emite etiqueta [IWebService.getPdfBytesEmiteEtiqueta]
			final byte[] lbl = webService.getPdfBytesEmiteEtiqueta(eer);
			// TODO exitir alert e continuar
			try {
				printerService.printLabel(lbl);
			} catch (final PrintException e) {
				e.printStackTrace();
			}			

			// imprime aviso de recebimento
			if (this.services.isDeliveryNotice()) {
				webService.getPdfBytesAvisoRecebimento(eer);
			}

			// imprime declaração de conteudo
			if (this.services.isValueDeclaration()) {
				webService.getPdfBytesDeclaracaoDeConteudo(eer);
			}
			break;

		default:
			break;
		}
		
		Session.getSession().put(Session.RELATORIO, relatorio);
		
		

		Platform.runLater(() -> {
			this.changeStage(StageStatus.PAYMENT);
			Session.setScene(this.nextScene);
			
		});

		

	}
	
	private class PaymentTEFCallback implements TEFCallback {

		private final Stage stage;

		public PaymentTEFCallback(final Stage pStage) {
			this.stage = pStage;
		}

		@Override
		public void charged(final PaymentResult result, final PaymentData paymentData, final String toPrint) {

			if (result == PaymentResult.SUCCESS) {
				
				PaymentNewController.this.changeStage(StageStatus.SUCCESS);
				PaymentNewController.this.chargeSuccess(paymentData, toPrint, this.stage);				
			} else {
				++PaymentNewController.this.tryCounter;
				if (PaymentNewController.this.tryCounter > PaymentNewController.MAX_PAYMENT_TRY) {
					Session.overAttempts(OverAttemptsType.PAYMENT);
				} else if (!PaymentNewController.this.canceled) {
					//PaymentNewController.this.setDefaultView(true);
					final ITEFService tefService = Session.getTEFService();
					tefService.charge(PaymentNewController.this.value, PaymentNewController.this.code,
							PaymentNewController.this.description, this);
				}
			}
		}

		@Override
		public void error(final Exception e) {
			Logger.getGlobal().log(Level.SEVERE, Messages.getString("PaymentController.0"), e); //$NON-NLS-1$			
			PaymentNewController.this.changeStage(StageStatus.ERROR);			
			//Session.error();
		}

		@Override
		public void timeout() {
		}

	}
	
	private void sleep(long sleep) {
		try {
			Thread.sleep(sleep);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	

}
