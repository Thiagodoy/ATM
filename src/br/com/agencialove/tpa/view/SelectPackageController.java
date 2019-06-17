package br.com.agencialove.tpa.view;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.agencialove.tpa.model.Pack;
import br.com.agencialove.tpa.workflow.Session;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

	private List<Button> options = new ArrayList<Button>();

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

		final Scene nextScene = Windows.DETAIL_PACKAGE_SELECTED.getScene();
		DetailPackageSelectedController control = (DetailPackageSelectedController)Windows.DETAIL_PACKAGE_SELECTED.getScene().getUserData();
		control.populateInfo();
		control.setPreviousScene(Windows.SELECT_PACKAGE.getScene());
		Session.setScene(nextScene);
	}

	@Override
	public void initialize(final URL url, final ResourceBundle resourceBundle) {
	}	
	
	@Override
	public void clear() {
		if (this.hbox != null)
			this.hbox.getChildren().clear();
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
			
			this.btnNext.setDisable(true);
			
		} catch (final SQLException e) {
			Logger.getAnonymousLogger().log(Level.SEVERE, "Erro no banco de dados carregar pacotes.", e);
			Session.error();
		}

	}
	
	
	public void loadInfo() {		
		
		Pack p = (Pack) Session.getSession().get(Session.SELECTED_PACKAGE);
		
		this.options.forEach(b->{
			
			String id = p.getDimensoes() + p.getValor();
			if(b.getId().equals(id)) {
				VBox box = (VBox) b.getChildrenUnmodifiable().get(0);
				box.getChildren().forEach(c->{
					if(c instanceof TextField) {
						TextField field = (TextField) c;
						field.setText(p.getQuantidade().toString());
					}
				});
			}
		});
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

		final TextField field = new TextField();
		field.setTranslateY(90);
		field.setVisible(true);

		String id = p.getDimensoes() + p.getValor();

		final Label quantidade = new Label("Quantidade");
		quantidade.getStyleClass().add("label-packDimensions");
		quantidade.setTranslateY(100);

		vBox.getChildren().add(img);
		vBox.getChildren().add(lbDescription);
		vBox.getChildren().add(lbDimensions);
		vBox.getChildren().add(lbValue);
		vBox.getChildren().add(field);
		vBox.getChildren().add(quantidade);

		final Button ret = new Button();
		ret.setId(id);
		ret.setGraphic(vBox);
		ret.getStyleClass().add("btnPack");
		ret.setPrefWidth(255.0);
		ret.setPrefHeight(630.0);
		ret.setTranslateY(130);

		// ret.setText(p.getDescricao() + "\r\n" + p.getDimensoes() + " \r\n " +
		// p.getValor());
		// ret.setWrapText(true);
		// ret.getStyleClass().add("btn");
		// ret.setContentDisplay(ContentDisplay.TOP);

		field.textProperty()
				.addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
					if (newValue.matches("[0-9]*") && newValue.length() > 0) {
						Long value = Long.valueOf(field.getText());
						if (value > 0) {
							field.setText(newValue);
							this.selectedPack.setQuantidade(value);
							this.btnNext.setDisable(false);
						}
					} else {
						field.setText("");
						this.btnNext.setDisable(true);
					}
				});		

		field.focusedProperty().addListener((ov, oldV, newV) -> {
			this.setStyleButtonSelected(ret, vBox);
			this.selectedPack = p;
			
		});

		ret.setOnAction((final ActionEvent event) -> {
			this.selectedPack = p;
			field.requestFocus();
			this.setStyleButtonSelected(ret, vBox);			
		});
		options.add(ret);
		return ret;
	}

	private void setStyleButtonSelected(Button button, VBox vbox) {

		button.getStyleClass().add("selected-package");

		this.options.stream().filter(b -> !b.getId().equals(button.getId())).forEach(b -> {
			b.getStyleClass().remove("selected-package");
			b.getChildrenUnmodifiable().forEach(n -> {
				VBox box = (VBox) n;
				box.getChildren().forEach(ff -> {
					if (ff instanceof TextField) {
						TextField t = (TextField) ff;
						t.setText("");
					}
				});
			});
		});
	}
}
