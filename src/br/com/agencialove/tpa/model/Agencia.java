package br.com.agencialove.tpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Agencia implements Serializable {
	
	private static final long serialVersionUID = 213452L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "CODIGO")
	private String codigo;
	
	@Column(name = "NOME_AGENCIA")
	private String nomeAgencia;
	
	@Column(name = "CNPJ_AGENCIA")
	private String cnpjAgencia;
	
	@Column(name = "MCU_UNIDADE")
	private String mcuUnidade;
	
	@Column(name = "NOME_FORNECEDOR")
	private String nomeFornecedor;
	
	@Column(name = "MODELO_ATM")
	private String modeloAtm;
	
	@Column(name = "CARTAO_POSTAGEM")
	private String cartaoPostagem;
	
	@Column(name = "NUMERO_PLP")
	private String numeroPlp;
	
	@Column(name = "REGISTRO_OBJETO")
	private String registroObjeto;
	
	@Column(name = "AUTORIZACAO_PAGAMENTO")
	private String autorizacaoPagamento;
	
	@Column(name = "DATA")
	private String data;
	
	@Column(name = "HORA")
	private String hora;
	
	@Column(name = "CREDITO_DEBITO")
	private String creditoDebito;
	
	@Column(name = "VALOR_VENDA")
	private String valorVenda;
	
	public Agencia() {
	}
	
	public Agencia(final String dCodigo, final String dNomeAgencia, final String dCnpjAgencia, final String dMcuUnidade, final String dNomeFornecedor, final String dModeloAtm, final String dCartaoPostagem, final String dNumeroPlp, final String dRegistroObjeto, final String dAutorizacaoPagamento, final String dData, final String dHora, final String dCreditoDebito, final String dValorVenda ) {
		this.codigo = dCodigo;
		this.nomeAgencia = dNomeAgencia;
		this.cnpjAgencia = dCnpjAgencia;
		this.mcuUnidade = dMcuUnidade;
		this.nomeFornecedor = dNomeFornecedor;
		this.modeloAtm = dModeloAtm;
		this.cartaoPostagem = dCartaoPostagem;
		this.numeroPlp = dNumeroPlp;
		this.registroObjeto = dRegistroObjeto;
		this.autorizacaoPagamento = dAutorizacaoPagamento;
		this.data = dData;
		this.hora = dHora;
		this.creditoDebito = dCreditoDebito;
		this.valorVenda = dValorVenda;
		
	}
	public String getNomeAgencia() {
		return this.nomeAgencia;
	}
	public void setNomeAgencia(final String nomeAgencia) {
		this.nomeAgencia = nomeAgencia;
	}
	public String getCodigo() {
		return this.codigo;
	}
	public void setCodigo(final String codigo) {
		this.codigo = codigo;
	}
	public String getCnpjAgencia() {
		return this.cnpjAgencia;
	}
	public void setCnpjAgencia(final String cnpjAgencia) {
		this.cnpjAgencia = cnpjAgencia;
	}
	public String getMcuUnidade() {
		return this.mcuUnidade;
	}
	public void setMcuUnidade(final String mcuUnidade) {
		this.mcuUnidade = mcuUnidade;
	}
	public String getNomeFornecedor() {
		return this.nomeFornecedor;
	}
	public void setNomeFornecedor(final String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}
	public String getModeloAtm() {
		return this.modeloAtm;
	}
	public void setModeloAtm(final String modeloAtm) {
		this.modeloAtm = modeloAtm;
	}
	public String getCartaoPostagem() {
		return this.cartaoPostagem;
	}
	public void setCartaoPostagem(final String cartaoPostagem) {
		this.cartaoPostagem = cartaoPostagem;
	}
	public String getNumeroPlp() {
		return this.numeroPlp;
	}
	public void setNumeroPlp(final String numeroPlp) {
		this.numeroPlp = numeroPlp;
	}
	public String getRegistroObjeto() {
		return this.registroObjeto;
	}
	public void setRegistroObjejeto(final String registroObjeto) {
		this.registroObjeto = registroObjeto;
	}
	public String getAutorizacaoPagamento() {
		return this.autorizacaoPagamento;
	}
	public void setAutorizacaoPagamento(final String autorizacaoPagamento) {
		this.autorizacaoPagamento = autorizacaoPagamento;
	}
	public String getData() {
		return this.data;
	}
	public void setData(final String data) {
		this.data = data;
	}
	public String getHora() {
		return this.hora;
	}
	public void setHora(final String hora) {
		this.hora = hora;
	}
	public String getCreditoDebito() {
		return this.creditoDebito;
	}
	public void setCreditoDebito(final String creditoDebito) {
		this.creditoDebito = creditoDebito;
	}
	public String getValorVenda() {
		return this.valorVenda;
	}
	public void setValorVenda(final String valorVenda) {
		this.valorVenda = valorVenda;
	}
}


