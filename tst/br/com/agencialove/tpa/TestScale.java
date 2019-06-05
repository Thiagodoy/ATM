package br.com.agencialove.tpa;

import java.io.IOException;
import java.io.OutputStream;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;

public class TestScale {
	public static void main(final String[] args) throws IOException, PortInUseException {
		final CommPortIdentifier portId = Configuration.getScalePort();
		final CommPort port = portId.open("TPA.TestScale",50);

		System.out.println(port.getName());
		final OutputStream os = port.getOutputStream();
		System.out.println("L");
		os.write("L\r".getBytes());
		System.out.println("I");
		os.write("I\r".getBytes());
	}
}
