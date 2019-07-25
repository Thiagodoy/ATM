package br.com.agencia.tpa.rest.request;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.agencia.tpa.rest.response.ServicoAdicionalResponse;
import br.com.agencialove.tpa.utils.Utils;

public class ServicoAdicionalRequest {
	
//	@JsonProperty(value = "status_processamento", required = true)
//	public String status;
	
	@JsonProperty(value = "codigo_servico_adicional", required = true)
	public List<String> codigos = new ArrayList<String>();
	
	
	@JsonProperty(value = "valor_declarado", required = false)
	public Double valor;
	
	
	public ServicoAdicionalRequest() {
		
	}
	
	public ServicoAdicionalRequest(ServicoAdicionalResponse response) {
		this.codigos = response.getCodigo_servico_adicional();
		
		String value = (response.getValor_declarado() == null || response.getValor_declarado().length() == 0 ) ? "0" : response.getValor_declarado(); 
		
		
		this.valor = Utils.parse(value);
	}

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



	
	
	

}
