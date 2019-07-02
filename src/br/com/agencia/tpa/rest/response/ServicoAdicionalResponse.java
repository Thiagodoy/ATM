package br.com.agencia.tpa.rest.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ServicoAdicionalResponse {

	@JsonProperty(value = "codigoServicosAdicionaisFormatado")
	private String codigoServicosAdicionaisFormatado;
	@JsonProperty(value = "valorDeclaradoFormatado")
	private String valorDeclaradoFormatado;
	@JsonProperty(value = "codigo_servico_adicional")
	private List<String> codigo_servico_adicional;
	@JsonProperty(value = "valor_declarado")
	private String valor_declarado;
	public String getCodigoServicosAdicionaisFormatado() {
		return codigoServicosAdicionaisFormatado;
	}
	public void setCodigoServicosAdicionaisFormatado(String codigoServicosAdicionaisFormatado) {
		this.codigoServicosAdicionaisFormatado = codigoServicosAdicionaisFormatado;
	}
	public String getValorDeclaradoFormatado() {
		return valorDeclaradoFormatado;
	}
	public void setValorDeclaradoFormatado(String valorDeclaradoFormatado) {
		this.valorDeclaradoFormatado = valorDeclaradoFormatado;
	}
	public List<String> getCodigo_servico_adicional() {
		return codigo_servico_adicional;
	}
	public void setCodigo_servico_adicional(List<String> codigo_servico_adicional) {
		this.codigo_servico_adicional = codigo_servico_adicional;
	}
	public String getValor_declarado() {
		return valor_declarado;
	}
	public void setValor_declarado(String valor_declarado) {
		this.valor_declarado = valor_declarado;
	}
	
	
	

}
