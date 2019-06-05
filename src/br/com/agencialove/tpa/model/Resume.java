package br.com.agencialove.tpa.model;

public class Resume {
	private String description;
	private String quantity;
	private String value;

	public Resume() {
	}

	public Resume(final String pDescription, final String pQuantity, final String pValue) {
		this.description = pDescription;
		this.quantity = pQuantity;
		this.value = pValue;
	}
	public String getDescription() {
		return this.description;
	}
	public void setDescription(final String description) {
		this.description = description;
	}
	public String getQuantity() {
		return this.quantity;
	}
	public void setQuantity(final String quantity) {
		this.quantity = quantity;
	}
	public String getValue() {
		return this.value;
	}
	public void setValue(final String value) {
		this.value = value;
	}
}
