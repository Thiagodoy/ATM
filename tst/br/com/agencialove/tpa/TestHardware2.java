package br.com.agencialove.tpa;

import br.com.agencialove.tpa.hardware.HardwareServiceImpl;
import br.com.agencialove.tpa.hardware.IHardwareCallback;
import br.com.agencialove.tpa.hardware.PrinterServiceImpl;
import br.com.agencialove.tpa.hardware.arduino.Debug;
import br.com.agencialove.tpa.hardware.arduino.Error;
import br.com.agencialove.tpa.hardware.arduino.Warning;
import br.com.agencialove.tpa.hardware.arduino.Working;
import br.com.agencialove.tpa.view.HardwareAndPrinterController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestHardware2 extends Application {

	public static void main(final String[] args) {
		//		System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/ttyACM0:/dev/ttyUSB0");
		//		System.setProperty("gnu.io.rxtx.ParallelPorts", "/dev/usb/lp0");

		Application.launch(TestHardware2.class, args);
	}

	@Override
	public void start(final Stage stage) throws Exception {
		final FXMLLoader loader = new FXMLLoader(this.getClass().getResource("view/HardwareAndPrinter.fxml"));
		final Parent root = loader.load();
		final HardwareAndPrinterController controller = loader.getController();

		controller.setHardwareService(new HardwareServiceImpl(new IHardwareCallback() {

			@Override
			public void callbackWorking(final Working working) {
				controller.working(working);
			}

			@Override
			public void callbackWarning(final Warning warning) {
				controller.warning(warning);
			}

			@Override
			public void callbackException(final Exception e) {
				controller.getTxtResult().appendText("[EXCEPTION] " + e.getMessage());
			}
			@Override
			public void callbackErro(final Error error) {
				controller.error(error);
			}

			@Override
			public void callbackDebug(final Debug debug) {
				controller.debug(debug);
			}
		}));

		controller.setPrinterService(new PrinterServiceImpl());

		final Scene scene = new Scene(root, 1024, 768);

		stage.setTitle("Teste de hardware");
		stage.setScene(scene);
		stage.show();
	}
}
