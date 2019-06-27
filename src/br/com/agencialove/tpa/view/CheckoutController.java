package br.com.agencialove.tpa.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.com.agencia.tpa.rest.request.DestinatarioRequest;
import br.com.agencia.tpa.rest.request.PrePostagemRequest;
import br.com.agencia.tpa.rest.request.RemetenteRequest;
import br.com.agencia.tpa.rest.response.PrecoPrazoResponse;
import br.com.agencialove.tpa.model.AdditionalServices;
import br.com.agencialove.tpa.model.PackageMeasures;
import br.com.agencialove.tpa.model.Resume;
import br.com.agencialove.tpa.workflow.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class CheckoutController implements IController {

	@FXML
	private Button btnBack;

	@FXML
	private Button btnNext;

	@SuppressWarnings("rawtypes")
	@FXML
	private TableView tableView;

	@FXML
	private void btnBackAction(final ActionEvent e) {
		final Scene scene = Windows.SELECT_SERVICE.getScene();
		Session.setScene(scene);
	}

	@FXML
	private void btnNextAction(final ActionEvent e) {
		final Scene scene = Windows.PAYMENT_NEW.getScene();
		final PaymentNewController controller = (PaymentNewController) scene.getUserData();
		controller.clear();		
		Session.setScene(scene);
	}


	@Override
	public void initialize(final URL url, final ResourceBundle resourceBundle) {

	}

	@SuppressWarnings("unchecked")
	public void loadInfo() {
		final List<Resume> itens = new ArrayList<>();
		
		PrePostagemRequest request = (PrePostagemRequest) Session.getSession().get(Session.PRE_POSTAGEM);
		RemetenteRequest remetente = request.getRemetente();
		DestinatarioRequest destinatario = request.getObjetoPostalRequest().get(0).getDestinatario();
		
		

		final StringBuilder sb = new StringBuilder();
		sb.append("Destinatário CEP ").append(destinatario.getCep());
		sb.append("\r\n");
		sb.append("Remetente CEP ").append(remetente.getCep());
		sb.append("\r\n");
		final PackageMeasures measures = (PackageMeasures)Session.getSession().get(Session.MEASURES);
		sb.append("Encomenda ")
		.append(measures.getWidth())
		.append(" x ").append(measures.getHeight())
		.append(" x ").append(measures.getDepth()).append(" cm ")
		.append(measures.getWeight()).append("g");

		itens.add(new Resume(sb.toString(), "", ""));

		final PrecoPrazoResponse selectedService = (PrecoPrazoResponse)Session.getSession().get(Session.SELECTED_SERVICE);
		final AdditionalServices addionalServices = (AdditionalServices)Session.getSession().get(Session.ADDITIONAL_SERVICES);
		
		
		
		if(addionalServices.isOnwHands()) {			
			itens.add(new Resume("Mão própria", "1", "R$ " + selectedService.getValorMaoPropria()));
		}
		
		if(addionalServices.isDeliveryNotice()) {			
			itens.add(new Resume("Aviso de recebimento", "1", "R$ " + selectedService.getValorAvisoRecebimento()));
		}
		
		
		if(addionalServices.isValueDeclaration()) {			
			itens.add(new Resume("Declaração de valor", "1", "R$ " + selectedService.getValorValorDeclarado()));
		}	

		itens.add(new Resume(selectedService.getDescricaoServico(), "1", "R$ " + selectedService.getValorSemAdicionais()));		

		final String vTotal = Session.getTotal(selectedService.getValorSemAdicionais(), selectedService.getValorMaoPropria(), selectedService.getValorAvisoRecebimento(), selectedService.getValorValorDeclarado());
		itens.add(new Resume("Total","", "R$ " +  vTotal));

		final ObservableList<Resume> tableModel = FXCollections.observableArrayList(itens);
		this.tableView.setItems(tableModel);

	}

	@Override
	public void clear() {
		if(this.tableView != null) this.tableView.getItems().clear();
	}

}
