package br.com.agencialove.tpa.view;

import java.net.URL;
import java.util.ResourceBundle;

import javax.print.PrintException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.qoppa.pdf.PDFException;

import br.com.agencia.rest.CorreiosPreAtendimentoApi;
import br.com.agencia.tpa.rest.request.EmiteRequest;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PrePostagemConfirmationController implements IController {

	private ZipType type;
	AdditionalServices as;
	private PrePost prePost;


	@FXML
	private Label ldPlp3;

	private String plp;
	
	
//	@FXML
//	private StackPane stack;

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

		CorreiosPreAtendimentoApi api = Session.getCorreiosPreAtentimentoWebService();
		final byte[] respGetPdfBytesEmiteEtiqueta = api.emitirAvisoRecebimento(request);


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
		EmiteRequest request  = new EmiteRequest();
		request.setNumeroPlp(this.plp);

		CorreiosPreAtendimentoApi api = Session.getCorreiosPreAtentimentoWebService();
		final byte[] respGetPdfBytesAvisoRecebimento = api.emitirDeclaracaoDeConteudo(request);



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
		EmiteRequest request  = new EmiteRequest();
		request.setNumeroPlp(this.plp);

		CorreiosPreAtendimentoApi api = Session.getCorreiosPreAtentimentoWebService();
		final byte[] avisoDeRecebimento = api.emitirAvisoRecebimento(request);


		//System.out.println("respGetPdfBytesTwo: >>>>>>> : " + respGetPdfBytesTwo);

		final PrinterServiceImpl printer = new PrinterServiceImpl();
		try {			
			//printer.printTicket(avisoDeRecebimento);
			
//			JFXButton buttonYes = new JFXButton("Ok");		
//			 buttonYes.getStyleClass().add("bt-blue-sm");
//			 VBox vBox = new VBox();
//			 vBox.getChildren().add(new Text("Informação"));
//			 vBox.getChildren().add(new Text("Retire o seu aviso de recebimento!"));
//			 
//			JFXDialogLayout layout = new JFXDialogLayout();
//			layout.setBody(vBox);
//			JFXDialog dialog = new JFXDialog(stack, layout, JFXDialog.DialogTransition.TOP);
//			buttonYes.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, (ed) -> {
//				dialog.close();
//				Session.reset();
//			});

			

//			layout.setActions(buttonYes);
//			dialog.setFocusTraversable(true);
//			dialog.show();
			
			
			
		} catch (final Exception e1) {		
			

			
//			JFXButton buttonYes = new JFXButton("Ok");		
//			 buttonYes.getStyleClass().add("bt-blue-sm");
//			 VBox vBox = new VBox();
//			 vBox.getChildren().add(new Text("Informação"));
//			 vBox.getChildren().add(new Text("Retire o seu aviso de recebimento!"));
//			 
//			JFXDialogLayout layout = new JFXDialogLayout();
//			layout.setBody();
//			JFXDialog dialog = new JFXDialog(stack, layout, JFXDialog.DialogTransition.TOP);
//			buttonYes.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, (ed) -> {
//				dialog.close();
//				Session.reset();
//			});
//
//			
//
//			layout.setActions(buttonYes);
//			dialog.setFocusTraversable(true);
//			dialog.show();
			
			
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
