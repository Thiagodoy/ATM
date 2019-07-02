package br.com.agencia.tpa.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DestinatarioResponse {

	@JsonProperty(value = "numeroEndDestinatarioFormatado")
	private String numeroEndDestinatarioFormatado;
	@JsonProperty(value = "complementoLogradouroFormatado")
	private String complementoLogradouroFormatado;
	@JsonProperty(value = "telefoneDestinatarioFormatado")
	private String telefoneDestinatarioFormatado;
	@JsonProperty(value = "nome_destinatario")
	private String nomeDestinatario;
	@JsonProperty(value = "telefone_destinatario")
	private String telefoneDestinatario;
	@JsonProperty(value = "celular_destinatario")
	private String celularDestinatario;
	@JsonProperty(value = "email_destinatario")
	private String emailDestinatario;
	@JsonProperty(value = "logradouro_destinatario")
	private String logradouroDestinatario;
	@JsonProperty(value = "complemento_destinatario")
	private String complementoDestinatario;
	@JsonProperty(value = "numero_end_destinatario")
	private String numeroEndDestinatario;
	@JsonProperty(value = "cpf_cnpj_destinatario")
	private String cpfCnpj;
	
	public String getNumeroEndDestinatarioFormatado() {
		return numeroEndDestinatarioFormatado;
	}
	public void setNumeroEndDestinatarioFormatado(String numeroEndDestinatarioFormatado) {
		this.numeroEndDestinatarioFormatado = numeroEndDestinatarioFormatado;
	}
	public String getComplementoLogradouroFormatado() {
		return complementoLogradouroFormatado;
	}
	public void setComplementoLogradouroFormatado(String complementoLogradouroFormatado) {
		this.complementoLogradouroFormatado = complementoLogradouroFormatado;
	}
	public String getTelefoneDestinatarioFormatado() {
		return telefoneDestinatarioFormatado;
	}
	public void setTelefoneDestinatarioFormatado(String telefoneDestinatarioFormatado) {
		this.telefoneDestinatarioFormatado = telefoneDestinatarioFormatado;
	}
	public String getNomeDestinatario() {
		return nomeDestinatario;
	}
	public void setNomeDestinatario(String nomeDestinatario) {
		this.nomeDestinatario = nomeDestinatario;
	}
	public String getTelefoneDestinatario() {
		return telefoneDestinatario;
	}
	public void setTelefoneDestinatario(String telefoneDestinatario) {
		this.telefoneDestinatario = telefoneDestinatario;
	}
	public String getCelularDestinatario() {
		return celularDestinatario;
	}
	public void setCelularDestinatario(String celularDestinatario) {
		this.celularDestinatario = celularDestinatario;
	}
	public String getEmailDestinatario() {
		return emailDestinatario;
	}
	public void setEmailDestinatario(String emailDestinatario) {
		this.emailDestinatario = emailDestinatario;
	}
	public String getLogradouroDestinatario() {
		return logradouroDestinatario;
	}
	public void setLogradouroDestinatario(String logradouroDestinatario) {
		this.logradouroDestinatario = logradouroDestinatario;
	}
	public String getComplementoDestinatario() {
		return complementoDestinatario;
	}
	public void setComplementoDestinatario(String complementoDestinatario) {
		this.complementoDestinatario = complementoDestinatario;
	}
	public String getNumeroEndDestinatario() {
		return numeroEndDestinatario;
	}
	public void setNumeroEndDestinatario(String numeroEndDestinatario) {
		this.numeroEndDestinatario = numeroEndDestinatario;
	}
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	
	
	

}
