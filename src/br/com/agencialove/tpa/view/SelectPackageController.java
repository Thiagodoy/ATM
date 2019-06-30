package br.com.agencialove.tpa.view;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.agencialove.tpa.model.Pack;
import br.com.agencialove.tpa.workflow.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SelectPackageController implements IController {

	private Pack selectedPack;

	@FXML
	private HBox hbox;

	@FXML
	private Button btnBack;

	@FXML
	private Button btnNext;

	@FXML
	public Button btnPack1;

	@FXML
	private Button btnPack2;

	@FXML
	private Button btnPack3;

	@FXML
	private Button btnPack4;

	private Scene previousScene;

	@FXML
	private void btnBackAction(final ActionEvent e) {
		final Scene startScene = Windows.START.getScene();
		if (startScene == this.previousScene)
			Session.reset();
		else
			Session.setScene(this.previousScene);

	}

	@FXML
	private void btnNextAction(final ActionEvent e) {
		Session.getSession().put(Session.SELECTED_PACKAGE, this.selectedPack);

		Scene nextScene = Windows.DETAIL_PACKAGE_SELECTED.getScene();
		
		DetailPackageSelectedController control = (DetailPackageSelectedController)Windows.DETAIL_PACKAGE_SELECTED.getScene().getUserData();
		control.populateInfo();	
		control.setPreviousScene(Windows.SELECT_PACKAGE.getScene());
		
		Session.setScene(nextScene);
	}

	@Override
	public void initialize(final URL url, final ResourceBundle resourceBundle) {
		this.btnNext.setDisable(true);
	}

	@Override
	public void clear() {
		if (this.hbox != null)
			this.hbox.getChildren().clear();

		this.btnNext.setDisable(true);
	}

	public Scene getPreviousScene() {
		return this.previousScene;
	}

	public void setPreviousScene(final Scene previousScene) {
		this.previousScene = previousScene;
	}

	public void loadPacksFromDB() {
		try {
			final List<Pack> packs = Session.getDBService().listPacks();
			for (final Pack p : packs) {
				final Button b = this.newButton(p);
				this.hbox.getChildren().add(b);
			}
		} catch (final SQLException e) {
			Logger.getAnonymousLogger().log(Level.SEVERE, "Erro no banco de dados carregar pacotes.", e);
			Session.error();
		}

	}

	private Button newButton(final Pack p) {
		final VBox vBox = new VBox();
		final ImageView img = new ImageView(p.getImagem());
		img.setFitHeight(155);
		img.setTranslateY(70);

		final Label lbDescription = new Label(p.getDescricao());
		lbDescription.getStyleClass().add("label-packDescription");
		lbDescription.setTranslateY(75);

		final Label lbDimensions = new Label(p.getDimensoes());
		lbDimensions.getStyleClass().add("label-packDimensions");
		lbDimensions.setTranslateY(80);

		final Label lbValue = new Label(p.getValor());
		lbValue.getStyleClass().add("label-packValue");
		lbValue.setTranslateY(85);

		vBox.getChildren().add(img);
		vBox.getChildren().add(lbDescription);
		vBox.getChildren().add(lbDimensions);
		vBox.getChildren().add(lbValue);

		final Button ret = new Button();
		ret.setGraphic(vBox);
		ret.getStyleClass().add("btnPack");
		ret.setPrefWidth(255.0);
		ret.setPrefHeight(630.0);
		ret.setTranslateY(130);
		ret.setId("caixa" + p.getCodigo());

		ret.setOnAction((final ActionEvent event) -> {
			this.selectedPack = p;
			this.btnNext.setDisable(false);

		});
		return ret;
	}
	
	@FXML
	@Override
	public void cancel() {
		Session.reset();		
	}
}
