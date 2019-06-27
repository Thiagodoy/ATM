package br.com.agencialove.tpa.view;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import br.com.agencia.tpa.rest.request.PrePostagemRequest;
import br.com.agencia.tpa.rest.response.PrecoPrazoResponse;
import br.com.agencialove.tpa.workflow.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class SelectServiceController implements IController {

	private PrecoPrazoResponse selectedService;
	private PrecoPrazoResponse sedexService;
	private PrecoPrazoResponse pacService;	

	@FXML
	private HBox hbox;

	@FXML
	private Button btnBack;

	@FXML
	private Button btnNext;

	@FXML
	private ImageView image;

	@FXML
	private Pane panelSedex;

	@FXML
	private Pane panelPac;

	@FXML
	private Label labelPacDia;

	@FXML
	private Label labelSedexDia;

	@FXML
	private Label labelPacValor;

	@FXML
	private Label labelSedexValor;
	
	@FXML
	private Label labelObservacaoSedex;
	
	@FXML
	private Label labelObservacaoPac;
	
	
	

	public void loadInfo() {

		this.labelObservacaoPac.setVisible(false);
		this.labelObservacaoSedex.setVisible(false);
		
		this.btnNext.setDisable(true);
		
		@SuppressWarnings("unchecked")
		final List<PrecoPrazoResponse> avaliableServices = (List<PrecoPrazoResponse>) Session.getSession()
				.get(Session.AVAILABLE_SERVICES);

		Optional<PrecoPrazoResponse> optSedex = avaliableServices.stream()
				.filter(f -> f.getCodigoServico().equalsIgnoreCase("04022")).findFirst();
		Optional<PrecoPrazoResponse> optPac = avaliableServices.stream()
				.filter(f -> f.getCodigoServico().equalsIgnoreCase("04030")).findFirst();

		if (optSedex.isPresent()) {
			Long dias = Long.parseLong(optSedex.get().getPrazoEntrega());
			this.labelSedexDia.setText("Dia da Postagem + "
					+ ((dias <= 1) ? dias.toString() + " dia útil" : dias.toString() + " dias útil"));
			this.labelSedexValor.setText("R$ " + optSedex.get().getValor());
			this.sedexService = optSedex.get();
			
			if(Optional.ofNullable(optSedex.get().observacao).isPresent()) {
				this.labelObservacaoSedex.setText(optSedex.get().getObservacao());
				this.labelObservacaoSedex.setVisible(true);
			}
			
		}

		if (optPac.isPresent()) {
			Long dias = Long.parseLong(optPac.get().getPrazoEntrega());
			this.labelPacDia.setText("Dia da Postagem + "
					+ ((dias <= 1) ? dias.toString() + " dia útil" : dias.toString() + " dias útil"));
			this.labelPacValor.setText("R$ " + optPac.get().getValor());
			
			this.pacService = optPac.get();
			this.selectedService = optPac.get();
			
			if(Optional.ofNullable(optPac.get().observacao).isPresent()) {
				this.labelObservacaoPac.setText(optSedex.get().getObservacao());
				this.labelObservacaoPac.setVisible(true);
			}				
			
		}
		
			
		

	}

	@FXML
	private void btnBackAction(final ActionEvent e) {
		final Scene scene = Windows.MEASURES.getScene();
		final MeasuresController controller = (MeasuresController) scene.getUserData();
		controller.clear();
		Session.setScene(scene);
	}

	@FXML
	private void btnNextAction(final ActionEvent e) {
		
		PrePostagemRequest request = (PrePostagemRequest) Session.getSession().get(Session.PRE_POSTAGEM);
		request.getObjetoPostalRequest().get(0).setCodigoServicoPostagem(this.selectedService.codigoServico);
		
		Session.getSession().put(Session.SELECTED_SERVICE, this.selectedService);
		final Scene scene = Windows.NFE_CHOOSE.getScene();
		Session.setScene(scene);	
		
	}
	

	@Override
	public void initialize(final URL url, final ResourceBundle resourceBundle) {

		this.panelSedex.setOnMouseClicked((event) -> {		
			this.panelSedex.getStyleClass().remove("select-sedex");
			this.panelSedex.getStyleClass().add("select-pac");
			this.panelSedex.getStyleClass().add("box-add-borda");
			this.panelPac.getStyleClass().remove("select-pac");
			this.panelPac.getStyleClass().remove("box-add-borda");
			this.panelPac.getStyleClass().add("select-sedex");
			this.selectedService = this.sedexService;
			this.btnNext.setDisable(false);
		});

		this.panelPac.setOnMouseClicked((event) -> {
			
			this.panelPac.getStyleClass().remove("select-sedex");
			this.panelPac.getStyleClass().add("select-pac");
			this.panelPac.getStyleClass().add("box-add-borda");
			this.panelSedex.getStyleClass().remove("select-pac");
			this.panelSedex.getStyleClass().remove("box-add-borda");
			this.panelSedex.getStyleClass().add("select-sedex");
			
			this.selectedService = this.pacService;
			this.btnNext.setDisable(false);
		});

	}

	@Override
	public void clear() {	
		this.btnNext.setDisable(true);
	}

}