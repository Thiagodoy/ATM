package br.com.agencialove.tpa.hardware.arduino;

public class Error implements IMessage{

	private String message;

	public Error(final ArduinoMessage am) {
		this.message = am.getProtocol().getMessage();
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
