package br.com.agencialove.tpa.utils;

public enum Stream {
	
	ENCOMENDA("",""),
	EMBALAGEM("embalagem","src/embalagem.xml");
	
	private String streamId;
	private String streamFile;
	
	private Stream(String streamId, String streamFile) {
		this.streamId = streamId;
		this.streamFile = streamFile;
	}

	public String getStreamFile() {
		return this.streamFile;
	}
	
	public String getStreamId() {
		return this.streamId;
	}
}
