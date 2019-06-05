package br.com.agencialove.tpa.model;

public class AdditionalServices {
	private boolean onwHands;
	private boolean deliveryNotice;
	private boolean valueDeclaration;
	private String contentValue;
	private String content;

	public boolean isOnwHands() {
		return this.onwHands;
	}
	public void setOnwHands(final boolean onwHands) {
		this.onwHands = onwHands;
	}
	public boolean isDeliveryNotice() {
		return this.deliveryNotice;
	}
	public void setDeliveryNotice(final boolean deliveryNotice) {
		this.deliveryNotice = deliveryNotice;
	}
	public String getContentValue() {
		return this.contentValue;
	}
	public void setContentValue(final String contentValue) {
		this.contentValue = contentValue;
	}
	public String getContent() {
		return this.content;
	}
	public void setContent(final String content) {
		this.content = content;
	}
	public boolean isValueDeclaration() {
		return this.valueDeclaration;
	}
	public void setValueDeclaration(final boolean valueDeclaration) {
		this.valueDeclaration = valueDeclaration;
	}
}
