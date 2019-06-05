package br.com.agencialove.tpa;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.Queue;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;

public class ScaleSimulatorAgent {

	private static CommPort port;
	private static final Queue<String> queue = new LinkedList<>();

	/*
	 * To run this agent it is necessary to execute the follow command line in linux:
	 *
	 * sudo socat -d -d -d pty,raw,echo=0,user=mendes,b9600,link=/dev/ttyVUSB0 pty,raw,echo=0,user=mendes,b9600,link=/dev/ttyUSB0
	 *
	 *
	 * Or, to use Null-Modem Emulator com0com [https://sourceforge.net/projects/com0com/] in Windows
	 */
	public static void main(final String[] args) throws NoSuchPortException, PortInUseException, IOException, InterruptedException {

		final CommPortIdentifier portId = Configuration.getScaleEmulatorPort();
		ScaleSimulatorAgent.port = portId.open("ScaleSimulatorAgent", 500);

		StringBuilder messageBuffer = new StringBuilder();
		while(true) {
			final InputStream is = ScaleSimulatorAgent.port.getInputStream();
			int a = 0;
			while(is.available() > 0) {
				a = is.available();
				final byte[] b = new byte[a];
				is.read(b, 0, a);
				messageBuffer.append(new String(b));
			}


			if(a > 0 && messageBuffer.length() > 0) {
				final String m = messageBuffer.toString();

				if(m.contains("\r") || m.contains("\n")) {
					final String[] messages = m.split("(\r|\n)+");
					int i = 0;
					for(; i < messages.length; ++i)
						ScaleSimulatorAgent.queue.add(messages[i]);

					--i;

					if(m.endsWith("\r") || m.endsWith("\n") || m.endsWith("\r\n"))
						messageBuffer = new StringBuilder();
					else {
						ScaleSimulatorAgent.queue.remove(messages[i]);
						messageBuffer = new StringBuilder(messages[i]);
					}
				}

				if(!ScaleSimulatorAgent.queue.isEmpty())
					ScaleSimulatorAgent.processQueue();

			}

			Thread.yield();

		}
	}

	private static void processQueue() throws IOException {
		final OutputStream os = ScaleSimulatorAgent.port.getOutputStream();
		if(!ScaleSimulatorAgent.queue.isEmpty()) {
			final String c = ScaleSimulatorAgent.queue.poll();
			if("P".equals(c) || "I".equals(c)) {
				final String resp = "+ 1.500 Kg\r";
				os.write(resp.getBytes());
				os.flush();
			}
		}

	}
}
