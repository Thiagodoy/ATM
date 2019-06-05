package br.com.agencialove.tpa.hardware.arduino;

public class ArduinoMessage {

	private ArduinoProtocol protocol;
	private String value;

	public ArduinoMessage(final ArduinoProtocol pProtocolMessage) {
		this.protocol = pProtocolMessage;
	}

	public ArduinoMessage(final ArduinoProtocol pProtocolMessage, final String pValue) {
		this.protocol = pProtocolMessage;
		this.value = pValue;
	}

	public static ArduinoMessage parseString(final String message) {

		for(final ArduinoProtocol resp : ArduinoProtocol.values()) {
			if(resp.getMessage().equals(message.trim()))
				return new ArduinoMessage(resp);
		}
		if(message.startsWith("[RESP]INVENTORY_D")) {
			if(message.startsWith("[RESP]INVENTORY_D1"))
				return new ArduinoMessage(ArduinoProtocol.RESP_INVENTORY_D1,ArduinoMessage.getValue(message));
			if(message.startsWith("[RESP]INVENTORY_D2"))
				return new ArduinoMessage(ArduinoProtocol.RESP_INVENTORY_D2,ArduinoMessage.getValue(message));
			if(message.startsWith("[RESP]INVENTORY_D3"))
				return new ArduinoMessage(ArduinoProtocol.RESP_INVENTORY_D3,ArduinoMessage.getValue(message));
			if(message.startsWith("[RESP]INVENTORY_D4"))
				return new ArduinoMessage(ArduinoProtocol.RESP_INVENTORY_D4,ArduinoMessage.getValue(message));
		}else if(message.startsWith("[DEBUG]")) {
			return new ArduinoMessage(ArduinoProtocol.DEBUG,message.replace("[DEBUG]", ""));
		}

		return null;
	}

	private static String getValue(final String message) {
		final String[] ret = message.split(":");
		if(ret.length < 2)
			return null;
		return ret[1];
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(final String value) {
		this.value = value;
	}

	public ArduinoProtocol getProtocol() {
		return this.protocol;
	}

	public void setProtocol(final ArduinoProtocol protocolMessage) {
		this.protocol = protocolMessage;
	}

}
