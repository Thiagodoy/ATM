package br.com.agencia.tpa.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.agencia.tpa.rest.request.DimensaoObjetoRequest;

public class ObjetoPostalResponse {

	@JsonProperty(value = "peso")
	private String peso;

	@JsonProperty(value = "destinatario")
	private DestinatarioResponse destinatario;

	@JsonProperty(value = "nacional")
	private NacionalResponse nacional;

	@JsonProperty(value = "pesoEmKG")
	private String pesoEmKg;

	@JsonProperty(value = "numero_etiqueta")
	private String numeroEtiqueta;

	@JsonProperty(value = "codigo_objeto_cliente")
	private String codigoObjetoCliente;

	@JsonProperty(value = "codigo_servico_postagem")
	private String codigoServicoPostagem;

	@JsonProperty(value = "dimensao_objeto")
	private DimensaoObjetoRequest dimensao;

	@JsonProperty(value = "data_postagem_sara")
	private String dataPostagemSara;

	@JsonProperty(value = "status_processamento")
	private String statusProcessamento;

	@JsonProperty(value = "numero_comprovante_postagem")
	private String numeroComprovantePostagem;

	@JsonProperty(value = "valor_cobrado")
	private String valorCobrado;

	@JsonProperty(value = "url_rotulo")
	private String url_rotulo;

	@JsonProperty(value = "url_disc_conteudo")
	private String url_disc_conteudo;

	@JsonProperty(value = "url_ar")
	private String url_ar;

	@JsonProperty(value = "rt1")
	private String rt1;
	@JsonProperty(value = "rt2")
	private String rt2;

	@JsonProperty(value = "servico_adicional")
	private ServicoAdicionalResponse serviceAdicional;

	public String getRt1() {
		return rt1;
	}

	public void setRt1(String rt1) {
		this.rt1 = rt1;
	}

	public String getRt2() {
		return rt2;
	}

	public void setRt2(String rt2) {
		this.rt2 = rt2;
	}

	public ServicoAdicionalResponse getServiceAdicional() {
		return serviceAdicional;
	}

	public void setServiceAdicional(ServicoAdicionalResponse serviceAdicional) {
		this.serviceAdicional = serviceAdicional;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public DestinatarioResponse getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(DestinatarioResponse destinatario) {
		this.destinatario = destinatario;
	}

	public NacionalResponse getNacional() {
		return nacional;
	}

	public void setNacional(NacionalResponse nacional) {
		this.nacional = nacional;
	}

	public String getPesoEmKg() {
		return pesoEmKg;
	}

	public void setPesoEmKg(String pesoEmKg) {
		this.pesoEmKg = pesoEmKg;
	}

	public String getNumeroEtiqueta() {
		return numeroEtiqueta;
	}

	public void setNumeroEtiqueta(String numeroEtiqueta) {
		this.numeroEtiqueta = numeroEtiqueta;
	}

	public String getCodigoObjetoCliente() {
		return codigoObjetoCliente;
	}

	public void setCodigoObjetoCliente(String codigoObjetoCliente) {
		this.codigoObjetoCliente = codigoObjetoCliente;
	}

	public String getCodigoServicoPostagem() {
		return codigoServicoPostagem;
	}

	public void setCodigoServicoPostagem(String codigoServicoPostagem) {
		this.codigoServicoPostagem = codigoServicoPostagem;
	}

	public DimensaoObjetoRequest getDimensao() {
		return dimensao;
	}

	public void setDimensao(DimensaoObjetoRequest dimensao) {
		this.dimensao = dimensao;
	}

	public String getDataPostagemSara() {
		return dataPostagemSara;
	}

	public void setDataPostagemSara(String dataPostagemSara) {
		this.dataPostagemSara = dataPostagemSara;
	}

	public String getStatusProcessamento() {
		return statusProcessamento;
	}

	public void setStatusProcessamento(String statusProcessamento) {
		this.statusProcessamento = statusProcessamento;
	}

	public String getNumeroComprovantePostagem() {
		return numeroComprovantePostagem;
	}

	public void setNumeroComprovantePostagem(String numeroComprovantePostagem) {
		this.numeroComprovantePostagem = numeroComprovantePostagem;
	}

	public String getValorCobrado() {
		return valorCobrado;
	}

	public void setValorCobrado(String valorCobrado) {
		this.valorCobrado = valorCobrado;
	}

	public String getUrl_rotulo() {
		return url_rotulo;
	}

	public void setUrl_rotulo(String url_rotulo) {
		this.url_rotulo = url_rotulo;
	}

	public String getUrl_disc_conteudo() {
		return url_disc_conteudo;
	}

	public void setUrl_disc_conteudo(String url_disc_conteudo) {
		this.url_disc_conteudo = url_disc_conteudo;
	}

	public String getUrl_ar() {
		return url_ar;
	}

	public void setUrl_ar(String url_ar) {
		this.url_ar = url_ar;
	}

}
