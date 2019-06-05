package br.com.agencialove.tpa.hardware.scale;

import java.io.IOException;

public interface IScalePort {

	void turnOn() throws IOException;

	String getWeight() throws IOException;

}
