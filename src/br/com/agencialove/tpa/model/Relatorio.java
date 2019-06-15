package br.com.agencialove.tpa.model;

import br.com.agencialove.tpa.model.rest.ServicesResponse;

public class Relatorio {

	
	private Address sender;
	private Address receiver;
	private AdditionalServices additionalServices;
	private PackageMeasures measures;
	private ServicesResponse servicesResponse;
	private Agencia agencia;
	private PaymentData paymentData;
	private String etiqueta;
	private String plp;
	
	
	
	
	public Relatorio(Address sender, Address receiver, AdditionalServices additionalServices, PackageMeasures measures,
			ServicesResponse servicesResponse, Agencia agencia, PaymentData paymentData, String etiqueta, String plp) {
		super();
		this.sender = sender;
		this.receiver = receiver;
		this.additionalServices = additionalServices;
		this.measures = measures;
		this.servicesResponse = servicesResponse;
		this.agencia = agencia;
		this.paymentData = paymentData;
		this.etiqueta = etiqueta;
		this.plp = plp;
	}
	
	
	public Address getSender() {
		return sender;
	}
	public void setSender(Address sender) {
		this.sender = sender;
	}
	public Address getReceiver() {
		return receiver;
	}
	public void setReceiver(Address receiver) {
		this.receiver = receiver;
	}
	public AdditionalServices getAdditionalServices() {
		return additionalServices;
	}
	public void setAdditionalServices(AdditionalServices additionalServices) {
		this.additionalServices = additionalServices;
	}
	public PackageMeasures getMeasures() {
		return measures;
	}
	public void setMeasures(PackageMeasures measures) {
		this.measures = measures;
	}
	public ServicesResponse getServicesResponse() {
		return servicesResponse;
	}
	public void setServicesResponse(ServicesResponse servicesResponse) {
		this.servicesResponse = servicesResponse;
	}
	public Agencia getAgencia() {
		return agencia;
	}
	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}
	public PaymentData getPaymentData() {
		return paymentData;
	}
	public void setPaymentData(PaymentData paymentData) {
		this.paymentData = paymentData;
	}
	public String getEtiqueta() {
		return etiqueta;
	}
	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}
	public String getPlp() {
		return plp;
	}
	public void setPlp(String plp) {
		this.plp = plp;
	}
	
	
}
