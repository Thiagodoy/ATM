package br.com.agencialove.tpa.model.rest;

public class PrePostResponse {
	private String numeroPLP;
	private String numeroEtiqueta;

	public String getNumeroPLP() {
		return this.numeroPLP;
	}
	public void setNumeroPLP(final String numeroPLP) {
		this.numeroPLP = numeroPLP;
	}
	public String getNumeroEtiqueta() {
		return this.numeroEtiqueta;
	}
	public void setNumeroEtiqueta(final String numeroEtiqueta) {
		this.numeroEtiqueta = numeroEtiqueta;
	}
	
	 public String toJSON() {
		 final String ret = "{" +
					"\"numeroPLP\":\"" + this.numeroPLP + "\","
					+ "\"numeroEtiqueta\":\"" + this.numeroEtiqueta + "\" },";
			return ret;
    }
}
