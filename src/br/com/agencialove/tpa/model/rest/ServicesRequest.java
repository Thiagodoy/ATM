package br.com.agencialove.tpa.model.rest;

public class ServicesRequest {

	private String cepOrigem = "09720-971";
	private String cepDestino;
	private String peso;
	private String formato;
	private String comprimento;
	private String altura;
	private String largura;
	private String diametro;
	private String maoPropria;
	private String valorDeclarado;
	private String avisoRecebimento;

	public ServicesRequest() {
	}

	public ServicesRequest(final String cepOrigem, final String cepDestino, final String peso, final String formato,
			final String comprimento, final String altura, final String largura, final String diametro,
			final String maoPropria, final String valorDeclarado, final String avisoRecebimento) {
		this.cepOrigem = cepOrigem;
		this.cepDestino = cepDestino;
		this.peso = peso;
		this.formato = formato;
		this.comprimento = comprimento;
		this.altura = altura;
		this.largura = largura;
		this.diametro = diametro;
		this.maoPropria = maoPropria;
		this.valorDeclarado = valorDeclarado;
		this.avisoRecebimento = avisoRecebimento;
	}

	public String getCepOrigem() {
		return this.cepOrigem;
	}

	public void setCepOrigem(final String cepOrigem) {
		this.cepOrigem = "09720971";
	}

	public String getCepDestino() {
		return this.cepDestino;
	}

	public void setCepDestino(final String cepDestino) {
		this.cepDestino = cepDestino;
	}

	public String getPeso() {
		return this.peso;
	}

	public void setPeso(final String peso) {
		this.peso = peso;
	}

	public String getFormato() {
		return this.formato;
	}

	public void setFormato(final String formato) {
		this.formato = formato;
	}

	public String getComprimento() {
		return this.comprimento;
	}

	public void setComprimento(final String comprimento) {
		this.comprimento = comprimento;
	}

	public String getAltura() {
		return this.altura;
	}

	public void setAltura(final String altura) {
		this.altura = altura;
	}

	public String getLargura() {
		return this.largura;
	}

	public void setLargura(final String largura) {
		this.largura = largura;
	}

	public String getDiametro() {
		return this.diametro;
	}

	public void setDiametro(final String diametro) {
		this.diametro = diametro;
	}

	public String getMaoPropria() {
		return this.maoPropria;
	}

	public void setMaoPropria(final String maoPropria) {
		this.maoPropria = maoPropria;
	}

	public String getValorDeclarado() {
		return this.valorDeclarado;
	}

	public void setValorDeclarado(final String valorDeclarado) {
		this.valorDeclarado = valorDeclarado;
	}

	public String getAvisoRecebimento() {
		return this.avisoRecebimento;
	}

	public void setAvisoRecebimento(final String avisoRecebimento) {
		this.avisoRecebimento = avisoRecebimento;
	}

	@Override
	public String toString() {
		return "{" + "\"cepOrigem\":\"" + this.cepOrigem + "\"" + ", \"cepDestino\":\"" + this.cepDestino + "\""
				+ ", \"peso\":\"" + this.peso + "\"" + ", \"formato\":\"" + this.formato + "\"" + ", \"comprimento\":\""
				+ this.comprimento + "\"" + ", \"altura\":\"" + this.altura + "\"" + ", \"largura\":\"" + this.largura
				+ "\"" + ", \"diametro\":\"" + this.diametro + "\"" + ", \"maoPropria\":\"" + this.maoPropria + "\""
				+ ", \"valorDeclarado\":\"" + this.valorDeclarado + "\"" + ", \"avisoRecebimento\":\""
				+ this.avisoRecebimento + "\"" + "}";
	}
}