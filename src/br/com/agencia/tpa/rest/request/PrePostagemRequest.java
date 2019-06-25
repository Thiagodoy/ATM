package br.com.agencia.tpa.rest.request;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PrePostagemRequest {	
	
	
	
	
	public PrePostagemRequest() {
		
		ObjetoPostalRequest obj = new ObjetoPostalRequest();		
		objetoPostalRequest = new ArrayList<ObjetoPostalRequest>();
		objetoPostalRequest.add(obj);
		
	}
	
	
	@JsonProperty("forma_pagamento")
	public String formaPagamento;
	
	@JsonProperty(value = "objeto_postal")
	public List<ObjetoPostalRequest> objetoPostalRequest;	
	
	@JsonProperty(value = "plp")
	public PlpRequest plpRequest;
	
	@JsonProperty(value = "remetente")
	public RemetenteRequest remetenteRequest;

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public List<ObjetoPostalRequest> getObjetoPostalRequest() {
		return objetoPostalRequest;
	}

	public void setObjetoPostalRequest(List<ObjetoPostalRequest> objetoPostalRequest) {
		this.objetoPostalRequest = objetoPostalRequest;
	}

	public PlpRequest getPlpRequest() {
		return plpRequest;
	}

	public void setPlpRequest(PlpRequest plpRequest) {
		this.plpRequest = plpRequest;
	}

	public RemetenteRequest getRemetenteRequest() {
		return remetenteRequest;
	}

	public void setRemetenteRequest(RemetenteRequest remetenteRequest) {
		this.remetenteRequest = remetenteRequest;
	}
	
	
	
	
	
	

}
