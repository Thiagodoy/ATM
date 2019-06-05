package br.com.agencialove.tpa.model;

public class PaymentData {
	private String id;
	private String code;
	private String value;
	private String currency;

	public String getId() {
		return this.id;
	}
	public void setId(final String id) {
		this.id = id;
	}
	public String getCode() {
		return this.code;
	}
	public void setCode(final String code) {
		this.code = code;
	}
	public String getValue() {
		return this.value;
	}
	public void setValue(final String value) {
		this.value = value;
	}
	public String getCurrency() {
		return this.currency;
	}
	public void setCurrency(final String currency) {
		this.currency = currency;
	}
}
