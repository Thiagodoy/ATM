package br.com.agencialove.tpa.hardware.scale;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.agencialove.tpa.Configuration;
import br.com.agencialove.tpa.hardware.IHardwareCallback;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;

public class ScaleServiceImpl implements IScalePort {

	private static CommPort PORT;
	private IHardwareCallback hardwareCallback;
	private StringBuilder messageBuffer = new StringBuilder();

	static {
		ScaleServiceImpl.openPort();
	}

	public static void openPort() {
		if(ScaleServiceImpl.PORT == null) {
			try {
				final CommPortIdentifier portId = Configuration.getScalePort();
				if(portId != null)
					ScaleServiceImpl.PORT = portId.open("TPA.ScaleServiceImpl", 500);
				else {
					final String msg = "A porta da balança não foi encontrada";
					Logger.getAnonymousLogger().log(Level.SEVERE, msg, new IOException(msg));
				}
			} catch (final PortInUseException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public class ScaleMonitoringThread implements Runnable{

		@Override
		public void run() {
			while(true) {
				try {
					ScaleServiceImpl.this.turnOn();
					Thread.sleep(3000);
				} catch (final InterruptedException e) {
					Logger.getAnonymousLogger().log(Level.SEVERE, "Um erro ocorreu ao monitorar a balança!", e);
					e.printStackTrace();
					ScaleServiceImpl.this.hardwareCallback.callbackException(e);
				} catch (final IOException e) {
					Logger.getAnonymousLogger().log(Level.SEVERE, "Um erro ocorreu ao manter a balança ligada!", e);
					e.printStackTrace();
					ScaleServiceImpl.this.hardwareCallback.callbackException(e);
				}
			}
		}

		public void setHardwareCallback(final IHardwareCallback pHardwareCallback) {
			ScaleServiceImpl.this.hardwareCallback = pHardwareCallback;
		}
	}

	@Override
	public void turnOn() throws IOException {
		final OutputStream os = ScaleServiceImpl.PORT.getOutputStream();
		os.write("L\r".getBytes());
		os.flush();
	}

	private void print() throws IOException {
		final OutputStream os = ScaleServiceImpl.PORT.getOutputStream();
		os.write("P\r".getBytes());
		os.flush();
	}

	@Override
	public String getWeight() throws IOException {
		final StringBuilder ret = new StringBuilder();
		try {
			//tries 5 times to communicate with scale
			String message = null;
			for(int i = 0; i < 5; ++i) {
				this.turnOn();
				Thread.sleep(1000);
				this.print();
				Thread.sleep(500);
				message = this.receive();
				if(message != null && !"".equals(message.trim()))
					break;
			}

			if(message != null)
				for(final char c : message.toCharArray()){
					if((c >= '0' && c <= '9') || c == '.')
						ret.append(c);
				}
		} catch (final InterruptedException e) {
			Logger.getAnonymousLogger().log(Level.SEVERE,"Erro ao aguardar para comunicar com a balança", e);
		}


		return ret.toString();
	}



	private String receive() throws IOException {
		String ret = null;

		final InputStream is = ScaleServiceImpl.PORT.getInputStream();
		int a = is.available();
		while(a > 0) {
			final char b = (char) is.read();
			if(b == '\r' || b == '\n') {
				if(this.messageBuffer.length() > 0) {
					ret = this.messageBuffer.toString();
					ScaleServiceImpl.this.messageBuffer = new StringBuilder();
					a = 0;
				}
			} else {
				ScaleServiceImpl.this.messageBuffer.append(b);
				a = is.available();
			}
		}

		return ret;
	}
}

