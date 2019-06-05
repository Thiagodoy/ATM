package br.com.agencialove.tpa.hardware.arduino;

public enum ArduinoProtocol {
	RESP_SUCCESS ("[RESP]SUCCESS", ArduinoProtocol.Type.RESP, ArduinoProtocol.Object.NONE, ArduinoProtocol.State.NONE, Dispenser.NONE),
	RESP_FAIL ("[RESP]FAIL", ArduinoProtocol.Type.RESP, ArduinoProtocol.Object.NONE, ArduinoProtocol.State.ERROR, Dispenser.NONE),

	RESP_IDLE_D1 ("[RESP]IDLE_D1", ArduinoProtocol.Type.RESP, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.IDLE, Dispenser.A),
	RESP_INVENTORY_D1 ("[RESP]INVENTORY_D1", ArduinoProtocol.Type.RESP, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.NONE, Dispenser.A),
	RESP_WORKING_D1 ("[RESP]WORKING_D1", ArduinoProtocol.Type.RESP, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.WORKING, Dispenser.A),
	RESP_WARNING_EMPTY_D1 ("[RESP]WARNING_EMPTY_D1", ArduinoProtocol.Type.RESP, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.WARNING, Dispenser.A),
	RESP_WARNING_LOW_LEVEL_D1 ("[RESP]WARNING_LOW_LEVEL_D1", ArduinoProtocol.Type.RESP, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.WARNING, Dispenser.A),
	RESP_ERROR_NOT_DISPENSED_D1 ("[RESP]ERROR_NOT_DISPENSED_D1", ArduinoProtocol.Type.RESP, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.ERROR, Dispenser.A),
	RESP_ERROR_BLOCKED_D1 ("[RESP]ERROR_BLOCKED_D1", ArduinoProtocol.Type.RESP, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.ERROR_BLOCKED, Dispenser.A),

	RESP_IDLE_D2 ("[RESP]IDLE_D2", ArduinoProtocol.Type.RESP, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.IDLE, Dispenser.B),
	RESP_INVENTORY_D2 ("[RESP]INVENTORY_D2", ArduinoProtocol.Type.RESP, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.NONE, Dispenser.B),
	RESP_WORKING_D2 ("[RESP]WORKING_D2", ArduinoProtocol.Type.RESP, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.WORKING, Dispenser.B),
	RESP_WARNING_EMPTY_D2 ("[RESP]WARNING_EMPTY_D2", ArduinoProtocol.Type.RESP, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.WARNING, Dispenser.B),
	RESP_WARNING_LOW_LEVEL_D2 ("[RESP]WARNING_LOW_LEVEL_D2", ArduinoProtocol.Type.RESP, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.WARNING, Dispenser.B),
	RESP_ERROR_NOT_DISPENSED_D2 ("[RESP]ERROR_NOT_DISPENSED_D2", ArduinoProtocol.Type.RESP, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.ERROR, Dispenser.B),
	RESP_ERROR_BLOCKED_D2 ("[RESP]ERROR_BLOCKED_D2", ArduinoProtocol.Type.RESP, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.ERROR_BLOCKED, Dispenser.B),

	RESP_IDLE_D3 ("[RESP]IDLE_D3", ArduinoProtocol.Type.RESP, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.IDLE, Dispenser.C),
	RESP_INVENTORY_D3 ("[RESP]INVENTORY_D3", ArduinoProtocol.Type.RESP, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.NONE, Dispenser.C),
	RESP_WORKING_D3 ("[RESP]WORKING_D3", ArduinoProtocol.Type.RESP, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.WORKING, Dispenser.C),
	RESP_WARNING_EMPTY_D3 ("[RESP]WARNING_EMPTY_D3", ArduinoProtocol.Type.RESP, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.WARNING, Dispenser.C),
	RESP_WARNING_LOW_LEVEL_D3 ("[RESP]WARNING_LOW_LEVEL_D3", ArduinoProtocol.Type.RESP, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.WARNING, Dispenser.C),
	RESP_ERROR_NOT_DISPENSED_D3 ("[RESP]ERROR_NOT_DISPENSED_D3", ArduinoProtocol.Type.RESP, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.ERROR, Dispenser.C),
	RESP_ERROR_BLOCKED_D3 ("[RESP]ERROR_BLOCKED_D3", ArduinoProtocol.Type.RESP, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.ERROR_BLOCKED, Dispenser.C),

	RESP_IDLE_D4 ("[RESP]IDLE_D4", ArduinoProtocol.Type.RESP, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.IDLE, Dispenser.D),
	RESP_INVENTORY_D4 ("[RESP]INVENTORY_D4", ArduinoProtocol.Type.RESP, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.NONE, Dispenser.D),
	RESP_WORKING_D4 ("[RESP]WORKING_D4", ArduinoProtocol.Type.RESP, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.WORKING, Dispenser.D),
	RESP_WARNING_EMPTY_D4 ("[RESP]WARNING_EMPTY_D4", ArduinoProtocol.Type.RESP, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.WARNING, Dispenser.D),
	RESP_WARNING_LOW_LEVEL_D4 ("[RESP]WARNING_LOW_LEVEL_D4", ArduinoProtocol.Type.RESP, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.WARNING, Dispenser.D),
	RESP_ERROR_NOT_DISPENSED_D4 ("[RESP]ERROR_NOT_DISPENSED_D4", ArduinoProtocol.Type.RESP, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.ERROR, Dispenser.D),
	RESP_ERROR_BLOCKED_D4 ("[RESP]ERROR_BLOCKED_D4", ArduinoProtocol.Type.RESP, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.ERROR_BLOCKED, Dispenser.D),


	RESP_BOX_CLOSED ("[RESP]BOX_CLOSED", ArduinoProtocol.Type.RESP, ArduinoProtocol.Object.BOX, ArduinoProtocol.State.CLOSED, Dispenser.NONE),
	RESP_BOX_OPEN_FULL ("[RESP]BOX_OPEN_FULL", ArduinoProtocol.Type.RESP, ArduinoProtocol.Object.BOX, ArduinoProtocol.State.OPENED_FULL, Dispenser.NONE),
	RESP_BOX_OPEN_HALF ("[RESP]BOX_OPEN_HALF", ArduinoProtocol.Type.RESP, ArduinoProtocol.Object.BOX, ArduinoProtocol.State.OPENED_HALF, Dispenser.NONE),
	RESP_BOX_BLOCKED ("[RESP]BOX_BLOCKED", ArduinoProtocol.Type.RESP, ArduinoProtocol.Object.BOX, ArduinoProtocol.State.ERROR_BLOCKED, Dispenser.NONE),
	RESP_BOX_WORKING_OPENING ("[RESP]BOX_WORKING_OPENING", ArduinoProtocol.Type.RESP, ArduinoProtocol.Object.BOX, ArduinoProtocol.State.WORKING, Dispenser.NONE),
	RESP_BOX_WORKING_CLOSING ("[RESP]BOX_WORKING_CLOSING", ArduinoProtocol.Type.RESP, ArduinoProtocol.Object.BOX, ArduinoProtocol.State.WORKING, Dispenser.NONE),

	RESP_MEASURES ("[RESP]MEASURES", ArduinoProtocol.Type.RESP, ArduinoProtocol.Object.METER, ArduinoProtocol.State.IDLE, Dispenser.NONE),

	RESP_UNRECOGNIZED ("[RESP]UNRECOGNIZED", ArduinoProtocol.Type.RESP, ArduinoProtocol.Object.NONE, ArduinoProtocol.State.ERROR, Dispenser.NONE),

	CALLBACK_FAIL ("[CB]FAIL", ArduinoProtocol.Type.CALLBACK, ArduinoProtocol.Object.BOX, ArduinoProtocol.State.ERROR, Dispenser.NONE),
	CALLBACK_SCALE_OFFLINE ("[CB]SCALE_OFFLINE", ArduinoProtocol.Type.CALLBACK, ArduinoProtocol.Object.SCALE, ArduinoProtocol.State.ERROR, Dispenser.NONE),

	CALLBACK_IDLE_D1 ("[CB]IDLE_D1", ArduinoProtocol.Type.CALLBACK, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.IDLE, Dispenser.A),
	CALLBACK_WORKING_D1 ("[CB]WORKING_D1", ArduinoProtocol.Type.CALLBACK, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.WORKING, Dispenser.A),
	CALLBACK_READY_TO_REMOVE_D1 ("[CB]READY_TO_REMOVE_D1", ArduinoProtocol.Type.CALLBACK, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.WORKING, Dispenser.A),
	CALLBACK_WARNING_EMPTY_D1 ("[CB]CALLBACK_WARNING_EMPTY_D1", ArduinoProtocol.Type.CALLBACK, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.WARNING, Dispenser.A),
	CALLBACK_WARNING_LOW_LEVEL_D1 ("[CB]CALLBACK_WARNING_LOW_LEVEL_D1", ArduinoProtocol.Type.CALLBACK, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.WARNING, Dispenser.A),
	CALLBACK_ERROR_BLOCKED_D1 ("[CB]CALLBACK_ERROR_BLOCKED_D1", ArduinoProtocol.Type.CALLBACK, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.ERROR_BLOCKED, Dispenser.A),
	CALLBACK_IDLE_D2 ("[CB]IDLE_D2", ArduinoProtocol.Type.CALLBACK, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.IDLE, Dispenser.B),
	CALLBACK_WORKING_D2 ("[CB]WORKING_D2", ArduinoProtocol.Type.CALLBACK, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.WORKING, Dispenser.B),
	CALLBACK_READY_TO_REMOVE_D2 ("[CB]READY_TO_REMOVE_D2", ArduinoProtocol.Type.CALLBACK, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.WORKING, Dispenser.B),
	CALLBACK_WARNING_EMPTY_D2 ("[CB]CALLBACK_WARNING_EMPTY_D2", ArduinoProtocol.Type.CALLBACK, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.WARNING, Dispenser.B),
	CALLBACK_WARNING_LOW_LEVEL_D2 ("[CB]CALLBACK_WARNING_LOW_LEVEL_D2", ArduinoProtocol.Type.CALLBACK, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.WARNING, Dispenser.B),
	CALLBACK_ERROR_BLOCKED_D2 ("[CB]CALLBACK_ERROR_BLOCKED_D2", ArduinoProtocol.Type.CALLBACK, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.ERROR_BLOCKED, Dispenser.B),
	CALLBACK_IDLE_D3 ("[CB]IDLE_D3", ArduinoProtocol.Type.CALLBACK, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.IDLE, Dispenser.C),
	CALLBACK_WORKING_D3 ("[CB]WORKING_D3", ArduinoProtocol.Type.CALLBACK, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.WORKING, Dispenser.C),
	CALLBACK_READY_TO_REMOVE_D3 ("[CB]READY_TO_REMOVE_D3", ArduinoProtocol.Type.CALLBACK, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.WORKING, Dispenser.C),
	CALLBACK_WARNING_EMPTY_D3 ("[CB]CALLBACK_WARNING_EMPTY_D3", ArduinoProtocol.Type.CALLBACK, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.WARNING, Dispenser.C),
	CALLBACK_WARNING_LOW_LEVEL_D3 ("[CB]CALLBACK_WARNING_LOW_LEVEL_D3", ArduinoProtocol.Type.CALLBACK, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.WARNING, Dispenser.C),
	CALLBACK_ERROR_BLOCKED_D3 ("[CB]CALLBACK_ERROR_BLOCKED_D3", ArduinoProtocol.Type.CALLBACK, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.ERROR_BLOCKED, Dispenser.C),
	CALLBACK_IDLE_D4 ("[CB]IDLE_D4", ArduinoProtocol.Type.CALLBACK, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.IDLE, Dispenser.D),
	CALLBACK_WORKING_D4 ("[CB]WORKING_D4", ArduinoProtocol.Type.CALLBACK, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.WORKING, Dispenser.D),
	CALLBACK_READY_TO_REMOVE_D4 ("[CB]READY_TO_REMOVE_D4", ArduinoProtocol.Type.CALLBACK, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.WORKING, Dispenser.D),
	CALLBACK_WARNING_EMPTY_D4 ("[CB]CALLBACK_WARNING_EMPTY_D4", ArduinoProtocol.Type.CALLBACK, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.WARNING, Dispenser.D),
	CALLBACK_WARNING_LOW_LEVEL_D4 ("[CB]CALLBACK_WARNING_LOW_LEVEL_D4", ArduinoProtocol.Type.CALLBACK, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.WARNING, Dispenser.D),
	CALLBACK_ERROR_BLOCKED_D4 ("[CB]CALLBACK_ERROR_BLOCKED_D4", ArduinoProtocol.Type.CALLBACK, ArduinoProtocol.Object.DISPENSER, ArduinoProtocol.State.ERROR_BLOCKED, Dispenser.D),

	CALLBACK_BOX_WORKING_OPENING ("[CB]BOX_WORKING_OPENING", ArduinoProtocol.Type.CALLBACK, ArduinoProtocol.Object.BOX, ArduinoProtocol.State.WORKING, Dispenser.NONE),
	CALLBACK_BOX_WORKING_CLOSING ("[CB]BOX_WORKING_CLOSING", ArduinoProtocol.Type.CALLBACK, ArduinoProtocol.Object.BOX, ArduinoProtocol.State.WORKING, Dispenser.NONE),


	CALLBACK_UNRECOGNIZED_COMM ("[CB]UNRECOGNIZED_COMM", ArduinoProtocol.Type.CALLBACK, ArduinoProtocol.Object.NONE, ArduinoProtocol.State.ERROR, Dispenser.NONE),
	DEBUG ("[DEBUG]", ArduinoProtocol.Type.DEBUG, ArduinoProtocol.Object.NONE, ArduinoProtocol.State.NONE, Dispenser.NONE);

	private String message;
	private ArduinoProtocol.Type type;
	private ArduinoProtocol.Object object;
	private State state;
	private Dispenser dispenser;

	private ArduinoProtocol(final String pMessage, final ArduinoProtocol.Type pType, final ArduinoProtocol.Object pObject, final ArduinoProtocol.State pState, final Dispenser pDispenser) {
		this.message = pMessage;
		this.type = pType;
		this.object = pObject;
		this.state = pState;
		this.dispenser = pDispenser;
	}

	public String getMessage() {
		return this.message;
	}

	public ArduinoProtocol.Type getType(){
		return this.type;
	}

	public enum Type{
		RESP, CALLBACK, DEBUG
	}

	public enum Object{
		BOX, DISPENSER, SCALE, NONE, METER
	}

	public enum State{
		IDLE, WORKING, CLOSED, OPENED_FULL, OPENED_HALF, WARNING, ERROR_BLOCKED, ERROR, NONE
	}

	public static ArduinoProtocol parseString(final String message) {

		for(final ArduinoProtocol ret : ArduinoProtocol.values())
			if(ret.getMessage().equals(message.trim()))
				return ret;
			else if(message.startsWith("[RESP]INVENTORY_D")) {
				if(message.startsWith(ArduinoProtocol.RESP_INVENTORY_D1.message))
					return ArduinoProtocol.RESP_INVENTORY_D1;
				if(message.startsWith(ArduinoProtocol.RESP_INVENTORY_D2.message))
					return ArduinoProtocol.RESP_INVENTORY_D2;
				if(message.startsWith(ArduinoProtocol.RESP_INVENTORY_D3.message))
					return ArduinoProtocol.RESP_INVENTORY_D3;
				if(message.startsWith(ArduinoProtocol.RESP_INVENTORY_D4.message))
					return ArduinoProtocol.RESP_INVENTORY_D4;
			}else if(message.startsWith("[RESP]MEASURES"))
				return ArduinoProtocol.RESP_MEASURES;

		return null;
	}

	public Object getObject() {
		return this.object;
	}

	public void setObject(final Object object) {
		this.object = object;
	}

	public State getState() {
		return this.state;
	}

	public void setState(final State state) {
		this.state = state;
	}

	public Dispenser getDispenser() {
		return this.dispenser;
	}

	public void setDispenser(final Dispenser dispenser) {
		this.dispenser = dispenser;
	}

	public DispenserState getDispenserState() {
		if(this.dispenser == null || this.dispenser == Dispenser.NONE)
			return null;

		if(("[RESP]IDLE_D"+this.dispenser.getId()).equals(this.message))
			return DispenserState.IDLE;
		if(("[RESP]WORKING_D"+this.dispenser.getId()).equals(this.message))
			return DispenserState.WORKING;
		if(("[RESP]READY_TO_REMOVE_D"+this.dispenser.getId()).equals(this.message))
			return DispenserState.READY_TO_REMOVE;
		if(("[RESP]WARNING_LOW_LEVEL_D"+this.dispenser.getId()).equals(this.message))
			return DispenserState.LOW_LEVEL;
		if(("[RESP]WARNING_EMPTY_D"+this.dispenser.getId()).equals(this.message))
			return DispenserState.EMPTY;
		if(("[RESP]ERROR_BLOCKED_D"+this.dispenser.getId()).equals(this.message))
			return DispenserState.ERROR_BLOCKED;
		return DispenserState.COMMUNICATION_ERROR;
	}
}
