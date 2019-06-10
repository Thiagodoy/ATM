package br.com.agencialove.tpa.hardware;

import java.io.IOException;

import br.com.agencialove.tpa.hardware.arduino.CollectBoxState;
import br.com.agencialove.tpa.hardware.arduino.Dispenser;
import br.com.agencialove.tpa.hardware.arduino.DispenserState;
import br.com.agencialove.tpa.model.PackageMeasures;

public class HardwareServiceMock2 implements IHardwareService {

	private final HardwareServiceImpl hardwareService;
	private String volumeMeasure;

	public HardwareServiceMock2(final IHardwareCallback pCallback) {
		this.hardwareService = new HardwareServiceImpl(pCallback);
	}

	@Override
	public DispenserState getDispenserState(final Dispenser d) throws IOException {
		return DispenserState.IDLE;
	}

	@Override
	public DispenserState dispensePack(final Dispenser d) throws IOException {
		return DispenserState.IDLE;
	}

	@Override
	public DispenserState recharge(final Dispenser d, final int quantity) throws IOException {
		return DispenserState.IDLE;
	}

	@Override
	public int getInventory(final Dispenser d) throws IOException {
		return 10;
	}

	@Override
	public CollectBoxState getBoxState(Integer boxId) throws IOException {
		return this.hardwareService.getBoxState(boxId);
	}

	@Override
	public CollectBoxState openBox(Integer boxId) throws IOException {
		return this.hardwareService.openBox(boxId);
	}

	@Override
	public CollectBoxState closeBox(Integer boxId) throws IOException {
		return this.hardwareService.closeBox(boxId);
	}

	@Override
	public void turnOnScale() throws IOException {
		this.hardwareService.turnOnScale();
	}

	@Override
	public PackageMeasures getMeasures() throws IOException {
		return this.hardwareService.getMeasures();
	}

	@Override
	public void alarmWarning() throws IOException {
		this.hardwareService.alarmWarning();
	}

	@Override
	public void alarmError() throws IOException {
		this.hardwareService.alarmError();
	}

	@Override
	public void alarmOff() throws IOException {
		this.hardwareService.alarmOff();
	}

	@Override
	public void setHardwareCallback(final IHardwareCallback callback) {
		this.hardwareService.setHardwareCallback(callback);
	}

	@Override
	public String getVolumeMeasured() {
		return this.volumeMeasure;
	}

	@Override
	public void setVolumeMeasured(final String volumeMeasured) {
		this.volumeMeasure = volumeMeasured;
	}

}
