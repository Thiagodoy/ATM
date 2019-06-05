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

public class ArduinoSimulatorAgent {

	private static final Queue<String> queue = new LinkedList<>();
	private static CommPort port;
	private static boolean boxFullopened;
	private static boolean boxClosed;
	private static boolean boxBlocked;

	/*
	 * To run this agent it is necessary to execute the follow command line in linux:
	 *
	 * sudo socat -d -d -d pty,raw,echo=0,user=mendes,b9600,link=/dev/ttyVACM0 pty,raw,echo=0,user=mendes,b9600,link=/dev/ttyACM0
	 *
	 *
	 * Or, to use Null-Modem Emulator com0com [https://sourceforge.net/projects/com0com/] in Windows
	 */
	public static void main(final String[] args) throws NoSuchPortException, PortInUseException, IOException, InterruptedException {

		final CommPortIdentifier portId = Configuration.getArduinoEmulatorPort();
		ArduinoSimulatorAgent.port = portId.open("ArduinoSimulatorAgent", 500);

		StringBuilder messageBuffer = new StringBuilder();
		while(true) {
			final InputStream is = ArduinoSimulatorAgent.port.getInputStream();
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
						ArduinoSimulatorAgent.queue.add(messages[i]);

					--i;

					if(m.endsWith("\r") || m.endsWith("\n") || m.endsWith("\r\n"))
						messageBuffer = new StringBuilder();
					else {
						ArduinoSimulatorAgent.queue.remove(messages[i]);
						messageBuffer = new StringBuilder(messages[i]);
					}
				}

				if(!ArduinoSimulatorAgent.queue.isEmpty())
					ArduinoSimulatorAgent.processQueue();

			}

			Thread.yield();
		}
	}

	private static void send(final String msg) throws IOException {
		if(msg != null && !"".equals(msg.trim())) {
			final OutputStream os = ArduinoSimulatorAgent.port.getOutputStream();
			os.write(msg.getBytes());
			os.flush();
		}
	}

	private static void processQueue() throws IOException, InterruptedException {
		final String msg = ArduinoSimulatorAgent.queue.poll();
		/************************
		 ********* Alarm ********
		 ************************/
		if(ArduinoSimulatorAgent.COMMAND_ALARM_OFF.equals(msg)){
			System.out.println("ALARM_OFF");
			ArduinoSimulatorAgent.send(ArduinoSimulatorAgent.RESP_SUCCESS + '\r');
			return;
		}

		if(ArduinoSimulatorAgent.COMMAND_ALARM_RED.equals(msg)){
			System.out.println("ALARM_RED_ON");
			ArduinoSimulatorAgent.send(ArduinoSimulatorAgent.RESP_SUCCESS + '\r');
			return;
		}

		if(ArduinoSimulatorAgent.COMMAND_ALARM_YELLOW.equals(msg)){
			System.out.println("ALARM_YELLOW_ON");
			ArduinoSimulatorAgent.send(ArduinoSimulatorAgent.RESP_SUCCESS + '\r');
			return;
		}

		/************************
		 ****** Dispenser1 ******
		 ************************/
		if(ArduinoSimulatorAgent.COMMAND_STATE_D1.equals(msg)){
			System.out.println("IDLE_D1");
			ArduinoSimulatorAgent.send(ArduinoSimulatorAgent.RESP_IDLE_D + "1\r");
			return;
		}

		if(ArduinoSimulatorAgent.COMMAND_INVENTORY_D1.equals(msg)){
			System.out.println("INVENTORY_D1:10");
			ArduinoSimulatorAgent.send(ArduinoSimulatorAgent.RESP_INVENTORY_D + "1:10\r");
			return;
		}

		if(ArduinoSimulatorAgent.COMMAND_DISPENSE_D1.equals(msg)){
			System.out.println("RESP_WORKING_D1");
			ArduinoSimulatorAgent.send(ArduinoSimulatorAgent.RESP_WORKING_D + "1" + '\r');
			Thread.sleep(1000);
			System.out.println("CB_WORKING_D1");
			ArduinoSimulatorAgent.send(ArduinoSimulatorAgent.CALLBACK_WORKING_D + "1" + '\r');
			Thread.sleep(1000);
			System.out.println("READY_TO_REMOVE_D1");
			ArduinoSimulatorAgent.send(ArduinoSimulatorAgent.RESP_READY_TO_REMOVE_D + "1" + '\r');
			Thread.sleep(2000);
			System.out.println("IDLE_D1");
			ArduinoSimulatorAgent.send(ArduinoSimulatorAgent.RESP_IDLE_D + "1" + '\r');
			Thread.sleep(2000);
			return;
		}

		if(msg.startsWith(ArduinoSimulatorAgent.COMMAND_RECHARGE_D1)){
			ArduinoSimulatorAgent.send(ArduinoSimulatorAgent.RESP_SUCCESS + '\r');
			return;
		}

		/************************
		 ********* Box **********
		 ************************/
		if(ArduinoSimulatorAgent.COMMAND_BOX_STATE.equals(msg)){
			if(ArduinoSimulatorAgent.boxFullopened && ArduinoSimulatorAgent.boxClosed){
				System.out.println("FAIL");
				ArduinoSimulatorAgent.send(ArduinoSimulatorAgent.RESP_FAIL + '\r');
				return;
			}

			if(ArduinoSimulatorAgent.boxClosed){
				System.out.println("BOX_CLOSED");
				ArduinoSimulatorAgent.send(ArduinoSimulatorAgent.RESP_BOX_CLOSED + '\r');
				return;
			}

			if(ArduinoSimulatorAgent.boxFullopened)
				if(!ArduinoSimulatorAgent.boxBlocked){
					System.out.println("BOX_OPEN_FULL");
					ArduinoSimulatorAgent.send(ArduinoSimulatorAgent.RESP_BOX_OPEN_FULL + '\r');
					return;
				}else{
					System.out.println("BOX_BLOCKED");
					ArduinoSimulatorAgent.send(ArduinoSimulatorAgent.RESP_BOX_BLOCKED + '\r');
					return;
				}
			else
				if(!ArduinoSimulatorAgent.boxBlocked){
					System.out.println("BOX_OPEN_HALF");
					ArduinoSimulatorAgent.send(ArduinoSimulatorAgent.RESP_BOX_OPEN_HALF + '\r');
					return;
				}else{
					System.out.println("BOX_BLOCKED");
					ArduinoSimulatorAgent.send(ArduinoSimulatorAgent.RESP_BOX_BLOCKED + '\r');
					return;
				}
		}

		if(ArduinoSimulatorAgent.COMMAND_BOX_OPEN.equals(msg)){
			ArduinoSimulatorAgent.boxFullopened = true;
			ArduinoSimulatorAgent.boxClosed = false;
			System.out.println("BOX_WORKING_OPENING");
			ArduinoSimulatorAgent.send(ArduinoSimulatorAgent.CALLBACK_BOX_WORKING_OPENING + '\r');
			Thread.sleep(2000);
			System.out.println("BOX_OPEN_FULL");
			ArduinoSimulatorAgent.send(ArduinoSimulatorAgent.RESP_BOX_OPEN_FULL + '\r');
			return;
		}

		if(ArduinoSimulatorAgent.COMMAND_BOX_CLOSE.equals(msg)){
			ArduinoSimulatorAgent.boxFullopened = false;
			ArduinoSimulatorAgent.boxClosed = true;
			System.out.println("BOX_WORKING_CLOSING");
			ArduinoSimulatorAgent.send(ArduinoSimulatorAgent.CALLBACK_BOX_WORKING_CLOSING + '\r');
			Thread.sleep(2000);
			System.out.println("BOX_CLOSED");
			ArduinoSimulatorAgent.send(ArduinoSimulatorAgent.RESP_BOX_CLOSED + '\r');
			return;
		}

		/************************
		 ****** Keep alive ******
		 ************************/
		if(ArduinoSimulatorAgent.COMMAND_KEEP_ALIVE.equals(msg)){
			System.out.println("KEEP_ALIVE");
			ArduinoSimulatorAgent.send(ArduinoSimulatorAgent.RESP_SUCCESS + '\r');
			return;
		}

		/************************
		 ****** Scale ******
		 ************************/
		if(ArduinoSimulatorAgent.COMMAND_SCALE_TURNON.equals(msg)){
			System.out.println("SCALE_ON\r");
			return;
		}

		if(ArduinoSimulatorAgent.COMMAND_SCALE_PRINTP.equals(msg)){
			System.out.println("WEIGHTED: + 1.500 Kg");
			ArduinoSimulatorAgent.send("+  1.500 Kg\r");
			return;
		}

		if(ArduinoSimulatorAgent.COMMAND_SCALE_PRINTI.equals(msg)){
			System.out.println("WEIGHTED: + 1.500 Kg");
			ArduinoSimulatorAgent.send("+  1.500 Kg\r");
			return;
		}

		if(ArduinoSimulatorAgent.COMMAND_CUBOMETER_MEASURES.equals(msg)){
			System.out.println("MEASURED: 11x13.5x18x0");
			ArduinoSimulatorAgent.send("[RESP]MEASURES:11x13.5x18x0\r");
			return;
		}


		ArduinoSimulatorAgent.send(ArduinoSimulatorAgent.RESP_UNRECOGNIZED);
	}





	/******** Commands ***********/
	private static String COMMAND_STATE_D1        = "STATE_D1";
	private static String COMMAND_INVENTORY_D1    = "INVENTORY_D1";
	private static String COMMAND_DISPENSE_D1     = "DISPENSE_D1";
	private static String COMMAND_RECHARGE_D1     = "RECHARGE_D1:";
	private static String COMMAND_BOX_STATE       = "BOX_STATE";
	private static String COMMAND_BOX_OPEN        = "BOX_OPEN";
	private static String COMMAND_BOX_CLOSE       = "BOX_CLOSE";
	private static String COMMAND_ALARM_RED       = "ALARM_RED";
	private static String COMMAND_ALARM_YELLOW    = "ALARM_YELLOW";
	private static String COMMAND_ALARM_OFF       = "ALARM_OFF";

	private static String COMMAND_KEEP_ALIVE       = "KEEP_ALIVE";

	/******** ResponsesProtocol:: **********/
	private static String RESP_SUCCESS               = "[RESP]SUCCESS";
	private static String RESP_FAIL                  = "[RESP]FAIL";

	private static String RESP_IDLE_D                = "[RESP]IDLE_D";
	private static String RESP_INVENTORY_D           = "[RESP]INVENTORY_D";
	private static String RESP_WORKING_D             = "[RESP]WORKING_D";
	private static String RESP_READY_TO_REMOVE_D     = "[RESP]READY_TO_REMOVE_D";
	private static String RESP_BOX_CLOSED            = "[RESP]BOX_CLOSED";
	private static String RESP_BOX_OPEN_FULL         = "[RESP]BOX_OPEN_FULL";
	private static String RESP_BOX_OPEN_HALF         = "[RESP]BOX_OPEN_HALF";
	private static String RESP_BOX_BLOCKED           = "[RESP]BOX_BLOCKED";
	private static String RESP_UNRECOGNIZED          = "[RESP]UNRECOGNIZED";

	private static String CALLBACK_WORKING_D             = "[CB]WORKING_D";
	private static String CALLBACK_BOX_WORKING_OPENING   = "[CB]BOX_WORKING_OPENING";
	private static String CALLBACK_BOX_WORKING_CLOSING   = "[CB]BOX_WORKING_CLOSING";

	/******** Other simulations **********/
	private static String COMMAND_SCALE_TURNON           = "L";
	private static String COMMAND_SCALE_PRINTP           = "P";
	private static String COMMAND_SCALE_PRINTI           = "I";

	private static String COMMAND_CUBOMETER_MEASURES      = "MEASURES";
}
