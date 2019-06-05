package br.com.agencialove.tpa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;

public class TestComm{

	public static void main(final String[] args) throws NoSuchPortException, PortInUseException, IOException {
		System.setProperty("gnu.io.rxtx.SerialPorts", "COM9;COM10");
		System.setProperty("gnu.io.rxtx.ParallelPorts", "/dev/usb/lp0");

		final Enumeration<CommPortIdentifier> commIds = CommPortIdentifier.getPortIdentifiers();
		while(commIds.hasMoreElements())
			System.out.println(commIds.nextElement().getName());

		final CommPortIdentifier commId = CommPortIdentifier.getPortIdentifier("COM10");
		final CommPort port = commId.open("TestComm",50);
		port.getOutputStream().write("I\r".getBytes());

		final BufferedReader br = new BufferedReader(new InputStreamReader(port.getInputStream()));
		System.out.println(br.readLine());

		port.close();
	}
}
