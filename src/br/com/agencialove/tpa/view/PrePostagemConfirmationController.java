package br.com.agencialove.tpa.view;

import java.net.URL;
import java.util.ResourceBundle;

import javax.print.PrintException;

import com.qoppa.pdf.PDFException;

import br.com.agencialove.tpa.hardware.PrinterServiceImpl;
import br.com.agencialove.tpa.model.AdditionalServices;
import br.com.agencialove.tpa.model.ZipType;
import br.com.agencialove.tpa.model.rest.EmiteEtiquetaRequest;
import br.com.agencialove.tpa.model.rest.PrePost;
import br.com.agencialove.tpa.webservices.IWebService;
import br.com.agencialove.tpa.workflow.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public class PrePostagemConfirmationController implements IController {

	private ZipType type;
	AdditionalServices as;
	private PrePost prePost;


	@FXML
	private Label ldPlp3;

	private String plp;

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
		final EmiteEtiquetaRequest ppr = new EmiteEtiquetaRequest();
		ppr.setNumeroPLP(this.plp);

		final IWebService ws = Session.getWebService();
		final byte[] respGetPdfBytesEmiteEtiqueta = ws.getPdfBytesEmiteEtiqueta(ppr);



		//System.out.println("respGetPdfBytesTwo: >>>>>>> : " + respGetPdfBytes);

		final PrinterServiceImpl printer = new PrinterServiceImpl();
		try {
			//printer.printLabel(respGetPdfBytes);
			printer.printTicket(respGetPdfBytesEmiteEtiqueta);
		} catch (final PrintException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@FXML
	private void btnImprimirDeclaracaoDeConteudo(final ActionEvent e) throws PDFException {
		final EmiteEtiquetaRequest ppr = new EmiteEtiquetaRequest();
		ppr.setNumeroPLP(this.plp);

		final IWebService ws = Session.getWebService();
		final byte[] respGetPdfBytesAvisoRecebimento = ws.getPdfBytesAvisoRecebimento(ppr);


		//System.out.println("respGetPdfBytesTwo: >>>>>>> : " + respGetPdfBytesTwo);

		final PrinterServiceImpl printer = new PrinterServiceImpl();
		try {
			//printer.printLabel(respGetPdfBytes);
			printer.printTicket(respGetPdfBytesAvisoRecebimento);
		} catch (final PrintException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@FXML
	private void btnImprimirAR(final ActionEvent e) throws PDFException {
		final EmiteEtiquetaRequest ppr = new EmiteEtiquetaRequest();
		ppr.setNumeroPLP(this.plp);

		final IWebService ws = Session.getWebService();
		final byte[] respGetPdfBytesDeclaracaoDeConteudo= ws.getPdfBytesDeclaracaoDeConteudo(ppr);


		//System.out.println("respGetPdfBytesTwo: >>>>>>> : " + respGetPdfBytesTwo);

		final PrinterServiceImpl printer = new PrinterServiceImpl();
		try {
			//printer.printLabel(respGetPdfBytes);
			printer.printTicket(respGetPdfBytesDeclaracaoDeConteudo);
		} catch (final PrintException e1) {
			// TODO Auto-generated catch block
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

}
