package br.com.agencialove.tpa;

import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.print.PrintService;

import gnu.io.CommPortIdentifier;

public class Configuration {
	private static final String FILE_NAME = "br/com/agencialove/tpa/config.properties"; //$NON-NLS-1$
	private static Map<String,String> CONFIGURATIONS = new HashMap<>();
	private static Map<String,CommPortIdentifier> PORTS = new HashMap<>();

	static {
		final File file = new File(Configuration.FILE_NAME);

		try {
			if(file.exists())
				Configuration.loadFile(file);
			else {

				Configuration.CONFIGURATIONS.put("arduino.port", "/dev/ttyACM0");
				Configuration.CONFIGURATIONS.put("scale.port", "/dev/ttyUSB0");
				Configuration.CONFIGURATIONS.put("printer.label", "Zebra");
				Configuration.CONFIGURATIONS.put("printer.label.port", "/dev/usb/lp1");
				Configuration.CONFIGURATIONS.put("printer.ticket", "Elgin");
				Configuration.CONFIGURATIONS.put("printer.ticket.port", "/dev/usb/lp0");
				Configuration.CONFIGURATIONS.put("weight.limit.upper", "30");
				Configuration.CONFIGURATIONS.put("weight.limit.lower", "0.2");

				Configuration.save();
			}

			final String fs = System.getProperty("path.separator");
			System.setProperty("gnu.io.rxtx.SerialPorts", Configuration.CONFIGURATIONS.get("arduino.port") + fs	+ Configuration.CONFIGURATIONS.get("scale.port"));
			System.setProperty("gnu.io.rxtx.ParallelPorts", Configuration.CONFIGURATIONS.get("printer.label.port") + System.getProperty("file.separator") +Configuration.CONFIGURATIONS.get("printer.ticket.port"));

			final String scaleEmulator = Configuration.CONFIGURATIONS.get("scale.emulator.port");
			final String arduinoEmulator = Configuration.CONFIGURATIONS.get("arduino.emulator.port");

			if(scaleEmulator != null) {
				final String serialPorts = System.getProperty("gnu.io.rxtx.SerialPorts");
				System.setProperty("gnu.io.rxtx.SerialPorts", serialPorts + fs + scaleEmulator);
			}

			if(arduinoEmulator != null) {
				final String serialPorts = System.getProperty("gnu.io.rxtx.SerialPorts");
				System.setProperty("gnu.io.rxtx.SerialPorts", serialPorts + fs + arduinoEmulator);
			}

			final Enumeration<CommPortIdentifier> portIds = CommPortIdentifier.getPortIdentifiers();
			while(portIds.hasMoreElements()) {
				final CommPortIdentifier portId = portIds.nextElement();
				Configuration.PORTS.put(portId.getName(), portId);
			}
		} catch (final IOException e) {
			Logger.getAnonymousLogger().log(Level.SEVERE, "Não foi possível criar ou carregar o arquivo de comunicação: " + Configuration.FILE_NAME, e);;
		}
	}

	public static String getString(final String key) {
		return Configuration.CONFIGURATIONS.get(key);
	}

	private static void saveFile(final File file) throws IOException {
		final FileOutputStream fos = new FileOutputStream(file);

		for(final Map.Entry<String, String> pair : Configuration.CONFIGURATIONS.entrySet()) {
			fos.write(pair.getKey().getBytes());
			fos.write("=".getBytes());
			fos.write(pair.getValue().getBytes());
			fos.write("\r\n".getBytes());
		}

		fos.flush();
		fos.close();
	}

	private static void loadFile(final File file) throws IOException {
		final FileInputStream fis = new FileInputStream(file);
		final InputStreamReader isr = new InputStreamReader(fis);
		final BufferedReader br = new BufferedReader(isr);
		Configuration.CONFIGURATIONS = new HashMap<>();

		int i = 0;
		String line = br.readLine();
		while(line != null) {
			++i;
			final String[] pair = line.split("=");
			if(pair.length != 2)
				throw new RuntimeException("Arquivo de configurações mal formado na linha " + i + " " + line);

			Configuration.CONFIGURATIONS.put(pair[0], pair[1]);
			line = br.readLine();
		}

		fis.close();
		isr.close();
		br.close();
	}

	private static void createFile(final File file) throws IOException {
		Configuration.createDir(file.getParentFile());
		file.createNewFile();
	}

	private static void createDir(final File dir) {
		if(dir != null && !dir.exists()) {
			final File parentDir = dir.getParentFile();
			Configuration.createDir(parentDir);
			dir.mkdir();
		}
	}

	public static Map<String,String> getConfigurations() {
		return new HashMap<>(Configuration.CONFIGURATIONS);
	}

	public static void setConfigurations(final Map<String, String> config) {
		Configuration.CONFIGURATIONS.clear();
		Configuration.CONFIGURATIONS.putAll(config);
	}

	public static void save() {
		final File file = new File(Configuration.FILE_NAME);

		try {
			if(!file.exists())
				Configuration.createFile(file);

			Configuration.saveFile(file);
		} catch (final IOException e) {
			Logger.getAnonymousLogger().log(Level.SEVERE, "Não foi possível criar ou carregar o arquivo de comunicação: " + Configuration.FILE_NAME, e);;
		}

	}

	public static CommPortIdentifier getScalePort() {
		final String portName = Configuration.CONFIGURATIONS.get("scale.port");
		return Configuration.getPort(portName);
	}

	public static CommPortIdentifier getArduinoPort() {
		final String portName = Configuration.CONFIGURATIONS.get("arduino.port");
		return Configuration.getPort(portName);
	}


	public static CommPortIdentifier getTricketPrinterPort() {
		final String portName = Configuration.CONFIGURATIONS.get("printer.ticket.port");
		return Configuration.getPort(portName);
	}

	public static CommPortIdentifier getLabelPrinterPort() {
		final String portName = Configuration.CONFIGURATIONS.get("printer.label.port");
		return Configuration.getPort(portName);
	}

	public static CommPortIdentifier getScaleEmulatorPort() {
		final String portName = Configuration.CONFIGURATIONS.get("scale.emulator.port");
		return Configuration.getPort(portName);
	}

	public static CommPortIdentifier getArduinoEmulatorPort() {
		final String portName = Configuration.CONFIGURATIONS.get("arduino.emulator.port");
		return Configuration.getPort(portName);
	}

	private static CommPortIdentifier getPort(final String portName) {

		return Configuration.PORTS.get(portName);
	}

	public static List<String> getSerialPorts() {
		final List<String> ret = new ArrayList<>();

		for(final Map.Entry<String, CommPortIdentifier> entry : Configuration.PORTS.entrySet()) {
			if(entry.getValue().getPortType() == CommPortIdentifier.PORT_SERIAL)
				ret.add(entry.getKey());
		}
		return ret;

	}

	public static List<String> getPrinters() {
		final PrintService[] services = PrinterJob.lookupPrintServices();
		final List<String> ret = new ArrayList<>();
		for (int i = 0; i < services.length; i++) {
			ret.add(services[i].getName());
		}

		return ret;
	}

	public static PrintService findPrintService(String printerName) {

		printerName = printerName.toLowerCase();

		PrintService service = null;

		// Get array of all print services
		final PrintService[] services = PrinterJob.lookupPrintServices();

		// Retrieve a print service from the array
		for (int index = 0; service == null && index < services.length; index++) {

			if (services[index].getName().toLowerCase().indexOf(printerName) >= 0) {
				service = services[index];
			}
		}

		// Return the print service
		return service;
	}

	public static double getWeightUpperLimit() {
		return 30;
	}

	public static double getWeightLowerLimit() {
		return 0.2;
	}


}
