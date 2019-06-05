package br.com.agencialove.tpa.view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.com.agencialove.tpa.model.rest.ServicesResponse;
import br.com.agencialove.tpa.workflow.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SelectServiceController implements IController {



	private ServicesResponse selectedService;
	private String obs;// Observa��o da entrega em domicilio

	@FXML
	private HBox hbox;

	@FXML
	private Button btnBack;

	@FXML
	private Button btnNext;

	@FXML
	private ImageView image;

	public void loadInfo() {
		final List<ServicesResponse> avaliableServices = (List<ServicesResponse>) Session.getSession().get(Session.AVAILABLE_SERVICES);
		for(final ServicesResponse s : avaliableServices ) {
			final Button b = this.getPane(s);
			this.hbox.getChildren().add(b);
			if (s.getObservacao()!= null && !s.getObservacao().equals("")) {
				this.obs = s.getObservacao();
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
		Session.getSession().put(Session.SELECTED_SERVICE, this.selectedService);
		final Scene scene = Windows.NFE_CHOOSE.getScene();
		Session.setScene(scene);
	}


	private Label getObservacao(final String obs) {
		if (obs != null && !obs.equals("")) {
			final Label l = new Label(obs);
			return l;
		}
		return null;
	}

	private Button getPane(final ServicesResponse service) {
		final Pane ret = new Pane();
		final VBox vBox = new VBox();

		ret.setPrefWidth(1024);
		ret.getStyleClass().add("pane"); //$NON-NLS-1$

		vBox.getStyleClass().add("bg-white");
		final Label lblServiceValue = new Label(service.getDescricaoServico());
		final Label lblTotalValue = new Label("R$ " + service.getValor());
		final Label lblDeadlineValue = new Label("Dia da Postagem + " + service.getPrazoEntrega() + " dia útil");
		final Label lblObs = this.getObservacao(service.getObservacao());

		if (lblObs != null) {
			lblObs.setTranslateY(220);
			lblObs.setTranslateX(15);
			lblObs.setStyle("-fx-text-fill: red");
			lblObs.setWrapText(true);
			vBox.getChildren().add(lblObs);
		}

		//Styles
		lblServiceValue.getStyleClass().add("lblTitleValues");
		//lblServiceValue.setPrefWidth(350);
		lblServiceValue.setPrefHeight(15);
		lblServiceValue.setTranslateX(15);
		lblServiceValue.setTranslateY(15);


		lblTotalValue.getStyleClass().add("lbValorTotal");
		lblTotalValue.setTranslateX(100);
		lblTotalValue.setTranslateY(18);
		//44051-634
		lblDeadlineValue.getStyleClass().add("lbDiasUteis");
		lblDeadlineValue.setTranslateX(80);
		lblDeadlineValue.setTranslateY(22);

		//final Label lblObs = new Label(service.getObservacao());

		vBox.getChildren().add(lblServiceValue);
		vBox.getChildren().add(lblTotalValue);
		vBox.getChildren().add(lblDeadlineValue);
		//vBox.getChildren().add(lblObs);

		final Button b = new Button();
		b.setGraphic(vBox);
		b.getStyleClass().add("btnPack");

		b.setId(service.getCodigoServico());
		b.setPrefWidth(350);
		b.setPrefHeight(200);
		b.setTranslateX(162);
		b.setTranslateY(120);

		this.btnNext.setDisable(false);
		//		ret.setText(p.getDescricao() + "\r\n" + p.getDimensoes() + " \r\n " + p.getValor());
		//		ret.setWrapText(true);
		//		ret.getStyleClass().add("btn");
		//		ret.setContentDisplay(ContentDisplay.TOP);

		b.setOnAction((final ActionEvent event) -> {
			this.selectedService = service;
			this.btnNext.setDisable(false);
		});

		return b;
	}

	@Override
	public void initialize(final URL url, final ResourceBundle resourceBundle) {
	}

	@Override
	public void clear() {
		if(this.hbox != null) this.hbox.getChildren().clear();
		this.btnNext.setDisable(true);
	}

}