package br.com.agencialove.tpa.view;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.agencialove.tpa.Messages;
import br.com.agencialove.tpa.model.Address;
import br.com.agencialove.tpa.model.ZipType;
import br.com.agencialove.tpa.workflow.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public class ConfirmAddressController implements IController {

	private ZipType type;
	private Address address;

	@FXML
	private Label lbTitle;

	@FXML
	private Label lbLogradouroConfirmacao;

	@FXML
	private Label lbNumeroConfirmacao;

	@FXML
	private Label lbBairro;

	@FXML
	private Label lbComplementoConfirmacao;

	@FXML
	private Label lbEstadoConfirmacao;

	@FXML
	private Label lbCEPConfirmacao;

	@FXML
	private Label lbCidadeConfirmacao;

	@FXML
	private Label lbNomeDestinatarioConfirmacao;

	@FXML
	private Label lbCPFDestinatario;

	@FXML
	private Label lbCelularDestinatarioConfirmacao;

	@FXML
	private Label lbEmailConfirmacao;


	@FXML
	private void btnBackAction(final ActionEvent e) {
		final Scene scene = Windows.FULFILL_ADDRESS.getScene();
		final FulfillAddressController controller = (FulfillAddressController)scene.getUserData();
		controller.setType(this.type);
		Session.setScene(scene);
	}

	@FXML
	private void btnNextAction(final ActionEvent e) {
		Scene scene = null;
		switch(this.type) {
		case RECEIVER:
			scene = Windows.SEARCH_ZIP.getScene();
			final SearchZipController controller = (SearchZipController) scene.getUserData();
			controller.clear();
			controller.setType(ZipType.SENDER);
			break;
		case SENDER:
			scene = Windows.ADDITIONAL_SERVICES1.getScene();
			break;
		}
		Session.setScene(scene);
	}


	public void setTitle(final String str) {
		this.lbTitle.setText(str);
	}

	@Override
	public void initialize(final URL url, final ResourceBundle resourceBundle) {

		//        MaskFieldUtil.foneField(this.txtCelular);
		//        MaskFieldUtil.cepField(this.txtCEP);

		//this.tipoPessoa = ContentNavigator.getController().getTipoPessoa();

		//ContentNavigator.getController().setButtonActionPerformet(event -> ContentNavigator.loadContent(ContentNavigator.CONSULTA_CEP_WINDOW));
	}

	public ZipType getType() {
		return this.type;
	}

	public void setType(final ZipType type) {
		this.type = type;
		switch(this.type) {
		case RECEIVER:
			this.lbTitle.setText(Messages.getString("ConfirmAddressController.0")); //$NON-NLS-1$
			break;
		case SENDER:
			this.lbTitle.setText(Messages.getString("ConfirmAddressController.1")); //$NON-NLS-1$
		}

		Address address = (Address) Session.getSession().get(this.type.name() + "_ADDRESS");
		if(address == null)
			address = new Address();
		this.setAddress(address);
	}

	public Address getAddress() {
		return this.address;
	}

	private void setAddress(final Address pAddress) {
		this.address = pAddress;

		this.lbLogradouroConfirmacao.setText(this.get(this.address.getStreet()));
		this.lbNumeroConfirmacao.setText(this.get(this.address.getNumber()));
		this.lbBairro.setText(this.get(this.address.getNeighborhood()));
		this.lbComplementoConfirmacao.setText(this.get(this.address.getComplement()));
		this.lbEstadoConfirmacao.setText(this.get(this.address.getState()));
		this.lbCEPConfirmacao.setText(this.get(this.address.getZip()));
		this.lbCidadeConfirmacao.setText(this.get(this.address.getCity()));
		this.lbNomeDestinatarioConfirmacao.setText(this.get(this.address.getPerson().getName()));
		this.lbCPFDestinatario.setText(this.get(this.address.getPerson().getCPF_CPNJ()));
		this.lbCelularDestinatarioConfirmacao.setText(this.get(this.address.getPerson().getCellPhone()));
		this.lbEmailConfirmacao.setText(this.get(this.address.getPerson().getEmail()));
	}

	private String get(final String str) {
		return (str == null) ? "" : str; //$NON-NLS-1$
	}

	@Override
	public void clear() {
		if(this.lbLogradouroConfirmacao != null) this.lbLogradouroConfirmacao.setText("");
		if(this.lbNumeroConfirmacao != null) this.lbNumeroConfirmacao.setText(""); //$NON-NLS-1$
		if(this.lbBairro != null) this.lbBairro.setText(""); //$NON-NLS-1$
		if(this.lbComplementoConfirmacao != null) this.lbComplementoConfirmacao.setText(""); //$NON-NLS-1$
		if(this.lbEstadoConfirmacao != null) this.lbEstadoConfirmacao.setText(""); //$NON-NLS-1$
		if(this.lbCEPConfirmacao != null) this.lbCEPConfirmacao.setText(""); //$NON-NLS-1$
		if(this.lbCidadeConfirmacao != null) this.lbCidadeConfirmacao.setText(""); //$NON-NLS-1$
		if(this.lbNomeDestinatarioConfirmacao != null) this.lbNomeDestinatarioConfirmacao.setText(""); //$NON-NLS-1$
		if(this.lbNomeDestinatarioConfirmacao != null) this.lbNomeDestinatarioConfirmacao.setText(""); //$NON-NLS-1$
		if(this.lbCelularDestinatarioConfirmacao != null) this.lbCelularDestinatarioConfirmacao.setText(""); //$NON-NLS-1$
		if(this.lbEmailConfirmacao != null) this.lbEmailConfirmacao.setText(""); //$NON-NLS-1$
	}
}
