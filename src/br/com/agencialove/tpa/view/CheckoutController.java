package br.com.agencialove.tpa.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.com.agencialove.tpa.model.AdditionalServices;
import br.com.agencialove.tpa.model.Address;
import br.com.agencialove.tpa.model.PackageMeasures;
import br.com.agencialove.tpa.model.Resume;
import br.com.agencialove.tpa.model.rest.ServicesResponse;
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

		final StringBuilder sb = new StringBuilder();
		sb.append("Destinatário CEP ").append(((Address)Session.getSession().get(Session.RECEIVER_ADDRESS)).getZip());
		sb.append("\r\n");
		sb.append("Remetente CEP ").append(((Address)Session.getSession().get(Session.SENDER_ADDRESS)).getZip());
		sb.append("\r\n");
		final PackageMeasures measures = (PackageMeasures)Session.getSession().get(Session.MEASURES);
		sb.append("Encomenda ")
		.append(measures.getWidth())
		.append(" x ").append(measures.getHeight())
		.append(" x ").append(measures.getDepth()).append(" cm ")
		.append(measures.getWeight()).append("g");

		itens.add(new Resume(sb.toString(), "", ""));

		final ServicesResponse selectedService = (ServicesResponse)Session.getSession().get(Session.SELECTED_SERVICE);
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

		final String vTotal = Session.getTotal(selectedService.getValor(), selectedService.getValorMaoPropria(), selectedService.getValorAvisoRecebimento(), selectedService.getValorValorDeclarado());
		itens.add(new Resume("Total","", "R$ " +  vTotal));

		final ObservableList<Resume> tableModel = FXCollections.observableArrayList(itens);
		this.tableView.setItems(tableModel);

	}

	@Override
	public void clear() {
		if(this.tableView != null) this.tableView.getItems().clear();
	}

}
