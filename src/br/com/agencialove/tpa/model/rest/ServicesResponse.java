package br.com.agencialove.tpa.model.rest;

public class ServicesResponse {

	private String codigoServico;
	private String descricaoServico;
	private String valor;
	private String prazoEntrega;
	private String valorMaoPropria;
	private String valorAvisoRecebimento;
	private String valorValorDeclarado;
	private String entregaDomiciliar;
	private String entregaSabado;
	private String codigoErro;
	private String msgErro;
	private String observacao;
	private String valorSemAdicionais;
	private String valorTotal;

	public ServicesResponse(final String codigoServico, final String descricaoServico, final String valor, final String prazoEntrega, final String valorMaoPropria, final String valorAvisoRecebimento, final String valorValorDeclarado, final String entregaDomiciliar, final String entregaSabado, final String codigoErro, final String msgErro, final String observacao, final String valorSemAdicionais) {
		this.codigoServico = codigoServico;
		this.descricaoServico = descricaoServico;
		this.valor = valor;
		this.prazoEntrega = prazoEntrega;
		this.valorMaoPropria = valorMaoPropria;
		this.valorAvisoRecebimento = valorAvisoRecebimento;
		this.valorValorDeclarado = valorValorDeclarado;
		this.entregaDomiciliar = entregaDomiciliar;
		this.entregaSabado = entregaSabado;
		this.codigoErro = codigoErro;
		this.msgErro = msgErro;
		this.observacao = observacao;
		this.valorSemAdicionais = valorSemAdicionais;
	}

	public String getCodigoServico() {
		return this.codigoServico;
	}

	public void setCodigoServico(final String codigoServico) {
		this.codigoServico = codigoServico;
	}

	public String getDescricaoServico() {
		return this.descricaoServico;
	}

	public void setDescricaoServico(final String descricaoServico) {
		this.descricaoServico = descricaoServico;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(final String valor) {
		this.valor = valor;
	}

	public String getPrazoEntrega() {
		return this.prazoEntrega;
	}

	public void setPrazoEntrega(final String prazoEntrega) {
		this.prazoEntrega = prazoEntrega;
	}

	public String getValorMaoPropria() {
		return this.valorMaoPropria;
	}

	public void setValorMaoPropria(final String valorMaoPropria) {
		this.valorMaoPropria = valorMaoPropria;
	}

	public String getValorAvisoRecebimento() {
		return this.valorAvisoRecebimento;
	}

	public void setValorAvisoRecebimento(final String valorAvisoRecebimento) {
		this.valorAvisoRecebimento = valorAvisoRecebimento;
	}

	public String getValorValorDeclarado() {
		return this.valorValorDeclarado;
	}

	public void setValorValorDeclarado(final String valorValorDeclarado) {
		this.valorValorDeclarado = valorValorDeclarado;
	}

	public String getEntregaDomiciliar() {
		return this.entregaDomiciliar;
	}

	public void setEntregaDomiciliar(final String entregaDomiciliar) {
		this.entregaDomiciliar = entregaDomiciliar;
	}

	public String getEntregaSabado() {
		return this.entregaSabado;
	}

	public void setEntregaSabado(final String entregaSabado) {
		this.entregaSabado = entregaSabado;
	}

	public String getCodigoErro() {
		return this.codigoErro;
	}

	public void setCodigoErro(final String codigoErro) {
		this.codigoErro = codigoErro;
	}

	public String getMsgErro() {
		return this.msgErro;
	}

	public void setMsgErro(final String msgErro) {
		this.msgErro = msgErro;
	}

	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(final String observacao) {
		this.observacao = observacao;
	}

	public String getValorSemAdicionais() {
		return this.valorSemAdicionais;
	}

	public void setValorSemAdicionais(final String valorSemAdicionais) {
		this.valorSemAdicionais = valorSemAdicionais;
	}

	@Override
	public String toString() {
		return "PrecoPrazoResponse{" +
				"codigoServico='" + this.codigoServico + '\'' +
				", descricaoServico='" + this.descricaoServico + '\'' +
				", valor='" + this.valor + '\'' +
				", prazoEntrega='" + this.prazoEntrega + '\'' +
				", valorMaoPropria='" + this.valorMaoPropria + '\'' +
				", valorAvisoRecebimento='" + this.valorAvisoRecebimento + '\'' +
				", valorValorDeclarado='" + this.valorValorDeclarado + '\'' +
				", entregaDomiciliar='" + this.entregaDomiciliar + '\'' +
				", entregaSabado='" + this.entregaSabado + '\'' +
				", codigoErro='" + this.codigoErro + '\'' +
				", msgErro='" + this.msgErro + '\'' +
				", observacao='" + this.observacao + '\'' +
				", valorSemAdicionais='" + this.valorSemAdicionais + '\'' +
				'}';
	}

	public String getValorTotal() {
		return this.valorTotal;
	}

	public void setValorTotal(final String valorTotal) {
		this.valorTotal = valorTotal;
	}
}
