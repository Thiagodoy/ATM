package br.com.agencialove.tpa;

import java.io.IOException;

import br.com.agencialove.tpa.view.ConfigController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ConfigUI extends Application{
	public static void main(final String[] args) throws IOException {
		Application.launch(args);
	}

	@Override
	public void start(final Stage primaryStage) throws Exception {
		primaryStage.setTitle("Configuração do ATM");
		final FXMLLoader loader = new FXMLLoader(this.getClass().getResource("view/Config.fxml"));
		final Scene scene = new Scene(loader.load());
		final ConfigController controller = loader.getController();
		controller.clear();

		controller.load();

		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
