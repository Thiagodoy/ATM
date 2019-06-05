package br.com.agencialove.tpa.hardware.scale;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.agencialove.tpa.hardware.IHardwareCallback;

public class ScaleServiceMock implements IScalePort {

	public IHardwareCallback hardwareCallback;


	@Override
	public void turnOn() throws IOException {

	}

	@Override
	public String getWeight() throws IOException {
		return "150";
	}


	public class ScaleMonitoringThread implements Runnable{
		@Override
		public void run() {
			while(true) {
				try {
					ScaleServiceMock.this.turnOn();
					Thread.sleep(3000);
				} catch (final InterruptedException e) {
					Logger.getAnonymousLogger().log(Level.SEVERE, "Um erro ocorreu ao monitorar a balança!", e);
					e.printStackTrace();
					ScaleServiceMock.this.hardwareCallback.callbackException(e);
				} catch (final IOException e) {
					Logger.getAnonymousLogger().log(Level.SEVERE, "Um erro ocorreu ao manter a balança ligada!", e);
					e.printStackTrace();
					ScaleServiceMock.this.hardwareCallback.callbackException(e);
				}
			}
		}

		public void setHardwareCallback(final IHardwareCallback pHardwareCallback) {
			ScaleServiceMock.this.hardwareCallback = pHardwareCallback;
		}
	}

}
