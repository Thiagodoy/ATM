package br.com.agencialove.tpa.model;

import java.util.ArrayList;

public class LabelInfo {
	private String prePostCode;
	private String qrCode;
	private String invoiceNumber;
	private String contractNumber;
	private String service;
	private String orderNumber;
	private String objectType;
	private String width;
	private String height;
	private String depth;
	private String volume;
	private String weight;
	private String barcodePostNumber;
	private String onwHands;
	private String deliveryNotice;
	private String valueDeclaration;
	private String contentDeclaration;
	private String senderName;
	private String senderStreet;
	private String senderNumber;
	private String senderComplement;
	private String senderNeighborhood;
	private String senderCity;
	private String senderState;
	private String senderZipCode;
	private String senderBarcode;
	private String receiverName;
	private String receiverStreet;
	private String receiverNumber;
	private String receiverComplement;
	private String receiverNeighborhood;
	private String receiverCity;
	private String receiverState;
	private String receiverZipCode;
	private String receiverObs;
	private ArrayList<String> additionals;

	public LabelInfo() {
	}

	public LabelInfo(final LabelInfo pInfo) {
		this.prePostCode			= pInfo.prePostCode;
		this.qrCode					= pInfo.qrCode;
		this.invoiceNumber			= pInfo.invoiceNumber;
		this.contractNumber			= pInfo.contractNumber;
		this.service				= pInfo.service;
		this.orderNumber			= pInfo.orderNumber;
		this.volume					= pInfo.volume;
		this.weight					= pInfo.weight;
		this.barcodePostNumber		= pInfo.barcodePostNumber;
		this.onwHands				= pInfo.onwHands;
		this.deliveryNotice			= pInfo.deliveryNotice;
		this.valueDeclaration		= pInfo.valueDeclaration;
		this.senderName				= pInfo.senderName;
		this.senderStreet			= pInfo.senderStreet;
		this.senderNumber			= pInfo.senderNumber;
		this.senderComplement		= pInfo.senderComplement;
		this.senderNeighborhood		= pInfo.senderNeighborhood;
		this.senderCity				= pInfo.senderCity;
		this.senderState			= pInfo.senderState;
		this.senderZipCode			= pInfo.senderZipCode;
		this.senderBarcode			= pInfo.senderBarcode;
		this.receiverName			= pInfo.receiverName;
		this.receiverStreet			= pInfo.receiverStreet;
		this.receiverNumber			= pInfo.receiverNumber;
		this.receiverComplement		= pInfo.receiverComplement;
		this.receiverNeighborhood	= pInfo.receiverNeighborhood;
		this.receiverCity 			= pInfo.receiverCity;
		this.receiverState 			= pInfo.receiverState;
		this.receiverZipCode 		= pInfo.receiverZipCode;
		this.receiverObs 			= pInfo.receiverObs;
		this.additionals              = pInfo.additionals;

	}

	public String getInvoiceNumber() {
		return this.invoiceNumber;
	}
	public void setInvoiceNumber(final String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public String getContractNumber() {
		return this.contractNumber;
	}
	public void setContractNumber(final String contractNumber) {
		this.contractNumber = contractNumber;
	}
	public String getService() {
		return this.service;
	}
	public void setService(final String service) {
		this.service = service;
	}
	public String getOrderNumber() {
		return this.orderNumber;
	}
	public void setOrderNumber(final String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getVolume() {
		return this.volume;
	}
	public void setVolume(final String volume) {
		this.volume = volume;
	}
	public String getWeight() {
		return this.weight;
	}
	public void setWeight(final String weight) {
		this.weight = weight;
	}
	public String getBarcodePostNumber() {
		return this.barcodePostNumber;
	}
	public void setBarcodePostNumber(final String barcodePostNumber) {
		this.barcodePostNumber = barcodePostNumber;
	}
	public String getOnwHands() {
		return this.onwHands;
	}
	public void setOnwHands(final String onwHands) {
		this.onwHands = onwHands;
	}
	public String getDeliveryNotice() {
		return this.deliveryNotice;
	}
	public void setDeliveryNotice(final String deliveryNotice) {
		this.deliveryNotice = deliveryNotice;
	}
	public String getValueDeclaration() {
		return this.valueDeclaration;
	}
	public void setValueDeclaration(final String valueDeclaration) {
		this.valueDeclaration = valueDeclaration;
	}
	public String getSenderName() {
		return this.senderName;
	}
	public void setSenderName(final String senderName) {
		this.senderName = senderName;
	}
	public String getSenderStreet() {
		return this.senderStreet;
	}
	public void setSenderStreet(final String senderStreet) {
		this.senderStreet = senderStreet;
	}
	public String getSenderNumber() {
		return this.senderNumber;
	}
	public void setSenderNumber(final String senderNumber) {
		this.senderNumber = senderNumber;
	}
	public String getSenderComplement() {
		return this.senderComplement;
	}
	public void setSenderComplement(final String senderComplement) {
		this.senderComplement = senderComplement;
	}
	public String getSenderNeighborhood() {
		return this.senderNeighborhood;
	}
	public void setSenderNeighborhood(final String senderNeighborhood) {
		this.senderNeighborhood = senderNeighborhood;
	}
	public String getSenderCity() {
		return this.senderCity;
	}
	public void setSenderCity(final String senderCity) {
		this.senderCity = senderCity;
	}
	public String getSenderState() {
		return this.senderState;
	}
	public void setSenderState(final String senderState) {
		this.senderState = senderState;
	}
	public String getSenderZipCode() {
		return this.senderZipCode;
	}
	public void setSenderZipCode(final String senderZipCode) {
		this.senderZipCode = senderZipCode;
	}
	public String getSenderBarcode() {
		return this.senderBarcode;
	}
	public void setSenderBarcode(final String senderBarcode) {
		this.senderBarcode = senderBarcode;
	}
	public String getReceiverName() {
		return this.receiverName;
	}
	public void setReceiverName(final String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverStreet() {
		return this.receiverStreet;
	}
	public void setReceiverStreet(final String receiverStreet) {
		this.receiverStreet = receiverStreet;
	}
	public String getReceiverNumber() {
		return this.receiverNumber;
	}
	public void setReceiverNumber(final String receiverNumber) {
		this.receiverNumber = receiverNumber;
	}
	public String getReceiverComplement() {
		return this.receiverComplement;
	}
	public void setReceiverComplement(final String receiverComplement) {
		this.receiverComplement = receiverComplement;
	}
	public String getReceiverNeighborhood() {
		return this.receiverNeighborhood;
	}
	public void setReceiverNeighborhood(final String receiverNeighborhood) {
		this.receiverNeighborhood = receiverNeighborhood;
	}
	public String getReceiverCity() {
		return this.receiverCity;
	}
	public void setReceiverCity(final String receiverCity) {
		this.receiverCity = receiverCity;
	}
	public String getReceiverState() {
		return this.receiverState;
	}
	public void setReceiverState(final String receiverState) {
		this.receiverState = receiverState;
	}
	public String getReceiverZipCode() {
		return this.receiverZipCode;
	}
	public void setReceiverZipCode(final String receiverZipCode) {
		this.receiverZipCode = receiverZipCode;
	}
	public String getReceiverObs() {
		return this.receiverObs;
	}
	public void setReceiverObs(final String receiverObs) {
		this.receiverObs = receiverObs;
	}
	public String getQrCode() {
		return this.qrCode;
	}
	public void setQrCode(final String qrCode) {
		this.qrCode = qrCode;
	}
	public String getPrePostCode() {
		return this.prePostCode;
	}
	public void setPrePostCode(final String prePostCode) {
		this.prePostCode = prePostCode;
	}

	public String getObjectType() {
		return this.objectType;
	}

	public void setObjectType(final String objectType) {
		this.objectType = objectType;
	}

	public String getWidth() {
		return this.width;
	}

	public void setWidth(final String width) {
		this.width = width;
	}

	public String getHeight() {
		return this.height;
	}

	public void setHeight(final String height) {
		this.height = height;
	}

	public String getDepth() {
		return this.depth;
	}

	public void setDepth(final String depth) {
		this.depth = depth;
	}

	public String getContentDeclaration() {
		return this.contentDeclaration;
	}

	public void setContentDeclaration(final String contentDeclaration) {
		this.contentDeclaration = contentDeclaration;
	}

	public ArrayList<String> getAdditionals() {
		return additionals;
	}

	public void setAdditionals(ArrayList<String> addionals) {
		this.additionals = addionals;
	}
}
