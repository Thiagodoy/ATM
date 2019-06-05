package br.com.agencialove.tpa.model;

public enum ObjectType {
	ENVELOPE("1"), BOX("2"), CYLINDER("3"), SMALL("4"), MID ("5"), BIG ("6");

	private String code;

	private ObjectType(final String pCode) {
		this.code = pCode;
	}

	public String getCode() {
		return this.code;
	}
}
