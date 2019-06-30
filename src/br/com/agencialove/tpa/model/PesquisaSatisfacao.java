package br.com.agencialove.tpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class PesquisaSatisfacao implements Serializable{
	
	private static final long serialVersionUID = -1798070786994676L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "QUESTAO_UM")
	private String questaoUm;
	
	@Column(name = "QUESTAO_DOIS")
	private String questaoDois;
	
	@Column(name = "QUESTAO_TRES")
	private String questaoTres;
	
	@Column(name = "QUESTAO_QUATRO")
	private String questaoQuatro;
	
	@Column(name = "QUESTAO_CINCO")
	private String questaoCinco;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestaoUm() {
		return questaoUm;
	}

	public void setQuestaoUm(String questaoUm) {
		this.questaoUm = questaoUm;
	}

	public String getQuestaoDois() {
		return questaoDois;
	}

	public void setQuestaoDois(String questaoDois) {
		this.questaoDois = questaoDois;
	}

	public String getQuestaoTres() {
		return questaoTres;
	}

	public void setQuestaoTres(String questaoTres) {
		this.questaoTres = questaoTres;
	}

	public String getQuestaoQuatro() {
		return questaoQuatro;
	}

	public void setQuestaoQuatro(String questaoQuatro) {
		this.questaoQuatro = questaoQuatro;
	}

	public String getQuestaoCinco() {
		return questaoCinco;
	}

	public void setQuestaoCinco(String questaoCinco) {
		this.questaoCinco = questaoCinco;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
