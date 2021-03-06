package br.com.agencialove.tpa.view;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.agencialove.tpa.model.AdditionalServices;
import br.com.agencialove.tpa.model.ZipType;
import br.com.agencialove.tpa.workflow.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class AdditionalServices1Controller implements IController {

	
	@SuppressWarnings("unused")
	private boolean chkNone = false;	
	private boolean chkOwnHand = false;	
	private boolean chkNotice = false;
	private boolean chkValueDeclaration = false;

	@FXML
	private Button btnBack;

	@FXML
	private Button btnNext;
	
	
	@FXML
	private Pane panelSemServicoAdicional;
	
	@FXML
	private Pane panelMaoPropria;
	
	@FXML
	private Pane panelAvisoRecebimento;
	
	@FXML
	private Pane panelDeclaracaoValor;	
	

	@FXML
	private void btnBackAction(final ActionEvent e) {
		final Scene scene = Windows.CONFIRM_ADDRESS.getScene();
		final ConfirmAddressController controller = (ConfirmAddressController) scene.getUserData();
		controller.setType(ZipType.SENDER);
		Session.setScene(scene);
	}

	@FXML
	private void btnNextAction(final ActionEvent e) {
		final AdditionalServices services = new AdditionalServices();
		services.setOnwHands(this.chkOwnHand);
		services.setDeliveryNotice(this.chkNotice);
		services.setValueDeclaration(this.chkValueDeclaration);
		Session.getSession().put(Session.ADDITIONAL_SERVICES, services);

		Scene scene = null;
		if(services.isValueDeclaration()) {
			scene = Windows.CONTENT_DECLARATION.getScene();
			final ContentDeclarationController controller = (ContentDeclarationController) scene.getUserData();
			controller.clear();
			controller.setBackScene(Windows.ADDITIONAL_SERVICES1.getScene());
		}else {
			scene = Windows.MEASURES.getScene();
			final MeasuresController controller = (MeasuresController)scene.getUserData();
			controller.setPreviousScene(Windows.ADDITIONAL_SERVICES1.getScene());
			controller.clear();
		}
		this.btnNext.setDisable(true);
		Session.setScene(scene);
	}

	@FXML
	public void chkNoneAction() {
	}

	@Override
	public void initialize(final URL url, final ResourceBundle resourceBundle) {		
		
		this.panelSemServicoAdicional.setOnMouseClicked((event)->{
			this.chkNone = true;	
			this.chkOwnHand = false;	
			this.chkNotice = false;
			this.chkValueDeclaration = false;
			
			this.panelMaoPropria.getStyleClass().remove("box-add-borda");
			this.panelAvisoRecebimento.getStyleClass().remove("box-add-borda");
			this.panelDeclaracaoValor.getStyleClass().remove("box-add-borda");
			this.panelSemServicoAdicional.getStyleClass().add("box-add-borda");	
			
			this.btnNext.setDisable(false);
		});
		
		this.panelMaoPropria.setOnMouseClicked((event)->{			
			boolean has = this.panelMaoPropria.getStyleClass().contains("box-add-borda");			
			this.chkOwnHand = !has;
			this.chkNone = false;
			
			if(has) {
				this.panelMaoPropria.getStyleClass().remove("box-add-borda");
			}else {
				this.panelMaoPropria.getStyleClass().add("box-add-borda");
				
			}		
			
			this.panelSemServicoAdicional.getStyleClass().remove("box-add-borda");
			this.btnNext.setDisable(false);
		});
		
		this.panelAvisoRecebimento.setOnMouseClicked((event)->{			
			boolean has = this.panelAvisoRecebimento.getStyleClass().contains("box-add-borda");			
			this.chkNotice = !has;
			this.chkNone = false;		
			
			if(has) {
				this.panelAvisoRecebimento.getStyleClass().remove("box-add-borda");
			}else {
				this.panelAvisoRecebimento.getStyleClass().add("box-add-borda");
			}			
			this.panelSemServicoAdicional.getStyleClass().remove("box-add-borda");
			this.btnNext.setDisable(false);
		});
		
		this.panelDeclaracaoValor.setOnMouseClicked((event)->{
			boolean has = this.panelDeclaracaoValor.getStyleClass().contains("box-add-borda");
			this.chkValueDeclaration = !has;
			this.chkNone = false;
			
			if(has) {
				this.panelDeclaracaoValor.getStyleClass().remove("box-add-borda");
			}else {
				this.panelDeclaracaoValor.getStyleClass().add("box-add-borda");
			}
			this.panelSemServicoAdicional.getStyleClass().remove("box-add-borda");	
			this.btnNext.setDisable(false);
		});
		
		this.btnNext.setDisable(true);
		
		
	}

	public void loadValues() {
//		final AdditionalServices services = (AdditionalServices) Session.getSession().get(Session.ADDITIONAL_SERVICES);
//		if(services != null){
//			this.chkOwnHand.setSelected(services.isOnwHands());
//			this.chkNotice.setSelected(services.isDeliveryNotice());
//			this.chkValueDeclaration.setSelected(services.isValueDeclaration());
//		}else {
//			this.chkOwnHand.setSelected(false);
//			this.chkNotice.setSelected(false);
//			this.chkValueDeclaration.setSelected(false);
//		}
	}

	@Override
	public void clear() {
		this.chkNone = true;	
		this.chkOwnHand = false;	
		this.chkNotice = false;
		this.chkValueDeclaration = false;
		
		this.panelMaoPropria.getStyleClass().remove("box-add-borda");
		this.panelAvisoRecebimento.getStyleClass().remove("box-add-borda");
		this.panelDeclaracaoValor.getStyleClass().remove("box-add-borda");
		this.panelSemServicoAdicional.getStyleClass().remove("box-add-borda");
		
		this.btnNext.setDisable(true);
	}

	@FXML
	@Override
	public void cancel() {
		Session.reset();		
	}

}
