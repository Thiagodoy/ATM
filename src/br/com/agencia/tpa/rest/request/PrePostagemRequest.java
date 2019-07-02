package br.com.agencia.tpa.rest.request;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.agencia.tpa.rest.response.PrePostagemResponse;

public class PrePostagemRequest {	
	
	@JsonProperty("forma_pagamento")
	public String formaPagamento;
	
	@JsonProperty(value = "objeto_postal")
	public List<ObjetoPostalRequest> objetoPostalRequest;	
	
	@JsonProperty(value = "plp")
	public PlpRequest plpRequest;
	
	@JsonProperty(value = "remetente")
	public RemetenteRequest remetenteRequest;
	
	
	public PrePostagemRequest() {
		
		ObjetoPostalRequest obj = new ObjetoPostalRequest();	
		DimensaoObjetoRequest dimensaoObjetoRequest = new DimensaoObjetoRequest("001"); 
		obj.setDimensaoObjetoRequest(dimensaoObjetoRequest);
		this.objetoPostalRequest = new ArrayList<ObjetoPostalRequest>();
		this.objetoPostalRequest.add(obj);
		this.formaPagamento = "0";
		
	}
	
	public PrePostagemRequest(PrePostagemResponse response) {
		ObjetoPostalRequest obj = new ObjetoPostalRequest();
		this.remetenteRequest = response.getRemetente();
		obj.setDestinatario(new DestinatarioRequest(response.getObjetoPostal().get(0).getDestinatario()));		
		obj.setDimensaoObjetoRequest(response.getObjetoPostal().get(0).getDimensao());		
		obj.setNacionalRequest(new NacionalRequest(response.getObjetoPostal().get(0).getNacional()));
		obj.setServico(new ServicoAdicionalRequest(response.getObjetoPostal().get(0).getServiceAdicional()));
		this.plpRequest = response.getPlp();
		this.formaPagamento = response.getFormaPagamento();
		this.objetoPostalRequest = Arrays.asList(obj);
		
	}
	
	

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

	public RemetenteRequest getRemetente() {
		return remetenteRequest;
	}

	public void setRemetenteRequest(RemetenteRequest remetenteRequest) {
		this.remetenteRequest = remetenteRequest;
	}
	
	
	
	
	
	

}
