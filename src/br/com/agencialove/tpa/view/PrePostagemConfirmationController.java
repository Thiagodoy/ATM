package br.com.agencialove.tpa.view;

import java.net.URL;
import java.util.ResourceBundle;

import com.qoppa.pdf.PDFException;

import br.com.agencia.rest.CorreiosPreAtendimentoApi;
import br.com.agencia.tpa.rest.request.EmiteRequest;
import br.com.agencialove.tpa.hardware.PrinterServiceImpl;
import br.com.agencialove.tpa.model.AdditionalServices;
import br.com.agencialove.tpa.model.ZipType;
import br.com.agencialove.tpa.model.rest.PrePost;
import br.com.agencialove.tpa.workflow.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class PrePostagemConfirmationController implements IController {

	private ZipType type;
	AdditionalServices as;
	private PrePost prePost;


	@FXML
	private Label ldPlp3;

	private String plp;
	
	
	@FXML
	private StackPane stack;

	@FXML
	private void btnBackAction(final ActionEvent e) {
		final Scene scene = Windows.FULFILL_ADDRESS.getScene();
		final FulfillAddressController controller = (FulfillAddressController)scene.getUserData();
		controller.setType(this.type);
		Session.setScene(scene);
	}

	@FXML
	private void btnNextAction(final ActionEvent e) {
		Session.getSession().put(Session.PRE_POST, this.prePost);

		final Scene scene = Windows.FINISH.getScene();
		Session.setScene(scene);
	}

	@FXML
	private void btnImprimirRotulo(final ActionEvent e) throws PDFException {
		EmiteRequest request  = new EmiteRequest();
		request.setNumeroPlp(this.plp);
		final PrinterServiceImpl printer = new PrinterServiceImpl();
		try {
			CorreiosPreAtendimentoApi api = Session.getCorreiosPreAtentimentoWebService();
			final byte[] respGetPdfBytesEmiteEtiqueta = api.emitirAvisoRecebimento(request);
			printer.printPdf(respGetPdfBytesEmiteEtiqueta, request.getNumeroPlp());
		} catch (final Exception e1) {
			e1.printStackTrace();
		}
	}

	@FXML
	private void btnImprimirDeclaracaoDeConteudo(final ActionEvent e) throws PDFException {
		EmiteRequest request  = new EmiteRequest();
		request.setNumeroPlp(this.plp);
		
		final PrinterServiceImpl printer = new PrinterServiceImpl();
		try {
			CorreiosPreAtendimentoApi api = Session.getCorreiosPreAtentimentoWebService();
			final byte[] respGetPdfBytesAvisoRecebimento = api.emitirDeclaracaoDeConteudo(request);
			printer.printPdf(respGetPdfBytesAvisoRecebimento, request.getNumeroPlp());
		} catch (final Exception e1) {
			e1.printStackTrace();
		}
	}

	@FXML
	private void btnImprimirAR(final ActionEvent e) throws PDFException {
		EmiteRequest request  = new EmiteRequest();
		request.setNumeroPlp(this.plp);

		final PrinterServiceImpl printer = new PrinterServiceImpl();
		try {		
			CorreiosPreAtendimentoApi api = Session.getCorreiosPreAtentimentoWebService();
			final byte[] avisoDeRecebimento = api.emitirAvisoRecebimento(request);
			printer.printPdf(avisoDeRecebimento, request.getNumeroPlp());			
		} catch (final Exception e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void initialize(final URL url, final ResourceBundle resourceBundle) {}

	@Override
	public void clear() {
		this.plp = (String) Session.getSession().get(Session.ID_PLP);
		this.plp = (this.plp != null) ? this.plp : "";
		this.ldPlp3.setText(this.plp);
	}
	
	@FXML
	@Override
	public void cancel() {
		Session.reset();		
	}
	
	public void setPlp(String plp) {
		this.ldPlp3.setText(plp);
	}

}
