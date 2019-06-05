package br.com.agencialove.tpa.hardware.arduino;

public class Working implements IMessage{

	private String message;

	public Working(final ArduinoMessage am) {
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
