package br.com.agencia.rest;

public class Error{
	
	 
	private String localDateTime;
	private String message;
	
	public Error() {
		
	}
	
	
	public String getLocalDateTime() {
		return localDateTime;
	}
	public void setLocalDateTime(String localDateTime) {
		this.localDateTime = localDateTime;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
