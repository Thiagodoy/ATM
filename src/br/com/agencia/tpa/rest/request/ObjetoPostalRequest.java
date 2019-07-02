package br.com.agencia.tpa.rest.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ObjetoPostalRequest {	
	
	

	@JsonProperty(value = "codigo_objeto_cliente")
	public String codigoObjetoCliente;
	
	@JsonProperty(value = "codigo_servico_postagem")
	public String codigoServicoPostagem;
	
	@JsonProperty(value = "data_postagem_sara")
	public String dataPostagemSara;
	

	@JsonProperty(value = "destinatario")
	public DestinatarioRequest destinatario;
	
	@JsonProperty(value = "dimensao_objeto")
	public DimensaoObjetoRequest dimensaoObjetoRequest;
	
	@JsonProperty(value = "nacional")
	public NacionalRequest nacionalRequest;
	
	@JsonProperty(value = "numero_comprovante_postagem")
	public String numeroComprovantePostagem;
	
	@JsonProperty(value = "numero_etiqueta")
	public String numeroEtiqueta;
	
	@JsonProperty(value = "peso", required = true)
	public Double peso;
	
	@JsonProperty(value = "rt1", required = false)
	public String rt1;
	
	@JsonProperty(value = "rt2", required = false)
	public String rt2;	
	
	@JsonProperty(value = "servico_adicional")
	public ServicoAdicionalRequest servico;
	
	
	
	
	

	public ObjetoPostalRequest() {
		this.codigoObjetoCliente = "";
		this.dataPostagemSara = "";
		this.numeroComprovantePostagem = "";
		this.numeroEtiqueta = "XX999999999BB";
		
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

	public String getDataPostagemSara() {
		return dataPostagemSara;
	}

	public void setDataPostagemSara(String dataPostagemSara) {
		this.dataPostagemSara = dataPostagemSara;
	}

	public DestinatarioRequest getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(DestinatarioRequest destinatario) {
		this.destinatario = destinatario;
	}

	public DimensaoObjetoRequest getDimensaoObjetoRequest() {
		return dimensaoObjetoRequest;
	}

	public void setDimensaoObjetoRequest(DimensaoObjetoRequest dimensaoObjetoRequest) {
		this.dimensaoObjetoRequest = dimensaoObjetoRequest;
	}

	public NacionalRequest getNacionalRequest() {
		return nacionalRequest;
	}

	public void setNacionalRequest(NacionalRequest nacionalRequest) {
		this.nacionalRequest = nacionalRequest;
	}

	public String getNumeroComprovantePostagem() {
		return numeroComprovantePostagem;
	}

	public void setNumeroComprovantePostagem(String numeroComprovantePostagem) {
		this.numeroComprovantePostagem = numeroComprovantePostagem;
	}

	public String getNumeroEtiqueta() {
		return numeroEtiqueta;
	}

	public void setNumeroEtiqueta(String numeroEtiqueta) {
		this.numeroEtiqueta = numeroEtiqueta;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

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

	public ServicoAdicionalRequest getServico() {
		return servico;
	}

	public void setServico(ServicoAdicionalRequest servicos) {
		this.servico = servicos;
	}

}
