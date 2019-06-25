package br.com.agencia.tpa.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DimensaoObjetoRequest {	
	
	@JsonProperty(value = "tipo_objeto", required = true)
	public String tipoObjeto;
	
	@JsonProperty(value = "dimensao_altura")
	public long dimensaoAltura;
	
	@JsonProperty(value = "dimensao_largura")
	public long dimensaoLargura;	
	
	@JsonProperty(value = "dimensao_comprimento")
	public long dimensaoComprimento;
	
	@JsonProperty(value = "dimensao_diametro")
	public long dimensaoDiametro;
	
	public DimensaoObjetoRequest() {		
		
	}
	

	public DimensaoObjetoRequest(String tipoObjeto) {		
		this.tipoObjeto = tipoObjeto;
	}

	public String getTipoObjeto() {
		return tipoObjeto;
	}

	public void setTipoObjeto(String tipoObjeto) {
		this.tipoObjeto = tipoObjeto;
	}

	public long getDimensaoAltura() {
		return dimensaoAltura;
	}

	public void setDimensaoAltura(long dimensaoAltura) {
		this.dimensaoAltura = dimensaoAltura;
	}

	public long getDimensaoLargura() {
		return dimensaoLargura;
	}

	public void setDimensaoLargura(long dimensaoLargura) {
		this.dimensaoLargura = dimensaoLargura;
	}

	public long getDimensaoComprimento() {
		return dimensaoComprimento;
	}

	public void setDimensaoComprimento(long dimensaoComprimento) {
		this.dimensaoComprimento = dimensaoComprimento;
	}

	public long getDimensaoDiametro() {
		return dimensaoDiametro;
	}

	public void setDimensaoDiametro(long dimensaoDiametro) {
		this.dimensaoDiametro = dimensaoDiametro;
	}

	
	
	
}
