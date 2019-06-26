package br.com.agencia.tpa.rest.request;

import javafx.scene.control.Button;

public class ItemDeclaracaoConteudoRequest {

	
	public ItemDeclaracaoConteudoRequest(final String pId, final String pDescription, final String pQuantity, final String pValue) {
		this.id = pId;
		this.description = pDescription;
		this.quantity = pQuantity;
		this.value = pValue;		
	}

	private String id;
	private String description;
	private String quantity;
	private String value;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
	


	
	
}
