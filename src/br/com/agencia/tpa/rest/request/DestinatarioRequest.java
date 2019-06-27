package br.com.agencia.tpa.rest.request;

import java.text.MessageFormat;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.JsonAdapter;

public class DestinatarioRequest {
	
	
	@JsonProperty(value = "nome_destinatario", required = true)
	public String nome;
	
	@JsonProperty(value = "cpf_cnpj_destinatario", required = true)
	public String cpf;
	
	@JsonProperty(value = "telefone_destinatario", required = false)
	public long telefone;
	
	@JsonProperty(value = "celular_destinatario", required = false)
	public long celular;	
	
	@JsonProperty(value = "email_destinatario", required = false)
	public String email;
	
	@JsonProperty(value = "logradouro_destinatario", required = true)
	public String logradouro;
	
	@JsonProperty(value = "complemento_destinatario", required = false)
	public String complemento;
	
	@JsonProperty(value = "numero_end_destinatario", required = true)
	public String numero;
	
	@JsonIgnore	
	public String cep;	
	
	@JsonIgnore	
	public String cidade;
	
	
	
	

	public DestinatarioRequest() {		
		this.nome = "";
		this.cpf = "";
		this.telefone = 0l;
		this.celular = 0l;
		this.email = "";
		this.logradouro = "";
		this.complemento = "";
		this.numero = "";
		this.cep = "";
		this.cidade = "";
		}
	
	
	
	

	public String getCidade() {
		return cidade;
	}





	public void setCidade(String cidade) {
		this.cidade = cidade;
	}





	public String getCep() {
		return cep;
	}



	public void setCep(String cep) {
		this.cep = cep;
	}



	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getTelefone() {
		return telefone;
	}

	public void setTelefone(long telefone) {
		this.telefone = telefone;
	}

	public long getCelular() {
		return celular;
	}

	public void setCelular(long celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	
	public String toFormatedAddress() {
		String logradouro = Optional.ofNullable(this.logradouro).isPresent() ? this.logradouro : "";		
		String number = Optional.ofNullable(this.numero).isPresent() ? this.numero : "";
		String city = Optional.ofNullable(this.cidade).isPresent() ? this.cidade : "";		
		String complement = Optional.ofNullable(this.complemento).isPresent() ? this.complemento : "";
		String zipCode = Optional.ofNullable(this.cep).isPresent() ? this.cep : "";
		
		return MessageFormat.format("Endereço: {0}, n° {1} , {2} \nComplemento: {3}\nCep{4}",logradouro,number,  city,complement,zipCode );
	}
	

}
