package br.com.agencia.tpa.rest.request;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ServicoAdicionalRequest {
	
//	@JsonProperty(value = "status_processamento", required = true)
//	public String status;
	
	@JsonProperty(value = "codigo_servico_adicional", required = true)
	public List<String> codigos = new ArrayList<String>();
	
	
	@JsonProperty(value = "valor_declarado", required = false)
	public Double valor;
	
//	@JsonProperty(value = "valor_cobrado", required = false)
//	public Double valorCobrado;


	public List<String> getCodigos() {
		return codigos;
	}


	public void setCodigos(List<String> codigo) {
		this.codigos = codigo;
	}


	public Double getValor() {
		return valor;
	}


	public void setValor(Double valor) {
		this.valor = valor;
	}


//	public String getStatus() {
//		return status;
//	}
//
//
//	public void setStatus(String status) {
//		this.status = status;
//	}


//	public Double getValorCobrado() {
//		return valorCobrado;
//	}
//
//
//	public void setValorCobrado(Double valorCobrado) {
//		this.valorCobrado = valorCobrado;
//	}
	
	
	
	

}
