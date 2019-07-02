package br.com.agencialove.tpa.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import br.com.agencialove.tpa.dao.EmbalagemDao;
import br.com.agencialove.tpa.dao.PostagemDao;
import br.com.agencialove.tpa.hardware.arduino.Dispenser;
import br.com.agencialove.tpa.hardware.arduino.DispenserState;
import br.com.agencialove.tpa.model.Embalagem;
import br.com.agencialove.tpa.model.Pack;
import br.com.agencialove.tpa.model.Postagem;
import br.com.agencialove.tpa.model.Relatorio;
import br.com.agencialove.tpa.model.Relatorio.RelatorioType;
import br.com.agencialove.tpa.workflow.Session;
import br.com.agencialove.tpa.workflow.SessionType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class FinishController implements IController {

	@FXML
	private StackPane stake;
	
	@FXML
	private Button btnPrintReceipt;

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {

		btnPrintReceipt.setOnAction(event->{		
			
			try {
				btnPrintReceipt.setDisable(true);
				Session.getPrinterThermalService().print("");
				Session.reset();
			} catch (Exception e) {				
				e.printStackTrace();
				btnPrintReceipt.setDisable(true);				
				this.openDialogError("Erro ao imprimir seu comprovante!");
				
			}
		});
		
		
	}

	@Override
	public void clear() {
	}

	private void openDialogError(String message) {
		JFXButton buttonYes = new JFXButton("Ok");

		// :FIXME Alinhar com o Luis Para definir as classes dos botoes		
		JFXDialogLayout layout = new JFXDialogLayout();
		layout.setBody(new Text(message));
		JFXDialog dialog = new JFXDialog(stake, layout, JFXDialog.DialogTransition.TOP);
		buttonYes.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, (e) -> {
			dialog.close();
		});

	}

	public void finish() {

		final SessionType type = (SessionType) Session.getSession().get(Session.SESSION_TYPE);

		switch (type) {
		case PACKAGE:

			Pack pack = (Pack) Session.getSession().get(Session.SELECTED_PACKAGE);

			Dispenser d = Dispenser.getDispenser(Integer.parseInt(pack.getCodigo()));

			try {
				DispenserState status = Session.getHardwareService().dispensePack(d);

				if (status.equals(DispenserState.ERROR_BLOCKED)) {
					this.openDialogError(
							"Erro ao liberar a embalagem.\n Entre em contato com a administração do correios.");
				}

				if (status.equals(DispenserState.COMMUNICATION_ERROR)) {
					this.openDialogError(
							"Erro de comunicação com o dispenser.\n Entre em contato com a administração do correios.");
				}

			} catch (IOException e) {
				this.openDialogError(
						"Erro ao liberar a embalagem.\n Entre em contato com a administração do correios.");
			}

			break;

		default:
			break;
		}		
		
		
		
		//Persisti o relatorio		
		Relatorio relatorio = (Relatorio) Session.getSession().get(Session.RELATORIO);
		
		
		if(relatorio.getType().equals(RelatorioType.EMBALAGEM)) {
			Embalagem embalagem = relatorio.getEmbalagem();  
			EmbalagemDao.save(embalagem);
			
		}else {
			Postagem postagem = relatorio.getPostagem();
			PostagemDao.save(postagem);
		}
		

	}
	
	@FXML
	@Override
	public void cancel() {
		Session.reset();		
	}
}
