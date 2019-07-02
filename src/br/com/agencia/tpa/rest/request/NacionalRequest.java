package br.com.agencia.tpa.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.agencia.tpa.rest.response.NacionalResponse;
import br.com.agencialove.tpa.utils.Utils;

public class NacionalRequest {

	@JsonProperty(value = "bairro_destinatario", required = true)
	public String bairro;

	@JsonProperty(value = "cidade_destinatario", required = true)
	public String cidade;

	@JsonProperty(value = "uf_destinatario", required = true)
	public String estado;

	@JsonProperty(value = "cep_destinatario", required = true)
	public String cep;

	@JsonProperty(value = "codigo_usuario_postal", required = false)
	public String codigoUsuarioPostal;

	@JsonProperty(value = "centro_custo_cliente", required = false)
	public String centroCustoCliente;

	@JsonProperty(value = "numero_nota_fiscal", required = false)
	public String numeroNotaFiscal;

	@JsonProperty(value = "serie_nota_fiscal", required = false)
	public String serieNotaFiscal;

	@JsonProperty(value = "valor_nota_fiscal", required = false)
	public Double valorNotaFiscal;

	@JsonProperty(value = "natureza_nota_fiscal", required = false)
	public String naturezaNotaFiscal;

	@JsonProperty(value = "descricao_objeto", required = false)
	public String descricaoObjeto;

	@JsonProperty(value = "valor_a_cobrar", required = false)
	public Double valorACobrar;

	public NacionalRequest() {

		this.bairro = "";
		this.cidade = "";
		this.estado = "";
		this.cep = "";
		this.codigoUsuarioPostal = "";
		this.centroCustoCliente = "";
		this.numeroNotaFiscal = "";
		this.serieNotaFiscal = "";
		this.valorNotaFiscal = 0d;
		this.naturezaNotaFiscal = "";
		this.descricaoObjeto = "";
		this.valorACobrar = 0d;
	}
	
	public NacionalRequest(NacionalResponse response) {

		this.bairro = response.getBairro_destinatario();
		this.cidade = response.getCidade_destinatario();
		this.estado = response.getUf_destinatario();
		this.cep = response.getCep_destinatario();
		this.codigoUsuarioPostal = response.getCodigo_usuario_postal();
		this.centroCustoCliente = response.getCentro_custo_cliente();
		this.numeroNotaFiscal = response.getNumero_nota_fiscal();
		this.serieNotaFiscal = response.getSerie_nota_fiscal();
		this.valorNotaFiscal = Utils.parse(response.getValor_nota_fiscal());
		this.naturezaNotaFiscal = response.getNatureza_nota_fiscal();
		this.descricaoObjeto = response.getDescricao_objeto();
		this.valorACobrar = Utils.parse(response.getValor_a_cobrar());
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCodigoUsuarioPostal() {
		return codigoUsuarioPostal;
	}

	public void setCodigoUsuarioPostal(String codigoUsuarioPostal) {
		this.codigoUsuarioPostal = codigoUsuarioPostal;
	}

	public String getCentroCustoCliente() {
		return centroCustoCliente;
	}

	public void setCentroCustoCliente(String centroCustoCliente) {
		this.centroCustoCliente = centroCustoCliente;
	}

	public String getNumeroNotaFiscal() {
		return numeroNotaFiscal;
	}

	public void setNumeroNotaFiscal(String numeroNotaFiscal) {
		this.numeroNotaFiscal = numeroNotaFiscal;
	}

	public String getSerieNotaFiscal() {
		return serieNotaFiscal;
	}

	public void setSerieNotaFiscal(String serieNotaFiscal) {
		this.serieNotaFiscal = serieNotaFiscal;
	}

	public Double getValorNotaFiscal() {
		return valorNotaFiscal;
	}

	public void setValorNotaFiscal(Double valorNotaFiscal) {
		this.valorNotaFiscal = valorNotaFiscal;
	}

	public String getNaturezaNotaFiscal() {
		return naturezaNotaFiscal;
	}

	public void setNaturezaNotaFiscal(String naturezaNotaFiscal) {
		this.naturezaNotaFiscal = naturezaNotaFiscal;
	}

	public String getDescricaoObjeto() {
		return descricaoObjeto;
	}

	public void setDescricaoObjeto(String descricaoObjeto) {
		this.descricaoObjeto = descricaoObjeto;
	}

	public Double getValorACobrar() {
		return valorACobrar;
	}

	public void setValorACobrar(Double valorACobrar) {
		this.valorACobrar = valorACobrar;
	}

}
