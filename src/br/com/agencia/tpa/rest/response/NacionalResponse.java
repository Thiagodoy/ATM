package br.com.agencia.tpa.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NacionalResponse {

	@JsonProperty(value = "validadorCEP")
	private String validadorCEP;
	@JsonProperty(value = "bairro_destinatario")
	private String bairro_destinatario;
	@JsonProperty(value = "cidade_destinatario")
	private String cidade_destinatario;
	@JsonProperty(value = "uf_destinatario")
	private String uf_destinatario;
	@JsonProperty(value = "cep_destinatario")
	private String cep_destinatario;
	@JsonProperty(value = "codigo_usuario_postal")
	private String codigo_usuario_postal;
	@JsonProperty(value = "centro_custo_cliente")
	private String centro_custo_cliente;
	@JsonProperty(value = "numero_nota_fiscal")
	private String numero_nota_fiscal;
	@JsonProperty(value = "serie_nota_fiscal")
	private String serie_nota_fiscal;
	@JsonProperty(value = "valor_nota_fiscal")
	private String valor_nota_fiscal;
	@JsonProperty(value = "natureza_nota_fiscal")
	private String natureza_nota_fiscal;
	@JsonProperty(value = "descricao_objeto")
	private String descricao_objeto;
	@JsonProperty(value = "valor_a_cobrar")
	private String valor_a_cobrar;
	public String getValidadorCEP() {
		return validadorCEP;
	}
	public void setValidadorCEP(String validadorCEP) {
		this.validadorCEP = validadorCEP;
	}
	public String getBairro_destinatario() {
		return bairro_destinatario;
	}
	public void setBairro_destinatario(String bairro_destinatario) {
		this.bairro_destinatario = bairro_destinatario;
	}
	public String getCidade_destinatario() {
		return cidade_destinatario;
	}
	public void setCidade_destinatario(String cidade_destinatario) {
		this.cidade_destinatario = cidade_destinatario;
	}
	public String getUf_destinatario() {
		return uf_destinatario;
	}
	public void setUf_destinatario(String uf_destinatario) {
		this.uf_destinatario = uf_destinatario;
	}
	public String getCep_destinatario() {
		return cep_destinatario;
	}
	public void setCep_destinatario(String cep_destinatario) {
		this.cep_destinatario = cep_destinatario;
	}
	public String getCodigo_usuario_postal() {
		return codigo_usuario_postal;
	}
	public void setCodigo_usuario_postal(String codigo_usuario_postal) {
		this.codigo_usuario_postal = codigo_usuario_postal;
	}
	public String getCentro_custo_cliente() {
		return centro_custo_cliente;
	}
	public void setCentro_custo_cliente(String centro_custo_cliente) {
		this.centro_custo_cliente = centro_custo_cliente;
	}
	public String getNumero_nota_fiscal() {
		return numero_nota_fiscal;
	}
	public void setNumero_nota_fiscal(String numero_nota_fiscal) {
		this.numero_nota_fiscal = numero_nota_fiscal;
	}
	public String getSerie_nota_fiscal() {
		return serie_nota_fiscal;
	}
	public void setSerie_nota_fiscal(String serie_nota_fiscal) {
		this.serie_nota_fiscal = serie_nota_fiscal;
	}
	public String getValor_nota_fiscal() {
		return valor_nota_fiscal;
	}
	public void setValor_nota_fiscal(String valor_nota_fiscal) {
		this.valor_nota_fiscal = valor_nota_fiscal;
	}
	public String getNatureza_nota_fiscal() {
		return natureza_nota_fiscal;
	}
	public void setNatureza_nota_fiscal(String natureza_nota_fiscal) {
		this.natureza_nota_fiscal = natureza_nota_fiscal;
	}
	public String getDescricao_objeto() {
		return descricao_objeto;
	}
	public void setDescricao_objeto(String descricao_objeto) {
		this.descricao_objeto = descricao_objeto;
	}
	public String getValor_a_cobrar() {
		return valor_a_cobrar;
	}
	public void setValor_a_cobrar(String valor_a_cobrar) {
		this.valor_a_cobrar = valor_a_cobrar;
	}
	
	

}
