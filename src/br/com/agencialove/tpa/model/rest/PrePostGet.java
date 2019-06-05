package br.com.agencialove.tpa.model.rest;

public class PrePostGet {

	private String nPlp;
	private String nEtiqueta;
	
	public PrePostGet() {}

	public PrePostGet(final String nPlp, final String nEtiqueta) {
		this.nPlp = nPlp;
		this.nEtiqueta = nEtiqueta;
	}
	
	public String getNplp() {
		return this.nPlp;
	}

	public void setNplp(final String nPlp) {
		this.nPlp = nPlp;
	}
	
	public String getNetiqueta() {
		return this.nEtiqueta;
	}

	public void setNetiqueta(final String nEtiqueta) {
		this.nEtiqueta = nEtiqueta;
	}

}	
