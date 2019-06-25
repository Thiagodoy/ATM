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
	
}
