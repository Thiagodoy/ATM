package br.com.agencia.tpa.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EtiquetaResponse {
	
	
	@JsonProperty(value = "numeroPLP")
	public String numeroPlp;
	

	@JsonProperty(value = "numeroEtiqueta")
	public String numeroEtiqueta;


	public String getNumeroPlp() {
		return numeroPlp;
	}


	public void setNumeroPlp(String numeroPlp) {
		this.numeroPlp = numeroPlp;
	}


	public String getNumeroEtiqueta() {
		return numeroEtiqueta;
	}


	public void setNumeroEtiqueta(String numeroEtiqueta) {
		this.numeroEtiqueta = numeroEtiqueta;
	}
	
	
	

}
