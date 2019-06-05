package br.com.agencialove.tpa.hardware.arduino;

public enum ArduinoCommands {
	STATE_D1 ("STATE_D1",Type.STATE),
	STATE_D2 ("STATE_D2",Type.STATE),
	STATE_D3 ("STATE_D3",Type.STATE),
	STATE_D4 ("STATE_D4",Type.STATE),
	FULLSTATE ("FULLSTATE",Type.STATE),

	INVENTORY_D1 ("INVENTORY_D1",Type.INVENTORY),
	INVENTORY_D2 ("INVENTORY_D2",Type.INVENTORY),
	INVENTORY_D3 ("INVENTORY_D3",Type.INVENTORY),
	INVENTORY_D4 ("INVENTORY_D4",Type.INVENTORY),

	DISPENSE_D1 ("DISPENSE_D1",Type.DISPENSE),
	DISPENSE_D2 ("DISPENSE_D2",Type.DISPENSE),
	DISPENSE_D3 ("DISPENSE_D3",Type.DISPENSE),
	DISPENSE_D4 ("DISPENSE_D4",Type.DISPENSE),

	RECHARGE_D1 ("RECHARGE_D1:",Type.RECHARGE),
	RECHARGE_D2 ("RECHARGE_D2:",Type.RECHARGE),
	RECHARGE_D3 ("RECHARGE_D3:",Type.RECHARGE),
	RECHARGE_D4 ("RECHARGE_D4:",Type.RECHARGE),

	ALARM_RED ("ALARM_RED",Type.ALARM),
	ALARM_YELLOW ("ALARM_YELLOW",Type.ALARM),
	ALARM_OFF ("ALARM_OFF",Type.ALARM),

	STATE_BOX ("BOX_STATE",Type.BOX),
	OPEN_BOX ("BOX_OPEN",Type.BOX),
	CLOSE_BOX ("BOX_CLOSE",Type.BOX),
	WEIGHT ("WEIGHT",Type.SCALE),

	KEEP_ALIVE ("KEEP_ALIVE",Type.COMM);

	private String command;
	private Type type;

	private ArduinoCommands(final String pCommand, final Type pType) {
		this.command = pCommand;
		this.setType(pType);
	}

	public String getCommand() {
		return this.command;
	}

	public void setCommand(final String command) {
		this.command = command;
	}

	public Type getType() {
		return this.type;
	}

	public void setType(final Type type) {
		this.type = type;
	}

	public enum Type{
		STATE, INVENTORY, DISPENSE, RECHARGE, ALARM, BOX, SCALE, COMM
	}
}
