package br.com.agencialove.tpa.model;

public class Person {

	private String name;
	private ZipType type; //001 para remetente e 002 para destinatário
	private String cpf_cnpj;
	private String cellPhone;
	private String email;
	

	public Person(final String pName, final String cpf_cnpj, final String pEmail, final ZipType pZipType) {
		this.name = pName;
		this.cpf_cnpj = cpf_cnpj;
		this.email = pEmail;
		this.type = pZipType;
	}

	public Person() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String nome) {
		this.name = nome;
	}

	public ZipType getType() {
		return this.type;
	}

	public void setType(final ZipType pZipType) {
		this.type = pZipType;
	}

	public String getCPF_CPNJ() {
		return this.cpf_cnpj;
	}

	public void setCPF_CPNJ(final String celular) {
		this.cpf_cnpj = celular;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Person{" +
				"name='" + this.name + '\'' +
				", type='" + this.type + '\'' +
				'}';
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
}
