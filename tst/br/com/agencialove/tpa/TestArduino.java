package br.com.agencialove.tpa;

import java.io.IOException;

import br.com.agencialove.tpa.hardware.HardwareServiceImpl;
import br.com.agencialove.tpa.hardware.IHardwareCallback;
import br.com.agencialove.tpa.hardware.IHardwareService;
import br.com.agencialove.tpa.hardware.arduino.Debug;
import br.com.agencialove.tpa.hardware.arduino.Error;
import br.com.agencialove.tpa.hardware.arduino.Warning;
import br.com.agencialove.tpa.hardware.arduino.Working;
import br.com.agencialove.tpa.view.ArduinoController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestArduino extends Application{
	public static void main(final String[] args) throws IOException {
		Application.launch(args);
	}

	@Override
	public void start(final Stage primaryStage) throws Exception {
		primaryStage.setTitle("Arduino Test");
		final FXMLLoader loader = new FXMLLoader(this.getClass().getResource("view/Arduino.fxml"));
		final Scene scene = new Scene(loader.load());
		final ArduinoController controller = loader.getController();

		primaryStage.setScene(scene);
		primaryStage.show();

		final IHardwareCallback callback = new IHardwareCallback() {

			@Override
			public void callbackWarning(final Warning warning) {
				controller.warning(warning);

			}

			@Override
			public void callbackErro(final Error error) {
				controller.error(error);
			}

			@Override
			public void callbackException(final Exception e) {
				e.printStackTrace();
			}

			@Override
			public void callbackDebug(final Debug debug) {
				controller.debug(debug);

			}

			@Override
			public void callbackWorking(final Working working) {
				controller.working(working);
			}
		};


		final IHardwareService hardwareService = new HardwareServiceImpl(callback);

		controller.setHardwareService(hardwareService);


	}
}
