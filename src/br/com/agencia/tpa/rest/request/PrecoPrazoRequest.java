package br.com.agencia.tpa.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PrecoPrazoRequest {
	
	@JsonProperty(value = "cepOrigem")
	public String cepOrigem;
	
	@JsonProperty(value = "cepDestino")
	public String cepDestino;
	
	@JsonProperty(value = "peso")
	public String peso;
	
	@JsonProperty(value = "formato")
	public String formato;
	
	@JsonProperty(value = "altura")
	public String altura;
	
	@JsonProperty(value = "largura")
	public String largura;
	
	@JsonProperty(value = "diametro")
	public String diametro;
	
	@JsonProperty(value = "maoPropria")
	public String maoPropria;
	
	@JsonProperty(value = "valorDeclarado")
	public String valorDeclarado;
	
	@JsonProperty(value = "avisoRecebimento")
	public String avisoRecebimento;
	
	@JsonProperty(value = "comprimento")
	public String comprimento;
	
	public String getCepOrigem() {
		return cepOrigem;
	}
	public void setCepOrigem(String cepOrigem) {
		this.cepOrigem = cepOrigem;
	}
	public String getCepDestino() {
		return cepDestino;
	}
	public void setCepDestino(String cepDestino) {
		this.cepDestino = cepDestino;
	}
	public String getPeso() {
		return peso;
	}
	public void setPeso(String peso) {
		this.peso = peso;
	}
	public String getFormato() {
		return formato;
	}
	public void setFormato(String formato) {
		this.formato = formato;
	}
	public String getAltura() {
		return altura;
	}
	public void setAltura(String altura) {
		this.altura = altura;
	}
	public String getLargura() {
		return largura;
	}
	public void setLargura(String largura) {
		this.largura = largura;
	}
	public String getDiametro() {
		return diametro;
	}
	public void setDiametro(String diametro) {
		this.diametro = diametro;
	}
	public String getMaoPropria() {
		return maoPropria;
	}
	public void setMaoPropria(String maoPropria) {
		this.maoPropria = maoPropria;
	}
	public String getValorDeclarado() {
		return valorDeclarado;
	}
	public void setValorDeclarado(String valorDeclarado) {
		this.valorDeclarado = valorDeclarado;
	}
	public String getAvisoRecebimento() {
		return avisoRecebimento;
	}
	public void setAvisoRecebimento(String avisoRecebimento) {
		this.avisoRecebimento = avisoRecebimento;
	}
	public String getComprimento() {
		return comprimento;
	}
	public void setComprimento(String comprimento) {
		this.comprimento = comprimento;
	}
	
	

}
