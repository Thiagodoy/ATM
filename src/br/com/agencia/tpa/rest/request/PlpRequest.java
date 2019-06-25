package br.com.agencia.tpa.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlpRequest {
	
	
	
	
	@JsonProperty(value = "id_plp")
	public Long idPlp;
	
	@JsonProperty(value = "valor_global")
	public Long valorGlobal;
	
	@JsonProperty(value = "mcu_unidade_postagem")
	public String mcuUnidadePostagem;
	
	@JsonProperty(value = "nome_unidade_postagem")
	public String nomeUnidadePostagem;
	
	@JsonProperty(value = "cartao_postagem", required = true)
	public String cartaPostagem;
	
	

	public Long getIdPlp() {
		return idPlp;
	}

	public void setIdPlp(Long idPlp) {
		this.idPlp = idPlp;
	}

	public Long getValorGlobal() {
		return valorGlobal;
	}

	public void setValorGlobal(Long valorGlobal) {
		this.valorGlobal = valorGlobal;
	}

	public String getMcuUnidadePostagem() {
		return mcuUnidadePostagem;
	}

	public void setMcuUnidadePostagem(String mcuUnidadePostagem) {
		this.mcuUnidadePostagem = mcuUnidadePostagem;
	}

	public String getNomeUnidadePostagem() {
		return nomeUnidadePostagem;
	}

	public void setNomeUnidadePostagem(String nomeUnidadePostagem) {
		this.nomeUnidadePostagem = nomeUnidadePostagem;
	}

	public String getCartaPostagem() {
		return cartaPostagem;
	}

	public void setCartaPostagem(String cartaPostagem) {
		this.cartaPostagem = cartaPostagem;
	}
	
	
	
	
	

}
