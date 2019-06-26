package br.com.agencia.tpa.rest.request;

import java.util.ArrayList;
import java.util.List;

public class DeclaracaoConteudoRequest {
	
	private List<ItemDeclaracaoConteudoRequest> itens = new ArrayList<ItemDeclaracaoConteudoRequest>();

	public List<ItemDeclaracaoConteudoRequest> getItens() {
		return itens;
	}

	public void setItens(List<ItemDeclaracaoConteudoRequest> itens) {
		this.itens = itens;
	}
	
	private double total;

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	

}
