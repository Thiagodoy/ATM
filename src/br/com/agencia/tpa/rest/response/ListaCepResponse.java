package br.com.agencia.tpa.rest.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ListaCepResponse {
	
	
	@JsonProperty(value = "STATUS")
	public String status;
	
	@JsonProperty(value = "ceps")
	public List<CepResponse> ceps;

}
