package br.com.agencialove.tpa.hardware.arduino;

public class Debug implements IMessage{

	private String message;

	public Debug(final ArduinoMessage am) {
		this.message = am.getValue();
	}

	@Override
	public String getMessage() {
		return this.message;
	}

	@Override
	public void setMessage(final String message) {
		this.message = message;
	}
}
