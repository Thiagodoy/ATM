package br.com.agencialove.tpa.utils;

public enum Url {

	CORREIOS("http://www.correios.com.br"),
	SATISFACAO("http://www2.correios.com.br/hotsites/pesquisaditec/default.cfm?pesquisa=27705&publico=g");
	
	private String url;
	
	Url(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return this.url;
	}
}
