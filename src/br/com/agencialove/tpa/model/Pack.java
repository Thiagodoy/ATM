package br.com.agencialove.tpa.model;

import javafx.scene.image.Image;

public class Pack {
	private String codigo;
	private String descricao;
	private String dimensoes;
	private String valor;
	private String dispenser;
	private Long quantidade;
	private Image imagem;
	public Pack() {
	}
	public Pack(final String pCodigo, final String pDescricao, final String pDimensoes, final String pValor, final String pDispenser) {
		this.codigo = pCodigo;
		this.descricao = pDescricao;
		this.dimensoes = pDimensoes;
		this.valor = pValor;
		this.dispenser = pDispenser;
	}
	
	
	public Long getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
	public String getValor() {
		return this.valor;
	}
	public void setValor(final String valor) {
		this.valor = valor;
	}
	public String getCodigo() {
		return this.codigo;
	}
	public void setCodigo(final String codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return this.descricao;
	}
	public void setDescricao(final String descricao) {
		this.descricao = descricao;
	}
	public Image getImagem() {
		return this.imagem;
	}
	public void setImagem(final Image imagem) {
		this.imagem = imagem;
	}
	public String getDispenser() {
		return this.dispenser;
	}
	public void setDispenser(final String dispenser) {
		this.dispenser = dispenser;
	}
	public String getDimensoes() {
		return this.dimensoes;
	}
	public void setDimensoes(final String dimensoes) {
		this.dimensoes = dimensoes;
	}	
	
}
