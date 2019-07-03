package br.com.agencia.rest;

public class ApiException extends Exception {

	private static final long serialVersionUID = 6836164215826703658L;
	private String message;
	public String getMessage() {
		return message;
	}
	
	
	
	public void setMessage(String message) {
		this.message = message;
	}
	public ApiException(Error erro) {
		this.message = erro.getMessage();
	}
	
	public ApiException(String erro) {
		this.message = erro;
	}
}
