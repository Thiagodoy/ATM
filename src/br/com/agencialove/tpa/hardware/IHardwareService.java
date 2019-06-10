package br.com.agencialove.tpa.hardware;

import java.io.IOException;

import br.com.agencialove.tpa.hardware.arduino.CollectBoxState;
import br.com.agencialove.tpa.hardware.arduino.Dispenser;
import br.com.agencialove.tpa.hardware.arduino.DispenserState;
import br.com.agencialove.tpa.model.PackageMeasures;

public interface IHardwareService {
	DispenserState getDispenserState(Dispenser d) throws IOException;

	DispenserState dispensePack(Dispenser d) throws IOException;

	DispenserState recharge(Dispenser d, int quantity) throws IOException;

	int getInventory(Dispenser d) throws IOException;

	CollectBoxState getBoxState(Integer boxId) throws IOException;

	CollectBoxState openBox(Integer boxId) throws IOException;

	CollectBoxState closeBox(Integer boxId) throws IOException;

	void turnOnScale() throws IOException;

	PackageMeasures getMeasures() throws IOException;

	void alarmWarning() throws IOException;

	void alarmError() throws IOException;

	void alarmOff() throws IOException;

	void setHardwareCallback(IHardwareCallback callback);

	String getVolumeMeasured();

	void setVolumeMeasured(String volumeMeasured);
}
