package br.com.agencialove.tpa.hardware.arduino;

import java.io.IOException;

public interface IArduinoService {

	ArduinoMessage sendCommand(ArduinoCommands command) throws IOException;

	ArduinoMessage sendCommand(ArduinoCommands command, Integer quantity) throws IOException;

	ArduinoMessage getResponse();

}
