package br.com.agencialove.tpa.hardware.arduino;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.agencialove.tpa.Configuration;
import br.com.agencialove.tpa.hardware.IHardwareCallback;
import br.com.agencialove.tpa.hardware.arduino.ArduinoProtocol.State;
import br.com.agencialove.tpa.hardware.arduino.ArduinoProtocol.Type;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;

public class ArduinoServiceImpl implements IArduinoService {

	private static CommPort PORT;
	private static final long COMMUNICATION_TIMEOUT = 10000;
	private IHardwareCallback hardwareCallback;
	private final Queue<ArduinoMessage> inQueue = new ConcurrentLinkedQueue<>();
	private final Queue<String> messagesQueue = new ConcurrentLinkedQueue<>();
	private StringBuilder messageBuffer = new StringBuilder();


	static {
		ArduinoServiceImpl.openPort();
	}

	public static void openPort() {
		if(ArduinoServiceImpl.PORT == null) {
			try {
				final CommPortIdentifier portId = Configuration.getArduinoPort();
				if(portId != null)
					ArduinoServiceImpl.PORT = portId.open("TPA.ArduinoServiceImpl", 500);
				else {
					final String msg = "A porta do Arduino não foi encontrada";
					Logger.getAnonymousLogger().log(Level.SEVERE, msg, new IOException(msg));
				}
			} catch (final PortInUseException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public ArduinoMessage sendCommand(final ArduinoCommands command) throws IOException {
		return this.sendCommand(command, null);
	}

	@Override
	public ArduinoMessage sendCommand(final ArduinoCommands command, final Integer quantity) throws IOException {
		String m = command.getCommand();
		if(quantity != null)
			m += Integer.toString(quantity);

		final OutputStream os = ArduinoServiceImpl.PORT.getOutputStream();
		os.write(m.getBytes());
		os.write('\r');
		os.flush();

		final long starttime = System.currentTimeMillis();
		final long stoptime = starttime + ArduinoServiceImpl.COMMUNICATION_TIMEOUT;

		while(stoptime > System.currentTimeMillis()) {
			final ArduinoMessage ret = this.getResponse();
			if(ret != null)
				return ret;

			Thread.yield();
		}


		if(stoptime < System.currentTimeMillis())
			throw new IOException("O tempo limite de espera pela resposta de um comando foi excedido!"); //$NON-NLS-1$

		return null;
	}

	public class ArduinoMonitoringThread implements Runnable {
		@Override
		public void run() {
			int a;
			try {
				a = ArduinoServiceImpl.PORT.getInputStream().available();
				if(a > 0)
					ArduinoServiceImpl.PORT.getInputStream().read(new byte[a]);
			} catch (final IOException e) {
				Logger.getAnonymousLogger().log(Level.SEVERE, "Um erro ocorreu ao monitorar o Arduino", e); //$NON-NLS-1$
				e.printStackTrace();
			}

			while(true) {
				try {

					this.readComm();
					if(ArduinoServiceImpl.this.messagesQueue.isEmpty()) {
						Thread.yield();
						continue;
					}

					final String message = ArduinoServiceImpl.this.messagesQueue.poll();

					if(message != null) {
						final ArduinoMessage am = ArduinoMessage.parseString(message);
						if(am != null) {
							/* if the message is a response */
							if(am.getProtocol().getType() == Type.RESP)
								ArduinoServiceImpl.this.inQueue.add(am);
							else if(am.getProtocol().getType() == Type.CALLBACK) {
								/* if the message is a callback */
								if(am.getProtocol().getState() == State.WORKING)
									ArduinoServiceImpl.this.hardwareCallback.callbackWorking(new Working(am));
								else if(am.getProtocol().getState() == State.WARNING)
									ArduinoServiceImpl.this.hardwareCallback.callbackWarning(new Warning(am));
								else if(am.getProtocol().getState() == State.ERROR)
									ArduinoServiceImpl.this.hardwareCallback.callbackErro(new Error(am));
							}else if(am.getProtocol().getType() == Type.DEBUG) {
								ArduinoServiceImpl.this.hardwareCallback.callbackDebug(new Debug(am));
							}
						}else
							Logger.getAnonymousLogger().log(Level.SEVERE, "Mensagem do Arduino não reconhecida: " + message); //$NON-NLS-1$
					}
				} catch (final Exception e) {
					Logger.getAnonymousLogger().log(Level.SEVERE, "Um erro ocorreu ao monitorar o Arduino", e); //$NON-NLS-1$
					e.printStackTrace();
					ArduinoServiceImpl.this.hardwareCallback.callbackException(e);
				}
			}

		}

		private void readComm() throws IOException {
			final InputStream is = ArduinoServiceImpl.PORT.getInputStream();
			int a = is.available();
			while(a > 0) {
				final char b = (char) is.read();
				if(b == '\r' || b == '\n') {
					if(ArduinoServiceImpl.this.messageBuffer.length() > 0) {
						ArduinoServiceImpl.this.messagesQueue.add(ArduinoServiceImpl.this.messageBuffer.toString().trim());
						ArduinoServiceImpl.this.messageBuffer = new StringBuilder();
						a = 0;
					}
				}else {
					ArduinoServiceImpl.this.messageBuffer.append(b);
					a = is.available();
				}
			}
		}

		public void setHardwareCallback(final IHardwareCallback pHardwareFailCallback) {
			ArduinoServiceImpl.this.hardwareCallback = pHardwareFailCallback;
		}

	}

	@Override
	public ArduinoMessage getResponse() {
		for(final ArduinoMessage message : this.inQueue)
			if(message.getProtocol().getType() == Type.RESP) {
				this.inQueue.remove(message);
				return message;
			}

		return null;
	}
}
