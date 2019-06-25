package br.com.agencialove.tpa.view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.com.agencia.rest.CorreiosImpl;
import br.com.agencia.tpa.rest.request.DestinatarioRequest;
import br.com.agencia.tpa.rest.request.PrePostagemRequest;
import br.com.agencia.tpa.rest.response.CepResponse;
import br.com.agencialove.tpa.Messages;
import br.com.agencialove.tpa.model.Address;
import br.com.agencialove.tpa.model.ZipType;
import br.com.agencialove.tpa.webservices.IWebService;
import br.com.agencialove.tpa.workflow.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SearchZipController implements IController {

	private List<CepResponse> ceps;

	@FXML
	private Label titleLabel;

	@FXML
	private Button btnSearch;

	@FXML
	private TextField txtCep;

	@FXML
	private Button btnBack;

	@FXML
	private Button btnNext;

	@FXML
	private TableView tableView;

	@FXML
	private Label statusLabel;

	private ZipType type;
	private Windows previousWindow;

	/**
	 * Responsável por buscar o CEP digitado no campo fieldCEP
	 * A consulta retorna uma lista de endereços.
	 *
	 * @param evt
	 */
	@SuppressWarnings("unchecked")
	@FXML
	private void btnSearchAction(final ActionEvent e) {
		if (!this.txtCep.getText().isEmpty()) {

			this.tableView.getItems().clear();

			final CorreiosImpl ws = Session.getCorreiosWebService();
			this.ceps = ws.buscarEndereco(this.txtCep.getText().replace("-", "")); //$NON-NLS-1$ //$NON-NLS-2$

			if (this.ceps.isEmpty()) {
				this.statusLabel.setText(Messages.getString("SearchZipController.2")); //$NON-NLS-1$
				this.statusLabel.setVisible(true);
			} else {
				final ObservableList<CepResponse> tableModel = FXCollections.observableArrayList(this.ceps);
				this.tableView.setItems(tableModel);
				if(this.ceps.size() == 1) {
					this.statusLabel.setText(Messages.getString("SearchZipController.3")); //$NON-NLS-1$
				}else {
					this.statusLabel.setText(Messages.getString("SearchZipController.4").replace(Messages.getString("SearchZipController.5"), Integer.toString(this.ceps.size()))); //$NON-NLS-1$ //$NON-NLS-2$
					this.tableView.setVisible(true);
				}
			}
		}
	}

	@FXML
	private void btnNextAction(final ActionEvent e) {
		final Scene scene = Windows.FULFILL_ADDRESS.getScene();
		final FulfillAddressController controller = (FulfillAddressController)scene.getUserData();
		controller.clear();
		controller.setType(this.type);
		Session.setScene(scene);
	}

	@FXML
	private void btnBackAction(final ActionEvent e) {
		final Scene scene = this.previousWindow.getScene();
		if(this.type == ZipType.SENDER) {
			final ConfirmAddressController controller = (ConfirmAddressController) scene.getUserData();
			controller.setType(ZipType.RECEIVER);
		}

		Session.setScene(scene);
	}

	@Override
	public void initialize(final URL url, final ResourceBundle resourceBundle) {
		this.tableView.setOnMouseClicked(new SelectedListener());
	}

	public ZipType getType() {
		return this.type;
	}

	public void setType(final ZipType type) {
		this.type = type;
		switch(this.type) {
		case RECEIVER:
			this.titleLabel.setText(Messages.getString("SearchZipController.6")); //$NON-NLS-1$
			this.previousWindow = Windows.START;
			break;
		case SENDER:
			this.titleLabel.setText(Messages.getString("SearchZipController.7")); //$NON-NLS-1$
			this.previousWindow = Windows.CONFIRM_ADDRESS;
			break;
		}
	}

	public void setTitle(final String value) {
		this.titleLabel.setText(value);
	}

	private class SelectedListener implements EventHandler<javafx.scene.input.MouseEvent>{
		@Override
		public void handle(final javafx.scene.input.MouseEvent event) {
			if(!SearchZipController.this.tableView.getItems().isEmpty()) {
				final CepResponse cep = (CepResponse) SearchZipController.this.tableView.getSelectionModel().getSelectedItem();
				if (cep == null) {
					SearchZipController.this.btnNext.setDisable(true);
				}else {
					SearchZipController.this.btnNext.setDisable(false);
					Session.getSession().put(SearchZipController.this.type.name() + "_ADDRESS",cep); //$NON-NLS-1$
				}
			}
		}
	}

	@Override
	public void clear() {
		if(this.ceps != null) this.ceps = null;
		if(this.type != null) this.type = null;
		if(this.titleLabel != null) this.titleLabel.setText(""); //$NON-NLS-1$
		if(this.txtCep != null) {this.txtCep.setText(""); this.txtCep.setText(""); this.txtCep.clear();}//$NON-NLS-1$
		if(this.btnNext != null) this.btnNext.setDisable(true);
		if(this.tableView != null) this.tableView.getItems().clear();
		if(this.statusLabel != null) this.statusLabel.setText(Messages.getString("SearchZipController.11")); //$NON-NLS-1$
	}
}
