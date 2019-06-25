package br.com.agencia.tpa.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PrePostagemResponse {
	
	
	@JsonProperty(value = "numeroPLP")
	public String numeroPlp;
	

	@JsonProperty(value = "numeroEtiqueta")
	public String numeroEtiqueta;

}
