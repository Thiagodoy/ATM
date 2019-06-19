package br.com.agencialove.tpa.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Embalagem implements Serializable{

	private static final long serialVersionUID = 3242355L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NOME_AGENCIA")
	private String nomeAgencia;
	
	@Column(name = "MODELO_ATM")
	private String modeloAtm;
	
	@Column(name = "DATA_TRANSACAO")
	//@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime dataTransacao;
	
//	@Column(name = "HORA_TRANSACAO")
//	private String horaTransacao;
	
	@Column(name = "NOME_COMPRADOR")
	private String nomeComprador;
	
	@Column(name = "CPF_CNPJ")
	private Long cpfCnpj;
	
	@Column(name = "ENDERECO")
	private String endereco;
	
	@Column(name = "TELEFONE")
	private String telefone;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "VALOR_COMPRA")
	private Double valorCompra;
	
	@Column(name = "EMBALAGEM_TIPO_1")
	private Long embalagemTipo1;
	
	@Column(name = "QUANTIDADE_TIPO_1")
	private Long quantidadeTipo1;
	
	@Column(name = "VALOR_TIPO_1")
	private Double valorTipo1;

	@Column(name = "EMBALAGEM_TIPO_2")
	private Long embalagemTipo2;
	
	@Column(name = "QUANTIDADE_TIPO_2")
	private Long quantidadeTipo2;
	
	@Column(name = "VALOR_TIPO_2")
	private Double valorTipo2;
	
	@Column(name = "EMBALAGEM_TIPO_4B")
	private Long embalagemTipo4B;
	
	@Column(name = "QUANTIDADE_TIPO_4B")
	private Long quantidadeTipo4B;
	
	@Column(name = "VALOR_TIPO_4B")
	private Double valorTipo4B;
	
	@Column(name = "BANDEIRA")
	private String bandeira;
	
	@Column(name = "NUMERO_CARTAO")
	private String numeroCartao;
	
	@Column(name = "NSU")
	private String nsu;
	
	@Column(name = "TIPO_PAGAMENTO")
	private String tipoPagamento;
	
	@Column(name = "DISPONIBILIDADE_ATM")
	private Double disponibilidadeAtm;
	
	@Column(name = "FUNCIOANAMENTO_REGULAR")
	private Double funcionamentoRegular;
	
	@Column(name = "TAXA_EFETIVIDADE")
	private Double taxaEfetividade;
	
	@Column(name = "TEMPO_RESPOSTA")
	private String tempoPostagem;	
	

	@Column(name = "STATUS")	
	private String status;

	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeAgencia() {
		return nomeAgencia;
	}

	public void setNomeAgencia(String nomeAgencia) {
		this.nomeAgencia = nomeAgencia;
	}

	public String getModeloAtm() {
		return modeloAtm;
	}

	public void setModeloAtm(String modeloAtm) {
		this.modeloAtm = modeloAtm;
	}

	public LocalDateTime getDataTransacao() {
		return dataTransacao;
	}

	public void setDataTransacao(LocalDateTime dataTransacao) {
		this.dataTransacao = dataTransacao;
	}


	public String getNomeComprador() {
		return nomeComprador;
	}

	public void setNomeComprador(String nomeComprador) {
		this.nomeComprador = nomeComprador;
	}

	public Long getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(Long cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Double getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(Double valorCompra) {
		this.valorCompra = valorCompra;
	}

	public Long getEmbalagemTipo1() {
		return embalagemTipo1;
	}

	public void setEmbalagemTipo1(Long embalagemTipo1) {
		this.embalagemTipo1 = embalagemTipo1;
	}

	public Long getQuantidadeTipo1() {
		return quantidadeTipo1;
	}

	public void setQuantidadeTipo1(Long quantidadeTipo1) {
		this.quantidadeTipo1 = quantidadeTipo1;
	}

	public Double getValorTipo1() {
		return valorTipo1;
	}

	public void setValorTipo1(Double valorTipo1) {
		this.valorTipo1 = valorTipo1;
	}

	public Long getEmbalagemTipo2() {
		return embalagemTipo2;
	}

	public void setEmbalagemTipo2(Long embalagemTipo2) {
		this.embalagemTipo2 = embalagemTipo2;
	}

	public Long getQuantidadeTipo2() {
		return quantidadeTipo2;
	}

	public void setQuantidadeTipo2(Long quantidadeTipo2) {
		this.quantidadeTipo2 = quantidadeTipo2;
	}

	public Double getValorTipo2() {
		return valorTipo2;
	}

	public void setValorTipo2(Double valorTipo2) {
		this.valorTipo2 = valorTipo2;
	}

	public Long getEmbalagemTipo4B() {
		return embalagemTipo4B;
	}

	public void setEmbalagemTipo4B(Long embalagemTipo4B) {
		this.embalagemTipo4B = embalagemTipo4B;
	}

	public Long getQuantidadeTipo4B() {
		return quantidadeTipo4B;
	}

	public void setQuantidadeTipo4B(Long quantidadeTipo4B) {
		this.quantidadeTipo4B = quantidadeTipo4B;
	}

	public Double getValorTipo4B() {
		return valorTipo4B;
	}

	public void setValorTipo4B(Double valorTipo4B) {
		this.valorTipo4B = valorTipo4B;
	}

	public String getBandeira() {
		return bandeira;
	}

	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public String getNsu() {
		return nsu;
	}

	public void setNsu(String nsu) {
		this.nsu = nsu;
	}

	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public Double getDisponibilidadeAtm() {
		return disponibilidadeAtm;
	}

	public void setDisponibilidadeAtm(Double disponibilidadeAtm) {
		this.disponibilidadeAtm = disponibilidadeAtm;
	}

	public Double getFuncionamentoRegular() {
		return funcionamentoRegular;
	}

	public void setFuncionamentoRegular(Double funcionamentoRegular) {
		this.funcionamentoRegular = funcionamentoRegular;
	}

	public Double getTaxaEfetividade() {
		return taxaEfetividade;
	}

	public void setTaxaEfetividade(Double taxaEfetividade) {
		this.taxaEfetividade = taxaEfetividade;
	}

	public String getTempoPostagem() {
		return tempoPostagem;
	}

	public void setTempoPostagem(String tempoPostagem) {
		this.tempoPostagem = tempoPostagem;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
			

}
