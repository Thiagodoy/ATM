package br.com.agencialove.tpa.model;

import br.com.agencia.tpa.rest.request.DestinatarioRequest;
import br.com.agencia.tpa.rest.request.RemetenteRequest;
import br.com.agencialove.tpa.model.rest.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.agencialove.tpa.model.rest.ServicesResponse;

@Entity
public class Postagem implements Serializable{
	
	private static final long serialVersionUID = -1798070786993154676L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NOME_AGENCIA")
	private String nomeAgencia;
	
	@Column(name = "MODELO_ATM")
	private String modeloAtm;
	
	@Column(name = "DATA_TRANSACAO")
	private Date dataTransacao;
	
	@Column(name = "HORA_TRANSACAO")
	private String horaTransacao;
	
	@Column(name = "TIPO_SERVICO")
	private Long tipoServico;
	
	@Column(name = "CODIGO_RASTREIO")
	private String codigoRastreio;
	
	@Column(name = "NUMERO_PLP")
	private String numeroPlp;
	
	@Column(name = "VALOR_VENDA")
	private Double valorVenda;
	
	@Column(name = "VALOR_MP")
	private Double valorMp;
	
	@Column(name = "VALOR_VD")
	private Double valorVd;
	
	@Column(name = "VALOR_AR")
	private Double valorAr;
	
	@Column(name = "DISPONIBILIDADE_ATM")
	private Double disponibilidadeAtm;
	
	@Column(name = "FUNCIOANAMENTO_REGULAR")
	private Double funcionamentoRegular;
	
	@Column(name = "TAXA_EFETIVIDADE")
	private Double taxaEfetividade;
	
	@Column(name = "TEMPO_RESPOSTA")
	private String tempoPostagem;
	
	@Column(name = "MCU")
	private Long mcu;
	
	@Column(name = "CNPJ_AGENCIA")
	private Long cnpjAgencia;
	
	@Column(name = "INCRICAO_ESTADUAL")
	private Long inscricaoEstadual;
	
	@Column(name = "NUMERO_CARTAO_POSTAGEM")
	private Long numeroCartaoPostagem;
	
	@Column(name = "PESO_OBJETO")
	private Long pesoObjeto;
	
	@Column(name = "DIMENSAO_OBJETO_LARGURA")
	private Double DimensaoObjetoLargura;
	
	@Column(name = "DIMENSAO_OBJETO_ALTURA")
	private Double DimensaoObjetoAltura;
	
	@Column(name = "DIMENSAO_OBJETO_COMPRIMENTO")
	private Double DimensaoObjetoComprimento;
	
	@Column(name = "SERVICO_ADICIONAL_MP")
	private Long servicoAdicionalMp;
	
	@Column(name = "SERVICO_ADICIONAL_VD")
	private Long servicoAdicionalVd;
	
	@Column(name = "SERVICO_ADICIONAL_AR")
	private Long servicoAdicionalAr;
	
	@Column(name = "BANDEIRA")
	private Long bandeira;
	
	@Column(name = "NUMERO_CARTAO")
	private String numeroCartao;
	
	@Column(name = "NSU")
	private String nsu;
	
	@Column(name = "TIPO_PAGAMENTO")
	private String tipoPagamento;
	
	@Column(name = "REMETENTE_NOME")
	private String remetenteNome;
	
	@Column(name = "REMETENTE_ENDERECO")
	private String remetenteEndereco;
	
	@Column(name = "REMETENTE_TELEFONE")
	private String remetenteTelefone;
	
	@Column(name = "REMETENTE_EMAIL")
	private String remetenteEmail;
	
	@Column(name = "DESTINATARIO_NOME")
	private String destinatarioNome;
	
	@Column(name = "DESTINATARIO_ENDERECO")
	private String destinatarioEndereco;
	
	@Column(name = "DESTINATARIO_TELEFONE")
	private String destinatarioTelefone;
	
	@Column(name = "DESTINATARIO_EMAIL")
	private String destinatarioEmail;	
	
	@Column(name = "STATUS")
	private String status;
	
	
	public Postagem() {
		
	}
	
	public Postagem(RemetenteRequest sender,DestinatarioRequest receiver, AdditionalServices additionalServices,PackageMeasures measures, ServicesResponse servicesResponse, Agencia agencia, PaymentData paymentData ) {
		this.remetenteNome = sender.getNome();
		this.remetenteEmail = sender.getEmail();
		this.remetenteEndereco = sender.toFormatedAddress();
		this.remetenteTelefone = String.valueOf(sender.getCelular());
		
		this.destinatarioNome = receiver.getNome();
		this.destinatarioEmail = receiver.getEmail();
		this.destinatarioEndereco = receiver.toFormatedAddress();
		this.destinatarioTelefone = String.valueOf(sender.getCelular());
		
		this.nomeAgencia = agencia.getNomeAgencia();
		this.modeloAtm = agencia.getModeloAtm();
		this.cnpjAgencia = Long.valueOf(agencia.getCnpjAgencia());
		this.mcu = Long.valueOf(agencia.getMcuUnidade());
		this.numeroCartaoPostagem = Long.valueOf(agencia.getCartaoPostagem());		
		
		this.horaTransacao = paymentData.getHoraTransacao();
		this.bandeira = Long.valueOf(paymentData.getBandeira());
		this.valorVenda = Double.valueOf(paymentData.getValue().replace(",", "."));
		this.tipoPagamento = paymentData.getFormaPagamento();	
		
		
		//:FIXME precisa definir de onde vira		
		this.tipoServico = 1l;
		this.valorMp = 0.0d;
		this.valorAr = 0.0d;
		this.valorVd = 0.0d;
		this.inscricaoEstadual = 1l;
		
		
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");		
		if(paymentData.getDataTransacao() != null) {
			try {
				this.dataTransacao = formatter.parse(paymentData.getDataTransacao());
			} catch (ParseException e) {			
				e.printStackTrace();
			}
		}
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public Date getDataTransacao() {
		return dataTransacao;
	}
	public void setDataTransacao(Date dataTransacao) {
		this.dataTransacao = dataTransacao;
	}
	public String getHoraTransacao() {
		return horaTransacao;
	}
	public void setHoraTransacao(String horaTransacao) {
		this.horaTransacao = horaTransacao;
	}
	public Long getTipoServico() {
		return tipoServico;
	}
	public void setTipoServico(Long tipoServico) {
		this.tipoServico = tipoServico;
	}
	public String getCodigoRastreio() {
		return codigoRastreio;
	}
	public void setCodigoRastreio(String codigoRastreio) {
		this.codigoRastreio = codigoRastreio;
	}
	public String getNumeroPlp() {
		return numeroPlp;
	}
	public void setNumeroPlp(String numeroPlp) {
		this.numeroPlp = numeroPlp;
	}
	public Double getValorVenda() {
		return valorVenda;
	}
	public void setValorVenda(Double valorVenda) {
		this.valorVenda = valorVenda;
	}
	public Double getValorMp() {
		return valorMp;
	}
	public void setValorMp(Double valorMp) {
		this.valorMp = valorMp;
	}
	public Double getValorVd() {
		return valorVd;
	}
	public void setValorVd(Double valorVd) {
		this.valorVd = valorVd;
	}
	public Double getValorAr() {
		return valorAr;
	}
	public void setValorAr(Double valorAr) {
		this.valorAr = valorAr;
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
	public Long getMcu() {
		return mcu;
	}
	public void setMcu(Long mcu) {
		this.mcu = mcu;
	}
	public Long getCnpjAgencia() {
		return cnpjAgencia;
	}
	public void setCnpjAgencia(Long cnpjAgencia) {
		this.cnpjAgencia = cnpjAgencia;
	}
	public Long getInscricaoEstadual() {
		return inscricaoEstadual;
	}
	public void setInscricaoEstadual(Long inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}
	public Long getNumeroCartaoPostagem() {
		return numeroCartaoPostagem;
	}
	public void setNumeroCartaoPostagem(Long numeroCartaoPostagem) {
		this.numeroCartaoPostagem = numeroCartaoPostagem;
	}
	public Long getPesoObjeto() {
		return pesoObjeto;
	}
	public void setPesoObjeto(Long pesoObjeto) {
		this.pesoObjeto = pesoObjeto;
	}
	public Double getDimensaoObjetoLargura() {
		return DimensaoObjetoLargura;
	}
	public void setDimensaoObjetoLargura(Double dimensaoObjetoLargura) {
		DimensaoObjetoLargura = dimensaoObjetoLargura;
	}
	public Double getDimensaoObjetoAltura() {
		return DimensaoObjetoAltura;
	}
	public void setDimensaoObjetoAltura(Double dimensaoObjetoAltura) {
		DimensaoObjetoAltura = dimensaoObjetoAltura;
	}
	public Double getDimensaoObjetoComprimento() {
		return DimensaoObjetoComprimento;
	}
	public void setDimensaoObjetoComprimento(Double dimensaoObjetoComprimento) {
		DimensaoObjetoComprimento = dimensaoObjetoComprimento;
	}
	public Long getServicoAdicionalMp() {
		return servicoAdicionalMp;
	}
	public void setServicoAdicionalMp(Long servicoAdicionalMp) {
		this.servicoAdicionalMp = servicoAdicionalMp;
	}
	public Long getServicoAdicionalVd() {
		return servicoAdicionalVd;
	}
	public void setServicoAdicionalVd(Long servicoAdicionalVd) {
		this.servicoAdicionalVd = servicoAdicionalVd;
	}
	public Long getServicoAdicionalAr() {
		return servicoAdicionalAr;
	}
	public void setServicoAdicionalAr(Long servicoAdicionalAr) {
		this.servicoAdicionalAr = servicoAdicionalAr;
	}
	public Long getBandeira() {
		return bandeira;
	}
	public void setBandeira(Long bandeira) {
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
	public String getRemetenteNome() {
		return remetenteNome;
	}
	public void setRemetenteNome(String remetenteNome) {
		this.remetenteNome = remetenteNome;
	}
	public String getRemetenteEndereco() {
		return remetenteEndereco;
	}
	public void setRemetenteEndereco(String remetenteEndereco) {
		this.remetenteEndereco = remetenteEndereco;
	}
	public String getRemetenteTelefone() {
		return remetenteTelefone;
	}
	public void setRemetenteTelefone(String remetenteTelefone) {
		this.remetenteTelefone = remetenteTelefone;
	}
	public String getRemetenteEmail() {
		return remetenteEmail;
	}
	public void setRemetenteEmail(String remetenteEmail) {
		this.remetenteEmail = remetenteEmail;
	}
	public String getDestinatarioNome() {
		return destinatarioNome;
	}
	public void setDestinatarioNome(String destinatarioNome) {
		this.destinatarioNome = destinatarioNome;
	}
	public String getDestinatarioEndereco() {
		return destinatarioEndereco;
	}
	public void setDestinatarioEndereco(String destinatarioEndereco) {
		this.destinatarioEndereco = destinatarioEndereco;
	}
	public String getDestinatarioTelefone() {
		return destinatarioTelefone;
	}
	public void setDestinatarioTelefone(String destinatarioTelefone) {
		this.destinatarioTelefone = destinatarioTelefone;
	}
	public String getDestinatarioEmail() {
		return destinatarioEmail;
	}
	public void setDestinatarioEmail(String destinatarioEmail) {
		this.destinatarioEmail = destinatarioEmail;
	}
	
	
	
	
	
	
}
