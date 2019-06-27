package br.com.agencia.tpa.rest.request;

import java.text.MessageFormat;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RemetenteRequest {

	@JsonProperty(value = "numero_contrato", required = false)
	public String contrato;

	@JsonProperty(value = "numero_diretoria", required = false)
	public Long numeroDiretoria;

	@JsonProperty(value = "codigo_administrativo", required = false)
	public String codigoAdministrativo;

	@JsonProperty(value = "nome_remetente", required = false)
	public String nome;

	@JsonProperty(value = "logradouro_remetente", required = false)
	public String logradouro;

	@JsonProperty(value = "numero_remetente", required = false)
	public String numero;

	@JsonProperty(value = "complemento_remetente")
	public String complemento;

	@JsonProperty(value = "bairro_remetente", required = true)
	public String bairro;

	@JsonProperty(value = "cep_remetente", required = true)
	public String cep;

	@JsonProperty(value = "cidade_remetente", required = true)
	public String cidade;

	@JsonProperty(value = "uf_remetente", required = true)
	public String estado;

	@JsonProperty(value = "telefone_remetente", required = false)
	public Long telefone;

	@JsonProperty(value = "fax_remetente", required = false)
	public Long fax;

	@JsonProperty(value = "celular_remetente", required = false)
	public Long celular;
	
	@JsonProperty(value = "email_remetente", required = false)
	public String email;
	
	@JsonProperty(value = "cpf_cnpj_remetente", required = false)
	public String cpf;
	
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public RemetenteRequest() {
		this.contrato = "";
		this.numeroDiretoria = 0l;
		this.codigoAdministrativo = "";
		this.nome = "";
		this.logradouro = "";
		this.numero = "";
		this.complemento = "";
		this.bairro = "";
		this.cep = "";
		this.cidade = "";
		this.estado = "";
		this.telefone = 0l;
		this.fax = 0l;
		this.celular = 0l;
		this.email = "";
		this.cpf = "";
	}

	public String getContrato() {
		return contrato;
	}

	public void setContrato(String contrato) {
		this.contrato = contrato;
	}

	public Long getNumeroDiretoria() {
		return numeroDiretoria;
	}

	public void setNumeroDiretoria(Long numeroDiretoria) {
		this.numeroDiretoria = numeroDiretoria;
	}

	public String getCodigoAdministrativo() {
		return codigoAdministrativo;
	}

	public void setCodigoAdministrativo(String codigoAdministrativo) {
		this.codigoAdministrativo = codigoAdministrativo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
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

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Long getTelefone() {
		return telefone;
	}

	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}

	public Long getFax() {
		return fax;
	}

	public void setFax(Long fax) {
		this.fax = fax;
	}

	public Long getCelular() {
		return celular;
	}

	public void setCelular(Long celular) {
		this.celular = celular;
	}
	
	public String toFormatedAddress() {
		String logradouro = Optional.ofNullable(this.logradouro).isPresent() ? this.logradouro : "";
		String neighborhood = Optional.ofNullable(this.bairro).isPresent() ? this.bairro : "";
		String number = Optional.ofNullable(this.numero).isPresent() ? this.numero : "";
		String city = Optional.ofNullable(this.cidade).isPresent() ? this.cidade : "";
		String state = Optional.ofNullable(this.estado).isPresent() ? this.estado : "";
		String complement = Optional.ofNullable(this.complemento).isPresent() ? this.complemento : "";
		String zipCode = Optional.ofNullable(this.cep).isPresent() ? this.cep : "";
		
		return MessageFormat.format("Endereço: {0}, n° {1} - {2}, {3} - {4} \nComplemento: {5}\nCep{6}",logradouro,number, neighborhood, city,state,complement,zipCode );
	}

}
