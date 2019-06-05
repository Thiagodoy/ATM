package br.com.agencialove.tpa.hardware.arduino;

public class Warning implements IMessage{

	private String message;
	private Dispenser dispenser;
	private DispenserState dispenserState;

	public Warning(final ArduinoMessage am) {
		this.message = am.getProtocol().getMessage();
		this.dispenser = am.getProtocol().getDispenser();
		this.dispenserState = am.getProtocol().getDispenserState();
	}

	@Override
	public String getMessage() {
		return this.message;
	}

	@Override
	public void setMessage(final String message) {
		this.message = message;
	}

	public Dispenser getDispenser() {
		return this.dispenser;
	}

	public void setDispenser(final Dispenser dispenser) {
		this.dispenser = dispenser;
	}

	public DispenserState getDispenserState() {
		return this.dispenserState;
	}

	public void setDispenserState(final DispenserState dispenserState) {
		this.dispenserState = dispenserState;
	}
}
