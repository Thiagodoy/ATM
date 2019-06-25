package br.com.agencia.tpa.rest.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;




public class ListaPrecoPrazoResponse {
	
	@JsonProperty("listaPrecoPrazo")
	public List<PrecoPrazoResponse> listaPrecoPrazo;
	
	

}
