package br.com.agencialove.tpa.view;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

//gitlab.com/agencialove/tpa.git
import br.com.agencialove.tpa.Configuration;
import br.com.agencialove.tpa.workflow.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ConfigController implements IController {

	@FXML
	private VBox vbox;

	@FXML
	private Button btnSave;

	@FXML
	private Button btnCancel;

	private Map<String,String> oldConfig;
	private Map<Label,ComboBox<String>> newConfig;

	@FXML
	private void btnCancelAction(final ActionEvent e) {
		System.exit(0);
	}

	@FXML
	private void btnSaveAction(final ActionEvent e) {
		final Map<String,String> config = new HashMap<>();
		for(final Map.Entry<Label, ComboBox<String>> entry : this.newConfig.entrySet())
			config.put(entry.getKey().getText(), entry.getValue().getSelectionModel().getSelectedItem());
		Configuration.setConfigurations(config);
		Configuration.save();
		System.exit(0);
	}


	@Override
	public void initialize(final URL url, final ResourceBundle resourceBundle) {
	}

	@Override
	public void clear() {
		if(this.vbox != null) this.vbox.getChildren().clear();
	}

	public void load() {
		this.oldConfig = Configuration.getConfigurations();
		this.newConfig = new HashMap<>();

		for(final Map.Entry<String,String> p : this.oldConfig.entrySet()) {
			final HBox hbox = new HBox();
			final Label key = new Label(p.getKey());
			final ComboBox<String> value = new ComboBox<>();
			
			value.setLayoutY(100.0);
			key.getStyleClass().add("txt-label");
			hbox.getStyleClass().add("title-package");
			
			//if(p.getKey().startsWith("serial"))
				//value.getItems().addAll(Configuration.getSerialPorts());
			if(p.getKey().startsWith("printer"))
				value.getItems().addAll(Configuration.getPrinters());

			this.newConfig.put(key, value);

			hbox.getChildren().add(key);
			hbox.getChildren().add(value);
			this.vbox.getChildren().add(hbox);
		}
	}
	
	@FXML
	@Override
	public void cancel() {
		Session.reset();		
	}
}
