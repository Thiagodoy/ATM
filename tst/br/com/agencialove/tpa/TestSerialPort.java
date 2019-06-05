package br.com.agencialove.tpa;

import gnu.io.CommPortIdentifier;

public class TestSerialPort {
	public static void main(final String[] args) {
		final CommPortIdentifier arduinoPort = Configuration.getArduinoPort();
		System.out.println(arduinoPort.getName());

		final CommPortIdentifier scalePort = Configuration.getScalePort();
		System.out.println(scalePort.getName());

		final CommPortIdentifier ticketPrinterPort = Configuration.getTricketPrinterPort();
		System.out.println(ticketPrinterPort.getName());

		final CommPortIdentifier labelPrinterPort = Configuration.getTricketPrinterPort();
		System.out.println(labelPrinterPort.getName());
	}
}
