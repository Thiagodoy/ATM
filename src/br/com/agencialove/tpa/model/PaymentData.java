package br.com.agencialove.tpa.model;

public class PaymentData {
	private String id;
	private String code;
	private String value;
	private String currency;	
	private String cnpjCpf;
	private String nsu;	
	private String Bandeira;
	private String numeroCartao;
	private String dataTransacao;
	private String horaTransacao;
	private String formaPagamento;
	
	
	public String getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public String getCnpjCpf() {
		return cnpjCpf;
	}
	public void setCnpjCpf(String cnpjCpf) {
		this.cnpjCpf = cnpjCpf;
	}
	public String getNsu() {
		return nsu;
	}
	public void setNsu(String nsu) {
		this.nsu = nsu;
	}
	public String getBandeira() {
		return Bandeira;
	}
	public void setBandeira(String bandeira) {
		Bandeira = bandeira;
	}
	public String getNumeroCartao() {
		return numeroCartao;
	}
	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}
	public String getDataTransacao() {
		return dataTransacao;
	}
	public void setDataTransacao(String dataTransacao) {
		this.dataTransacao = dataTransacao;
	}
	public String getHoraTransacao() {
		return horaTransacao;
	}
	public void setHoraTransacao(String horaTransacao) {
		this.horaTransacao = horaTransacao;
	}

	public String getId() {
		return this.id;
	}
	public void setId(final String id) {
		this.id = id;
	}
	public String getCode() {
		return this.code;
	}
	public void setCode(final String code) {
		this.code = code;
	}
	public String getValue() {
		return this.value;
	}
	public void setValue(final String value) {
		this.value = value;
	}
	public String getCurrency() {
		return this.currency;
	}
	public void setCurrency(final String currency) {
		this.currency = currency;
	}
}
