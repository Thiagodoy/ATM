package br.com.agencialove.tpa.hardware;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.agencialove.tpa.hardware.arduino.Debug;
import br.com.agencialove.tpa.hardware.arduino.Error;
import br.com.agencialove.tpa.hardware.arduino.Warning;
import br.com.agencialove.tpa.hardware.arduino.Working;
import br.com.agencialove.tpa.hardware.scale.ScaleServiceImpl;
import br.com.agencialove.tpa.hardware.scale.ScaleServiceImpl.ScaleMonitoringThread;

class TestScaleServiceImpl {
	private final IHardwareCallback hardwareCallback = new IHardwareCallback() {

		@Override
		public void callbackWorking(final Working working) {
			System.out.println("Working");
		}

		@Override
		public void callbackWarning(final Warning warning) {
			System.out.println("Warning");
		}

		@Override
		public void callbackException(final Exception e) {
			System.out.println("Exception");
		}

		@Override
		public void callbackErro(final Error error) {
			System.out.println("Error");
		}

		@Override
		public void callbackDebug(final Debug debug) {
			System.out.println("Debug");
		}
	};

	@Test
	void test() throws IOException {
		final ScaleServiceImpl service = new ScaleServiceImpl();

		final ScaleMonitoringThread scaleMonitor = service.new ScaleMonitoringThread();
		scaleMonitor.setHardwareCallback(this.hardwareCallback);
		new Thread(scaleMonitor).start();

		final String weight = service.getWeight();
		Assertions.assertEquals("1.500", weight);
	}

}
