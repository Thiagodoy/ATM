package br.com.agencialove.tpa.view;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.agencialove.tpa.Messages;
import br.com.agencialove.tpa.hardware.ITEFService;
import br.com.agencialove.tpa.hardware.ITEFService.TEFCallback;
import br.com.agencialove.tpa.model.AdditionalServices;
import br.com.agencialove.tpa.model.Address;
import br.com.agencialove.tpa.model.OverAttemptsType;
import br.com.agencialove.tpa.model.Pack;
import br.com.agencialove.tpa.model.PackageMeasures;
import br.com.agencialove.tpa.model.PaymentData;
import br.com.agencialove.tpa.model.PaymentResult;
import br.com.agencialove.tpa.model.rest.ServicesResponse;
import br.com.agencialove.tpa.utils.Utils;
import br.com.agencialove.tpa.view.PaymentController.PaymentTEFCallback;
import br.com.agencialove.tpa.workflow.Session;
import br.com.agencialove.tpa.workflow.SessionType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
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
	private ProgressBar progressBar;
	
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
	
	public enum StageStatus{LOADING,PAYMENT,ERROR,SUCCESS}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {		
		progressBar.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
	}

	@Override
	public void clear() {

	}
	
	
	
	@FXML
	public void btnYesAction(final ActionEvent e) {
		
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
				selectedService.setValor(valor.toString().replace(",", "."));
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
	
	public void changeStage(StageStatus stage) {
		
		
		
		switch (stage) {
		case LOADING:
			
			this.panelProsseguir.setVisible(false);
			this.panelSucesso.setVisible(false);
			this.panelErro.setVisible(false);
			this.panelLoading.setVisible(true);			
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
	
	private class PaymentTEFCallback implements TEFCallback {

		private final Stage stage;

		public PaymentTEFCallback(final Stage pStage) {
			this.stage = pStage;
		}

		@Override
		public void charged(final PaymentResult result, final PaymentData paymentData, final String toPrint) {

			if (result == PaymentResult.SUCCESS) {
				//PaymentController.this.setDefaultView(false);
				//PaymentController.this.chargeSuccess(paymentData, toPrint, this.stage);
			} else {
//				++PaymentController.this.tryCounter;
//				if (PaymentController.this.tryCounter > PaymentController.MAX_PAYMENT_TRY) {
//					Session.overAttempts(OverAttemptsType.PAYMENT);
//				} else if (!PaymentController.this.canceled) {
//					PaymentController.this.setDefaultView(true);
//					final ITEFService tefService = Session.getTEFService();
//					tefService.charge(PaymentController.this.value, PaymentController.this.code,
//							PaymentController.this.description, this);
//				}
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

	

}
