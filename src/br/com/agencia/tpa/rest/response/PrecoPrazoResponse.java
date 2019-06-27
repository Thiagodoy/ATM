package br.com.agencia.tpa.rest.response;


import com.fasterxml.jackson.annotation.JsonProperty;


public class PrecoPrazoResponse {
	
	@JsonProperty(value = "codigoServico")
	public String codigoServico;
	
	@JsonProperty(value = "descricaoServico")
	public String descricaoServico;
	
	@JsonProperty(value = "valor")
	public String valor;
	
	@JsonProperty(value = "prazoEntrega")
	public String prazoEntrega;
	
	@JsonProperty(value = "valorMaoPropria")
	public String valorMaoPropria;
	
	@JsonProperty(value = "valorAvisoRecebimento")
	public String valorAvisoRecebimento;
	
	@JsonProperty(value = "valorValorDeclarado")
	public String valorValorDeclarado;
	
	@JsonProperty(value = "entregaDomiciliar")
	public String entregaDomiciliar;
	
	@JsonProperty(value = "entregaSabado")
	public String entregaSabado;
	
	@JsonProperty(value = "codigoErro")
	public String codigoErro;
	
	@JsonProperty(value = "msgErro")
	public String msgErro;
	
	@JsonProperty(value = "valorSemAdicionais")
	public String valorSemAdicionais;
	
	@JsonProperty(value = "observacao")
	public String observacao;
	
	

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getCodigoServico() {
		return codigoServico;
	}

	public void setCodigoServico(String codigoServico) {
		this.codigoServico = codigoServico;
	}

	public String getDescricaoServico() {
		return descricaoServico;
	}

	public void setDescricaoServico(String descricaoServico) {
		this.descricaoServico = descricaoServico;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getPrazoEntrega() {
		return prazoEntrega;
	}

	public void setPrazoEntrega(String prazoEntrega) {
		this.prazoEntrega = prazoEntrega;
	}

	public String getValorMaoPropria() {
		return valorMaoPropria;
	}

	public void setValorMaoPropria(String valorMaoPropria) {
		this.valorMaoPropria = valorMaoPropria;
	}

	public String getValorAvisoRecebimento() {
		return valorAvisoRecebimento;
	}

	public void setValorAvisoRecebimento(String valorAvisoRecebimento) {
		this.valorAvisoRecebimento = valorAvisoRecebimento;
	}

	public String getValorValorDeclarado() {
		return valorValorDeclarado;
	}

	public void setValorValorDeclarado(String valorValorDeclarado) {
		this.valorValorDeclarado = valorValorDeclarado;
	}

	public String getEntregaDomiciliar() {
		return entregaDomiciliar;
	}

	public void setEntregaDomiciliar(String entregaDomiciliar) {
		this.entregaDomiciliar = entregaDomiciliar;
	}

	public String getEntregaSabado() {
		return entregaSabado;
	}

	public void setEntregaSabado(String entregaSabado) {
		this.entregaSabado = entregaSabado;
	}

	public String getCodigoErro() {
		return codigoErro;
	}

	public void setCodigoErro(String codigoErro) {
		this.codigoErro = codigoErro;
	}

	public String getMsgErro() {
		return msgErro;
	}

	public void setMsgErro(String msgErro) {
		this.msgErro = msgErro;
	}

	public String getValorSemAdicionais() {
		return valorSemAdicionais;
	}

	public void setValorSemAdicionais(String valorSemAdicionais) {
		this.valorSemAdicionais = valorSemAdicionais;
	}
	
	
	
	
}
