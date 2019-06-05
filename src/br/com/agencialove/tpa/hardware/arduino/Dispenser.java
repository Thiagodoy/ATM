package br.com.agencialove.tpa.hardware.arduino;

public enum Dispenser {
	A(1), B(2), C(3), D(4), NONE(0);

	private int id;

	private Dispenser(final int pId) {
		this.id = pId;
	}

	public int getId() {
		return this.id;
	}
}
