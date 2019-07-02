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
import br.com.agencialove.tpa.dao.PostagemDao;
import br.com.agencialove.tpa.hardware.IPrinterService;
import br.com.agencialove.tpa.hardware.ITEFService;
import br.com.agencialove.tpa.hardware.ITEFService.TEFCallback;
import br.com.agencialove.tpa.model.AdditionalServices;
import br.com.agencialove.tpa.model.Address;
import br.com.agencialove.tpa.model.Agencia;
import br.com.agencialove.tpa.model.OverAttemptsType;
import br.com.agencialove.tpa.model.Pack;
import br.com.agencialove.tpa.model.PackageMeasures;
import br.com.agencialove.tpa.model.PaymentData;
import br.com.agencialove.tpa.model.PaymentResult;
import br.com.agencialove.tpa.model.Postagem;
import br.com.agencialove.tpa.model.Relatorio;
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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PaymentController implements IController {

	public static final int MAX_PAYMENT_TRY = 5;
	private String value;
	private String code;
	private String description;

	@FXML
	private ImageView imgInstruction;

	@FXML
	private Label lblInitial;

	@FXML
	private Label lblRetry;

	@FXML
	private Label lblCharging;

	@FXML
	private Button btnYes;

	@FXML
	private Button btnNo;

	@FXML
	private StackPane stake;

	private Scene nextScene;

	private int tryCounter;
	private boolean canceled;
	private Address sender;
	private Address receiver;
	private AdditionalServices services;
	private PackageMeasures measures;
	private ServicesResponse selectedService;

	@FXML
	private void btnYesAction(final ActionEvent e) {

		final Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		final SessionType type = (SessionType) Session.getSession().get(Session.SESSION_TYPE);
		switch (type) {
		case SERVICE:
			this.nextScene = Windows.REMOVE_STICK.getScene();
			break;
		case PACKAGE:
			this.nextScene = Windows.REMOVE_PACKAGE.getScene();
			Pack pack = (Pack) Session.getSession().get(Session.SELECTED_PACKAGE);
			ServicesResponse selectedService = new ServicesResponse();
			selectedService.setCodigoServico("04022");
			Double valor = Utils.parse(pack.getValor());
			Long qtd = Long.valueOf(pack.getQuantidade());
			Double valorTotal = (valor * qtd);
			selectedService.setValor(valorTotal.toString().replace(",", "."));
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

		System.out.println("Codigo serviço: " + selectedService.getCodigoServico());

		this.startPaymentProcess(selectedService.getValor(), selectedService.getCodigoServico(),
				selectedService.getDescricaoServico(), stage);

	}

	@FXML
	private void btnNoAction(final ActionEvent e) {

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
			final PrePost req = webService.getPrePostRequest(PaymentController.this.sender,
					PaymentController.this.receiver, PaymentController.this.services, PaymentController.this.measures,
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

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {

	}

	private void startPaymentProcess(final String pValue, final String pCode, final String pDescription,
			final Stage stage) {
		this.value = pValue;
		this.code = pCode;
		this.description = pDescription;

		this.setChargingView();

		final ITEFService tefService = Session.getTEFService();

		tefService.charge(this.value, this.code, this.description, new PaymentTEFCallback(stage));
	}

	@Override
	public void clear() {
		this.value = null;
		this.code = null;
		this.description = null;		
		this.setDefaultView(false);
	}

	private void setDefaultView(final boolean retry) {
		if (this.btnNo != null)
			this.btnNo.setVisible(true);
		if (this.btnYes != null)
			this.btnYes.setVisible(true);
		if (this.btnNo != null)
			this.btnNo.setDisable(false);
		if (this.btnYes != null)
			this.btnYes.setDisable(false);
		if (this.lblInitial != null)
			this.lblCharging.setVisible(!retry);
		if (this.lblCharging != null)
			this.lblCharging.setVisible(false);
		if (this.lblRetry != null)
			this.lblRetry.setVisible(retry);
		if (this.imgInstruction != null)
			this.imgInstruction.setVisible(!retry);
	}

	public void setChargingView() {
		if (this.btnNo != null)
			this.btnNo.setVisible(false);
		if (this.btnYes != null)
			this.btnYes.setVisible(false);
		if (this.lblInitial != null)
			this.lblInitial.setVisible(false);
		if (this.lblCharging != null)
			this.lblCharging.setVisible(true);
		if (this.lblRetry != null)
			this.lblRetry.setVisible(false);
		if (this.imgInstruction != null)
			this.imgInstruction.setVisible(false);
	}

	public void chargeSuccess(final PaymentData paymentData, final String toPrint, final Stage stage) {
		final IPrinterService printerService = Session.getPrinterService();

		// imprime recibo do TEF
		// TODO exitir alert e continuar
		try {
			printerService.printTicket(toPrint);
		} catch (final PrintException e) {
			
		}	

		
		Agencia agencia = AgenciaDao.getAgencia();
		final SessionType type = (SessionType) Session.getSession().get(Session.SESSION_TYPE);

		switch (type) {
		case PACKAGE:				
			break;
		case PRE_POSTING:
		case SERVICE:
			// gera PLP [IWebService.serviceRegisterResponse]
			final IWebService webService = Session.getWebService();
			final PrePost req = webService.getPrePostRequest(PaymentController.this.sender,
					PaymentController.this.receiver, PaymentController.this.services, PaymentController.this.measures,
					PaymentController.this.selectedService);
			final PrePostResponse resp = webService.serviceRegisterResponse(req);

			final String plp = resp.getNumeroPLP();
			final String nEtq = resp.getNumeroEtiqueta();

			Session.getSession().put(Session.ID_PLP, plp);

			ServicesResponse service = (ServicesResponse) Session.getSession().get(Session.SELECTED_SERVICE);

//			
//			Relatorio relatorio = new Relatorio(this.sender, this.receiver, this.services, this.measures, service,
//					agencia, paymentData, nEtq, plp);

//			// Gera um relatorio
//			this.persistInfo(relatorio);
//
//			// prepara objeto para as requisições ao WebService
//			final EmiteEtiquetaRequest eer = new EmiteEtiquetaRequest();
//			eer.setNumeroPLP(plp);
//			eer.setNumeroEtiqueta(nEtq);
//
//			// emite etiqueta [IWebService.getPdfBytesEmiteEtiqueta]
//			final byte[] lbl = webService.getPdfBytesEmiteEtiqueta(eer);
//			// TODO exitir alert e continuar
//			try {
//				printerService.printLabel(lbl);
//			} catch (final PrintException e) {
//				e.printStackTrace();
//			}			
//
//			// imprime aviso de recebimento
//			if (this.services.isDeliveryNotice()) {
//				webService.getPdfBytesAvisoRecebimento(eer);
//			}
//
//			// imprime declaração de conteudo
//			if (this.services.isValueDeclaration()) {
//				webService.getPdfBytesDeclaracaoDeConteudo(eer);
//			}
			break;

		default:
			break;
		}

		Platform.runLater(() -> {
			Session.setScene(this.nextScene);
		});

		this.setDefaultView(false);

	}

	private void persistInfo(Relatorio relatorio) {

		final SessionType type = (SessionType) Session.getSession().get(Session.SESSION_TYPE);
		switch (type) {
		case SERVICE:
			Postagem postagem = new Postagem(relatorio.getSender(), relatorio.getReceiver(),
					relatorio.getAdditionalServices(), relatorio.getMeasures(), relatorio.getServicesResponse(),
					relatorio.getAgencia(), relatorio.getPaymentData());
			postagem.setCodigoRastreio(relatorio.getEtiqueta());
			postagem.setNumeroPlp(relatorio.getPlp());
			PostagemDao.save(postagem);

			break;		
		}

	}

	private class PaymentTEFCallback implements TEFCallback {

		private final Stage stage;

		public PaymentTEFCallback(final Stage pStage) {
			this.stage = pStage;
		}

		@Override
		public void charged(final PaymentResult result, final PaymentData paymentData, final String toPrint) {

			if (result == PaymentResult.SUCCESS) {
				PaymentController.this.setDefaultView(false);
				PaymentController.this.chargeSuccess(paymentData, toPrint, this.stage);
			} else {
				++PaymentController.this.tryCounter;
				if (PaymentController.this.tryCounter > PaymentController.MAX_PAYMENT_TRY) {
					Session.overAttempts(OverAttemptsType.PAYMENT);
				} else if (!PaymentController.this.canceled) {
					PaymentController.this.setDefaultView(true);
					final ITEFService tefService = Session.getTEFService();
					tefService.charge(PaymentController.this.value, PaymentController.this.code,
							PaymentController.this.description, this);
				}
			}
		}

		@Override
		public void error(final Exception e) {
			Logger.getGlobal().log(Level.SEVERE, Messages.getString("PaymentController.0"), e); //$NON-NLS-1$
			Session.error();
		}

		@Override
		public void timeout() {
		}

	}
	
	@FXML
	@Override
	public void cancel() {
		Session.reset();		
	}
}