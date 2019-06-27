package br.com.agencialove.tpa.model;

import br.com.agencia.tpa.rest.request.DestinatarioRequest;
import br.com.agencia.tpa.rest.request.RemetenteRequest;
import br.com.agencialove.tpa.model.rest.ServicesResponse;

public class Relatorio {

	
	private RemetenteRequest sender;
	private DestinatarioRequest receiver;
	private AdditionalServices additionalServices;
	private PackageMeasures measures;
	private ServicesResponse servicesResponse;
	private Agencia agencia;
	private PaymentData paymentData;
	private String etiqueta;
	private String plp;
	private RelatorioType type;
	
	
	public enum RelatorioType {POSTAGEM,EMBALAGEM}
	
	public Relatorio() {
		
	}
	
	
	public Embalagem getEmbalagem() {
		
		Embalagem embalagem = new Embalagem();
		embalagem.setBandeira(this.paymentData.getBandeira());
		embalagem.setModeloAtm(agencia.getModeloAtm());
		embalagem.setNomeAgencia(agencia.getNomeAgencia());		
		embalagem.setNumeroCartao(paymentData.getNumeroCartao());
			
		
		return embalagem;
	}
	
	public Postagem getPostagem() {
		
		Postagem postagem = new Postagem(this.getSender(), this.getReceiver(),
				this.getAdditionalServices(), this.getMeasures(), this.getServicesResponse(),
				this.getAgencia(), this.getPaymentData());
		postagem.setCodigoRastreio(this.getEtiqueta());
		postagem.setNumeroPlp(this.getPlp());
		
		return postagem;
	}
	
	public RelatorioType getType() {
		return type;
	}

	public void setType(RelatorioType type) {
		this.type = type;
	}	
	public RemetenteRequest getSender() {
		return sender;
	}
	public void setSender(RemetenteRequest sender) {
		this.sender = sender;
	}
	public DestinatarioRequest getReceiver() {
		return receiver;
	}
	public void setReceiver(DestinatarioRequest receiver) {
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
