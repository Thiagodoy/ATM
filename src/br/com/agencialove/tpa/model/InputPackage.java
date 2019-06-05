package br.com.agencialove.tpa.model;

public class InputPackage {

	private String altura;
	private String diametro;
	private String comprimento;	
	private String largura;	
	private String profundidade;
	
	public InputPackage() {
		// TODO Auto-generated constructor stub
	}
	
	public InputPackage(String altura, String largura, String comprimento, String diametro, String profundidade) {
		this.setAltura(altura);
		this.setDiametro(diametro);
		this.setComprimento(comprimento);
		this.setLargura(largura);		
		this.setProfundidade(profundidade);
	}
	
	
	public void setAltura(String altura) {
		this.altura = altura;
	}
	
	public void setComprimento(String comprimento) {
		this.comprimento = comprimento;
	}
	
	public void setDiametro(String diametro) {
		this.diametro = diametro;
	}
	
	public void setLargura(String largura) {
		this.largura = largura;
	}
	
	public void setProfundidade(String profundidade) {
		this.profundidade = profundidade;
	}
	
	public String getAltura() {
		return altura;
	}
	
	public String getComprimento() {
		return comprimento;
	}
	
	public String getDiametro() {
		return diametro;
	}
	
	public String getLargura() {
		return largura;
	}

	public String getProfundiade() {
		return profundidade;
	}
	
	@Override
	public String toString() {
		return "Address{" +
				"Altura ='" + this.altura + '\'' +
				", Largura='" + this.largura + '\'' +
				", Comprimento='" + this.comprimento + '\'' +
				", Diametro='" + this.diametro + '\'' +
				", Profundidade='" + this.profundidade + '\'' +
				
				'}';
	}
}
