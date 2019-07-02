package br.com.agencia.tpa.rest.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.agencia.tpa.rest.request.PlpRequest;
import br.com.agencia.tpa.rest.request.RemetenteRequest;

public class PrePostagemResponse {
	
	@JsonProperty(value = "plp")
	private PlpRequest plp;
	
	@JsonProperty(value = "remetente")
	private RemetenteRequest remetente;

	@JsonProperty(value = "forma_pagamento")
	private String formaPagamento;
	
	
	@JsonProperty(value = "objeto_postal")
	private List<ObjetoPostalResponse> objetoPostal;


	public PlpRequest getPlp() {
		return plp;
	}


	public void setPlp(PlpRequest plp) {
		this.plp = plp;
	}


	public RemetenteRequest getRemetente() {
		return remetente;
	}


	public void setRemetente(RemetenteRequest remetente) {
		this.remetente = remetente;
	}


	public String getFormaPagamento() {
		return formaPagamento;
	}


	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}


	public List<ObjetoPostalResponse> getObjetoPostal() {
		return objetoPostal;
	}


	public void setObjetoPostal(List<ObjetoPostalResponse> objetoPostal) {
		this.objetoPostal = objetoPostal;
	}
	
	
	
	
}
