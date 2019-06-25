package br.com.agencia.tpa.rest.response;


import com.fasterxml.jackson.annotation.JsonProperty;


public class CepResponse {
	
	public CepResponse() {
		
		this.estado = "";
		this.cidade = "";
		this.rua = "";
		this.complemento = "";
		this.bairro = "";
		this.tipo = "";
		this.cep = "";
	}

	@JsonProperty(value = "UF")
	public String estado;
	
	@JsonProperty(value = "LOCAL")
	public String cidade;
	
	@JsonProperty(value = "ENDERECO")
	public String rua;
	
	@JsonProperty(value = "ADICIONAL")
	public String complemento;
	
	@JsonProperty(value = "BAIRRO")
	public String bairro;
	
	@JsonProperty(value = "TIPO")
	public String tipo;	
	
	@JsonProperty(value = "CEP")
	public String cep;

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}	

}
