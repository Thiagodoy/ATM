package br.com.agencialove.tpa.model;

import java.text.MessageFormat;
import java.util.Optional;

public class Address {

	private Person person;
	private String zip;
	//Estado
	private String state;
	private String city;
	private String street;
	private String complement;
	private String neighborhood;
	private String number;
	private String type;

	public Address() {
		this.person = new Person();
	}

	public Address(final Person pPerson,
			final String pZip,
			final String pState,
			final String pCity,
			final String pAddress,
			final String pComplement,
			final String pNeighborhood,
			final String pType) {
		this.person = pPerson;
		this.zip = pZip;
		this.state = pState;
		this.city = pCity;
		this.street = pAddress;
		this.complement = pComplement;
		this.neighborhood = pNeighborhood;
		this.type = pType;
	}

	public Address(final String pZip,
			final String pState,
			final String pCity,
			final String pAddress,
			final String pComplement,
			final String pNeighborhood) {
		this.zip = pZip;
		this.state = pState;
		this.city = pCity;
		this.street = pAddress;
		this.neighborhood = pNeighborhood;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(final Person pPerson) {
		this.person = pPerson;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(final String pZip) {
		this.zip = pZip;
	}

	public String getState() {
		return this.state;
	}

	public void setState(final String pState) {
		this.state = pState;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(final String pCity) {
		this.city = pCity;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(final String pAddress) {
		this.street = pAddress;
	}

	public String getComplement() {
		return this.complement;
	}

	public void setComplement(final String pComplement) {
		this.complement = pComplement;
	}

	public String getNeighborhood() {
		return this.neighborhood;
	}

	public void setNeighborhood(final String pNeighborhood) {
		this.neighborhood = pNeighborhood;
	}

	public String getType() {
		return this.type;
	}

	public void setType(final String pType) {
		this.type = pType;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(final String pNumber) {
		this.number = pNumber;
	}

	@Override
	public String toString() {
		return "Address{" +
				"ZIP='" + this.zip + '\'' +
				", STATE='" + this.state + '\'' +
				", PLACE='" + this.city + '\'' +
				", ADDRESS='" + this.street + '\'' +
				", COMPLEMENT='" + this.complement + '\'' +
				", NEIGHBORHOOD='" + this.neighborhood + '\'' +
				", TYPE='" + this.type + '\'' +
				'}';
	}
	
	public String toFormatedAddress() {
		String street = Optional.ofNullable(this.street).isPresent() ? this.street : "";
		String neighborhood = Optional.ofNullable(this.neighborhood).isPresent() ? this.neighborhood : "";
		String number = Optional.ofNullable(this.number).isPresent() ? this.number : "";
		String city = Optional.ofNullable(this.city).isPresent() ? this.city : "";
		String state = Optional.ofNullable(this.state).isPresent() ? this.state : "";
		String complement = Optional.ofNullable(this.complement).isPresent() ? this.complement : "";
		String zipCode = Optional.ofNullable(this.zip).isPresent() ? this.zip : "";
		
		return MessageFormat.format("Endereço: {0}, n° {1} - {2}, {3} - {4} \nComplemento: {5}\nCep{6}",street,number, neighborhood, city,state,complement,zipCode );
	}
}
