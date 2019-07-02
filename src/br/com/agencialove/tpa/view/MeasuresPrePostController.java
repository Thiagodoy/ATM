package br.com.agencialove.tpa.view;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import br.com.agencialove.tpa.Configuration;
import br.com.agencialove.tpa.Messages;
import br.com.agencialove.tpa.model.Address;
import br.com.agencialove.tpa.model.ObjectType;
import br.com.agencialove.tpa.model.PackageMeasures;
import br.com.agencialove.tpa.model.rest.PrePost;
import br.com.agencialove.tpa.model.rest.PrePostResponse;
import br.com.agencialove.tpa.webservices.IWebService;
import br.com.agencialove.tpa.workflow.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;

public class MeasuresPrePostController implements IController {

	private PackageMeasures measures;
	@FXML
	private Button btnBack;

	@FXML
	private Button btnNext;

	@FXML
	private Button btnMeasures;

	@FXML
	private Button btnPackageP;

	@FXML
	private Button btnPackageM;

	@FXML
	private Button btnPackageG;

	@FXML
	private Button btnPackageL;

	@FXML
	private RadioButton rbEnvelope;

	@FXML
	private RadioButton rbBox;

	@FXML
	private RadioButton rbCylinder;


	private ObjectType objectType;

	@FXML
	private void btnBackAction(final ActionEvent e) {
		final Scene scene = Windows.CONFIRM_PRE_POST.getScene();
		Session.setScene(scene);
	}

	@FXML
	private void btnPackagePAction(final ActionEvent e) {


	}

	@FXML
	private void btnPackageMAction(final ActionEvent e) {
		this.objectType = ObjectType.MID;
		System.out.println("OK");
	}


	@FXML
	private void btnPackageGAction(final ActionEvent e) {
		this.objectType = ObjectType.BIG;
		System.out.println("OK");
	}


	@FXML
	private void btnPackageLAction(final ActionEvent e) {
		this.objectType = ObjectType.SMALL;
		System.out.println("OK");
	}


	@FXML
	private void btnPackageAction(final ActionEvent e) {
		if(this.btnPackageP.isArmed()) {
			this.objectType = ObjectType.SMALL;
			System.out.println("selecionado: P");
		}
		if(this.btnPackageM.isArmed()) {
			this.objectType = ObjectType.MID;
			System.out.println("selecionado: M");
		}
		if(this.btnPackageG.isArmed()) {
			this.objectType = ObjectType.BIG;
			System.out.print("selecionado: G");
		}

		if(this.objectType != null)
			this.btnMeasures.setDisable(false);
	}

	@FXML
	private void btnNextAction(final ActionEvent e) {
		final PrePostResponse req = this.getRequest();
		final IWebService ws = Session.getWebService();
		final PrePost resp = ws.prePostGet(req);


		Session.getSession().put(Session.RECEIVER_ADDRESS, this.getCepDestinatario(resp));
		Session.getSession().put(Session.SENDER_ADDRESS, this.getCepRemetente(resp));
		Session.getSession().put(Session.MEASURES, this.getMeasures(resp));


		final Scene scene = Windows.CHECKOUT.getScene();
		final CheckoutController controller = (CheckoutController) scene.getUserData();
		controller.loadInfo();
		Session.setScene(scene);
	}
	private PackageMeasures getMeasures(final PrePost pResp) {
		final PackageMeasures measures = new PackageMeasures();

		measures.setType("001");
		measures.setWeight(pResp.getPeso());
		measures.setWidth(pResp.getDimensaoLargura());
		measures.setHeight(pResp.getDimensaoAltura());
		measures.setDiameter(pResp.getDimensaoDiametro());
		measures.setDepth(pResp.getDimensaoComprimento());


		return measures;
	}


	private Address getCepDestinatario(final PrePost pResp) {
		final Address ret = new Address();
		ret.setZip(pResp.getCepDestinatario());
		return ret;
	}

	private Address getCepRemetente(final PrePost pResp) {
		final Address ret = new Address();
		ret.setZip(pResp.getCepRemetente());
		return ret;
	}

	private PrePostResponse getRequest(){
		final PrePostResponse req = new PrePostResponse();

		req.setNumeroPLP(((PrePostResponse)Session.getSession().get(Session.ID_PLP)).getNumeroPLP());
		req.setNumeroEtiqueta("");

		return req;
	}

	@FXML
	private void btnMeasureAction(final ActionEvent e) {
		if(this.objectType != null) {
			try {
				this.measures = Session.getHardwareService().getMeasures();

				final double d = Double.parseDouble(this.measures.getWeight());
				if(d <= Configuration.getWeightUpperLimit() && d >= Configuration.getWeightLowerLimit()) {
					this.btnNext.setDisable(false);
				}else {
					final JOptionPane pane = new JOptionPane("Peso fora do limite permitido: " + this.measures.getWeight());
					pane.setVisible(true);

					//TODO este JOptionPane não é do JavaFX, deve-se substituir por um alert do JavaFX
				}

			} catch (final Exception ioe) {
				Logger.getAnonymousLogger().log(Level.SEVERE, Messages.getString("MeasuresController.4"), ioe); //$NON-NLS-1$
				Session.error();
			}
		}
		Session.getSession().put(Session.MEASURES, this.measures);
		if(this.btnMeasures.isArmed()) {
			String peso;
			peso = JOptionPane.showInputDialog("Digite o peso da encomenda");
			JOptionPane.showMessageDialog(null, peso);
			this.measures.setWeight(peso);
		}
		if(this.objectType != null)
			this.btnNext.setDisable(false);
	}

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {

	}
	/*
	@Override
	public void clear() {
		if(this.rbEnvelope != null) this.rbEnvelope.setSelected(false);
		if(this.rbBox != null) this.rbBox.setSelected(false);;
		if(this.rbCylinder != null) this.rbCylinder.setSelected(false);;
		if(this.measures != null) this.measures = null;
		if(this.objectType != null) this.objectType = null;
		this.btnMeasures.setDisable(true);
	}*/
	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}
	
	@FXML
	@Override
	public void cancel() {
		Session.reset();		
	}
}
