package br.com.agencialove.tpa.view;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.agencialove.tpa.model.rest.PrePost;
import br.com.agencialove.tpa.model.rest.PrePostResponse;
import br.com.agencialove.tpa.webservices.IWebService;
import br.com.agencialove.tpa.workflow.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

public class PrePostingController implements IController {

	@FXML
	private TextField txtPrePost;

	private PrePost prePost;


	@FXML
	private void btnBackAction(final ActionEvent e) {
		final Scene scene = Windows.START.getScene();
		Session.setScene(scene);
	}

	@FXML
	private void btnNextAction(final ActionEvent e) {
		Session.getSession().put(Session.PRE_POST, this.prePost);

		final PrePostResponse req = this.getRequest();

		Session.getSession().put(Session.ID_PLP, this.getRequest());

		final IWebService ws = Session.getWebService();
		final PrePost resp = ws.prePostGet(req);

		final Scene scene = Windows.CONFIRM_PRE_POST.getScene();

		final ConfirmPrePostController controller = (ConfirmPrePostController)scene.getUserData();
		controller.setPrePostDestinatario(resp.getNomeDestinatario(), resp.getCepDestinatario(), resp.getNumeroEndDestinatario(), resp.getLogradouroDestinatario(),
				resp.getEmailDestinatario(), resp.getCelularDestinatario(),
				//Remetente
				resp.getNomeRemetente(), resp.getCepRemetente(), resp.getNumeroEndRemetente(),
				resp.getLogradouroRemetente(), resp.getEmailRemetente(), resp.getCelularRemetente());
		Session.setScene(scene);
	}

	private PrePostResponse getRequest(){
		final PrePostResponse req = new PrePostResponse();

		req.setNumeroPLP(this.getTxtPrePost().getText().trim());
		req.setNumeroEtiqueta("");

		return req;
	}

	public TextField getTxtPrePost() {
		return this.txtPrePost;
	}

	public void setTxtPrePost(final TextField txtPrePost) {
		this.txtPrePost = txtPrePost;
	}




	@Override
	public void initialize(final URL location, final ResourceBundle resources) {

	}

	@Override
	public void clear() {
	}
}
