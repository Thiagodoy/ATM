package br.com.agencialove.tpa.model.rest;

import br.com.agencialove.tpa.model.AdditionalServices;
import br.com.agencialove.tpa.model.Address;

public class PrePostGetResponse {
	private String formaPagamento;
	//objeto_postal
	private String codigoObjetoCliente;
	private String codigoServicoPostagem;
	private String dataPostagemSara;
	//destinatario
	private String nomeDestinatario;
	private String telefoneDestinatario;
	private String celularDestinatario;
	private String emailDestinatario;
	private String logradouroDestinatario;
	private String complementoDestinatario;
	private String numeroEndDestinatario;
	//dimensao_objeto
	private String tipoObjeto;
	private String dimensaoAltura;
	private String dimensaoLargura;
	private String dimensaoComprimento;
	private String dimensaoDiametro;
	//nacional
	private String bairroDestinatario;
	private String cidadeDestinatario;
	private String ufDestinatario;
	private String cepDestinatario;
	private String codigoUsuarioPostal;
	private String centroCustoCliente;
	private String numeroNotaFiscal;
	private String serieNotaFiscal;
	private String valorNotaFiscal;
	private String naturezaNotaFiscal;
	private String descricaoObjeto;
	private String valorACobrar;
	private String numeroComprovantePostagem;
	private String numeroEtiqueta;
	private String peso;
	private String rt1;
	private String rt2;
	//servico_adicional
	private String codigoServicoAdicional;
	private String valorDeclarado;
	private String statusProcessamento;
	private String valorCobrado;
	//plp
	private String idPlp;
	private String valorGlobal;
	private String mcuUnidadePostagem;
	private String nomeUnidadePostagem;
	private String cartaoPostagem;
	//remetente
	private String numeroContrato;
	private String numeroDiretoria;
	private String codigoAdministrativo;
	private String nomeRemetente;
	private String logradouroRemetente;
	private String numeroRemetente;
	private String complementoRemetente;
	private String bairroRemetente;
	private String cepRemetente;
	private String cidadeRemetente;
	private String ufRemetente;
	private String telefoneRemetente;
	private String emailRemetente;
	private String faxRemetente;
	private String tipoArquivo;
	private String versaoArquivo;

	public PrePostGetResponse() {
		// TODO Auto-generated constructor stub
	}

	public PrePostGetResponse(final Address receiverAddress, final Address senderAddress, final AdditionalServices additionalServices) {
		// TODO Auto-generated constructor stub
	}

	public String toJSON() {
		final String ret = "{" +
				"\"forma_pagamento\":\"" + this.formaPagamento + "\","
				+"\"objeto_postal\":[{"
				+"\"codigo_objeto_cliente\":\"\","
				+"\"codigo_servico_postagem\":\""+ this.codigoServicoPostagem +"\","
				+"\"cubagem\":\"0,0000\"," //TODO review this field, it is not present in the documentation
				+"\"data_postagem_sara\":\"\","
				+"\"destinatario\":{"
				+"\"celular_destinatario\":\"" + this.celularDestinatario + "\","
				+"\"complemento_destinatario\":\"" + this.complementoDestinatario + "\","
				+"\"cpf_cnpj_destinatario\":\"\","
				+"\"email_destinatario\":\"" + this.emailDestinatario + "\","
				+"\"logradouro_destinatario\":\"" + this.logradouroDestinatario + "\","
				+"\"nome_destinatario\":\"" + this.nomeDestinatario + "\","
				+"\"numero_end_destinatario\":\"" + this.numeroEndDestinatario + "\","
				+"\"telefone_destinatario\":\"" + this.telefoneDestinatario + "\","
				+"\"tipo_logradouro_destinatario\":\"2\"},"//TODO review this field, it is not present in the documentation
				+"\"dimensao_objeto\":{"
				+"\"dimensao_altura\":\"" + this.dimensaoAltura + "\","
				+"\"dimensao_comprimento\":\"" + this.dimensaoComprimento + "\","
				+"\"dimensao_diametro\":\"" + this.dimensaoDiametro + "\","
				+"\"dimensao_largura\":\"" + this.dimensaoLargura + "\","
				+"\"tipo_objeto\":\"" + this.tipoObjeto + "\"},"
				+"\"nacional\":{"
				+"\"bairro_destinatario\":\"" + this. bairroDestinatario + "\","
				+"\"centro_custo_cliente\":\"" + this.centroCustoCliente + "\","
				+"\"cep_destinatario\":\"" + this.cepDestinatario + "\","
				+"\"cidade_destinatario\":\"" + this.cidadeDestinatario + "\","
				+"\"codigo_usuario_postal\":\"" + this.codigoUsuarioPostal + "\","
				+"\"descricao_objeto\":\"" + this.descricaoObjeto + "\","
				+"\"natureza_nota_fiscal\":\"" + this.naturezaNotaFiscal + "\","
				+"\"numero_nota_fiscal\":\"" + this.numeroNotaFiscal + "\","
				+"\"serie_nota_fiscal\":\"" + this.serieNotaFiscal + "\","
				+"\"uf_destinatario\":\"" + this.ufDestinatario + "\","
				+"\"valor_a_cobrar\":\"" + this.valorACobrar + "\","
				+"\"valor_nota_fiscal\":\"" + this.valorNotaFiscal + "\"},"
				+"\"numero_comprovante_postagem\":\"" + this.numeroComprovantePostagem + "\","
				+"\"numero_etiqueta\":\"" + this.numeroEtiqueta + "\","
				+"\"peso\":\"" + this.peso + "\","
				+"\"rt1\":\"" + this.rt1 + "\","
				+"\"rt2\":\"" + this.rt2 + "\","
				+"\"servico_adicional\":{"
				+"\"codigo_servico_adicional\":[\"" + this.codigoServicoAdicional + "\"]},"
				+"\"status_processamento\":\"" + this.statusProcessamento + "\","
				+"\"valor_cobrado\":\"\"}],"
				+"\"plp\":{"
				+"\"cartao_postagem\":\"" + this.cartaoPostagem + "\","
				+"\"id_plp\":\"" + this.idPlp + "\","
				+"\"mcu_unidade_postagem\":\"" + this.mcuUnidadePostagem + "\","
				+"\"nome_unidade_postagem\":\"" + this.nomeUnidadePostagem + "\","
				+"\"valor_global\":\"" + this.valorGlobal + "\"},"
				+"\"remetente\":{"
				+"\"bairro_remetente\":\"" + this.bairroRemetente + "\","
				+"\"cep_remetente\":\"" + this.cepRemetente + "\","
				+"\"cidade_remetente\":\"" + this.cidadeRemetente + "\","
				+"\"codigo_administrativo\":\"" + this.codigoAdministrativo + "\","
				+"\"complemento_remetente\":\"" + this.complementoRemetente + "\","
				+"\"cpf_cnpj_remetente\":\"\","
				+"\"email_remetente\":\"" + this.emailRemetente + "\","
				+"\"fax_remetente\":\"\","
				+"\"logradouro_remetente\":\"" + this.logradouroRemetente + "\","
				+"\"nome_remetente\":\"" + this.nomeRemetente + "\","
				+"\"numero_contrato\":\"" + this.numeroContrato + "\","
				+"\"numero_diretoria\":\"" + this.numeroDiretoria + "\","
				+"\"numero_remetente\":\"" + this.numeroRemetente + "\","
				+"\"telefone_remetente\":\"" + this.telefoneRemetente + "\","
				+"\"tipo_logradouro_remetente\":\"\","
				+"\"uf_remetente\":\"" + this.ufRemetente + "\"},"
				+"\"tipo_arquivo\":\"Postagem\","
				+"\"versao_arquivo\":\"2.3\"}";

		return ret;
	}

	public String getFormaPagamento() {
		return this.formaPagamento;
	}
	public void setFormaPagamento(final String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public String getCodigoObjetoCliente() {
		return this.codigoObjetoCliente;
	}
	public void setCodigoObjetoCliente(final String codigoObjetoCliente) {
		this.codigoObjetoCliente = codigoObjetoCliente;
	}
	public String getCodigoServicoPostagem() {
		return this.codigoServicoPostagem;
	}
	public void setCodigoServicoPostagem(final String codigoServicoPostagem) {
		this.codigoServicoPostagem = codigoServicoPostagem;
	}
	public String getDataPostagemSara() {
		return this.dataPostagemSara;
	}
	public void setDataPostagemSara(final String dataPostagemSara) {
		this.dataPostagemSara = dataPostagemSara;
	}
	public String getNomeDestinatario() {
		return this.nomeDestinatario;
	}
	public void setNomeDestinatario(final String nomeDestinatario) {
		this.nomeDestinatario = nomeDestinatario;
	}
	public String getTelefoneDestinatario() {
		return this.telefoneDestinatario;
	}
	public void setTelefoneDestinatario(final String telefoneDestinatario) {
		this.telefoneDestinatario = telefoneDestinatario;
	}
	public String getCelularDestinatario() {
		return this.celularDestinatario;
	}
	public void setCelularDestinatario(final String celularDestinatario) {
		this.celularDestinatario = celularDestinatario;
	}
	public String getEmailDestinatario() {
		return this.emailDestinatario;
	}
	public void setEmailDestinatario(final String emailDestinatario) {
		this.emailDestinatario = emailDestinatario;
	}
	public String getLogradouroDestinatario() {
		return this.logradouroDestinatario;
	}
	public void setLogradouroDestinatario(final String logradouroDestinatario) {
		this.logradouroDestinatario = logradouroDestinatario;
	}
	public String getComplementoDestinatario() {
		return this.complementoDestinatario;
	}
	public void setComplementoDestinatario(final String complementoDestinatario) {
		this.complementoDestinatario = complementoDestinatario;
	}
	public String getNumeroEndDestinatario() {
		return this.numeroEndDestinatario;
	}
	public void setNumeroEndDestinatario(final String numeroEndDestinatario) {
		this.numeroEndDestinatario = numeroEndDestinatario;
	}
	public String getTipoObjeto() {
		return this.tipoObjeto;
	}
	public void setTipoObjeto(final String tipoObjeto) {
		this.tipoObjeto = tipoObjeto;
	}
	public String getDimensaoAltura() {
		return this.dimensaoAltura;
	}
	public void setDimensaoAltura(final String dimensaoAltura) {
		this.dimensaoAltura = dimensaoAltura;
	}
	public String getDimensaoLargura() {
		return this.dimensaoLargura;
	}
	public void setDimensaoLargura(final String dimensaoLargura) {
		this.dimensaoLargura = dimensaoLargura;
	}
	public String getDimensaoComprimento() {
		return this.dimensaoComprimento;
	}
	public void setDimensaoComprimento(final String dimensaoComprimento) {
		this.dimensaoComprimento = dimensaoComprimento;
	}
	public String getDimensaoDiametro() {
		return this.dimensaoDiametro;
	}
	public void setDimensaoDiametro(final String dimensaoDiametro) {
		this.dimensaoDiametro = dimensaoDiametro;
	}
	public String getBairroDestinatario() {
		return this.bairroDestinatario;
	}
	public void setBairroDestinatario(final String bairroDestinatario) {
		this.bairroDestinatario = bairroDestinatario;
	}
	public String getCidadeDestinatario() {
		return this.cidadeDestinatario;
	}
	public void setCidadeDestinatario(final String cidadeDestinatario) {
		this.cidadeDestinatario = cidadeDestinatario;
	}
	public String getUfDestinatario() {
		return this.ufDestinatario;
	}
	public void setUfDestinatario(final String ufDestinatario) {
		this.ufDestinatario = ufDestinatario;
	}
	public String getCepDestinatario() {
		return this.cepDestinatario;
	}
	public void setCepDestinatario(final String cepDestinatario) {
		this.cepDestinatario = cepDestinatario;
	}
	public String getCodigoUsuarioPostal() {
		return this.codigoUsuarioPostal;
	}
	public void setCodigoUsuarioPostal(final String codigoUsuarioPostal) {
		this.codigoUsuarioPostal = codigoUsuarioPostal;
	}
	public String getCentroCustoCliente() {
		return this.centroCustoCliente;
	}
	public void setCentroCustoCliente(final String centroCustoCliente) {
		this.centroCustoCliente = centroCustoCliente;
	}
	public String getNumeroNotaFiscal() {
		return this.numeroNotaFiscal;
	}
	public void setNumeroNotaFiscal(final String numeroNotaFiscal) {
		this.numeroNotaFiscal = numeroNotaFiscal;
	}
	public String getSerieNotaFiscal() {
		return this.serieNotaFiscal;
	}
	public void setSerieNotaFiscal(final String serieNotaFiscal) {
		this.serieNotaFiscal = serieNotaFiscal;
	}
	public String getValorNotaFiscal() {
		return this.valorNotaFiscal;
	}
	public void setValorNotaFiscal(final String valorNotaFiscal) {
		this.valorNotaFiscal = valorNotaFiscal;
	}
	public String getNaturezaNotaFiscal() {
		return this.naturezaNotaFiscal;
	}
	public void setNaturezaNotaFiscal(final String naturezaNotaFiscal) {
		this.naturezaNotaFiscal = naturezaNotaFiscal;
	}
	public String getDescricaoObjeto() {
		return this.descricaoObjeto;
	}
	public void setDescricaoObjeto(final String descricaoObjeto) {
		this.descricaoObjeto = descricaoObjeto;
	}
	public String getValorACobrar() {
		return this.valorACobrar;
	}
	public void setValorACobrar(final String valorACobrar) {
		this.valorACobrar = valorACobrar;
	}
	public String getNumeroComprovantePostagem() {
		return this.numeroComprovantePostagem;
	}
	public void setNumeroComprovantePostagem(final String numeroComprovantePostagem) {
		this.numeroComprovantePostagem = numeroComprovantePostagem;
	}
	public String getNumeroEtiqueta() {
		return this.numeroEtiqueta;
	}
	public void setNumeroEtiqueta(final String numeroEtiqueta) {
		this.numeroEtiqueta = numeroEtiqueta;
	}
	public String getPeso() {
		return this.peso;
	}
	public void setPeso(final String peso) {
		this.peso = peso;
	}
	public String getRt1() {
		return this.rt1;
	}
	public void setRt1(final String rt1) {
		this.rt1 = rt1;
	}
	public String getRt2() {
		return this.rt2;
	}
	public void setRt2(final String rt2) {
		this.rt2 = rt2;
	}
	public String getCodigoServicoAdicional() {
		return this.codigoServicoAdicional;
	}
	public void setCodigoServicoAdicional(final String codigoServicoAdicional) {
		this.codigoServicoAdicional = codigoServicoAdicional;
	}
	public String getValorDeclarado() {
		return this.valorDeclarado;
	}
	public void setValorDeclarado(final String valorDeclarado) {
		this.valorDeclarado = valorDeclarado;
	}
	public String getStatusProcessamento() {
		return this.statusProcessamento;
	}
	public void setStatusProcessamento(final String statusProcessamento) {
		this.statusProcessamento = statusProcessamento;
	}
	public String getValorCobrado() {
		return this.valorCobrado;
	}
	public void setValorCobrado(final String valorCobrado) {
		this.valorCobrado = valorCobrado;
	}
	public String getIdPlp() {
		return this.idPlp;
	}
	public void setIdPlp(final String idPlp) {
		this.idPlp = idPlp;
	}
	public String getValorGlobal() {
		return this.valorGlobal;
	}
	public void setValorGlobal(final String valorGlobal) {
		this.valorGlobal = valorGlobal;
	}
	public String getMcuUnidadePostagem() {
		return this.mcuUnidadePostagem;
	}
	public void setMcuUnidadePostagem(final String mcuUnidadePostagem) {
		this.mcuUnidadePostagem = mcuUnidadePostagem;
	}
	public String getNomeUnidadePostagem() {
		return this.nomeUnidadePostagem;
	}
	public void setNomeUnidadePostagem(final String nomeUnidadePostagem) {
		this.nomeUnidadePostagem = nomeUnidadePostagem;
	}
	public String getCartaoPostagem() {
		return this.cartaoPostagem;
	}
	public void setCartaoPostagem(final String cartaoPostagem) {
		this.cartaoPostagem = cartaoPostagem;
	}
	public String getNumeroContrato() {
		return this.numeroContrato;
	}
	public void setNumeroContrato(final String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}
	public String getNumeroDiretoria() {
		return this.numeroDiretoria;
	}
	public void setNumeroDiretoria(final String numeroDiretoria) {
		this.numeroDiretoria = numeroDiretoria;
	}
	public String getCodigoAdministrativo() {
		return this.codigoAdministrativo;
	}
	public void setCodigoAdministrativo(final String codigoAdministrativo) {
		this.codigoAdministrativo = codigoAdministrativo;
	}
	public String getNomeRemetente() {
		return this.nomeRemetente;
	}
	public void setNomeRemetente(final String nomeRemetente) {
		this.nomeRemetente = nomeRemetente;
	}
	public String getLogradouroRemetente() {
		return this.logradouroRemetente;
	}
	public void setLogradouroRemetente(final String logradouroRemetente) {
		this.logradouroRemetente = logradouroRemetente;
	}
	public String getNumeroRemetente() {
		return this.numeroRemetente;
	}
	public void setNumeroRemetente(final String numeroRemetente) {
		this.numeroRemetente = numeroRemetente;
	}
	public String getComplementoRemetente() {
		return this.complementoRemetente;
	}
	public void setComplementoRemetente(final String complementoRemetente) {
		this.complementoRemetente = complementoRemetente;
	}
	public String getBairroRemetente() {
		return this.bairroRemetente;
	}
	public void setBairroRemetente(final String bairroRemetente) {
		this.bairroRemetente = bairroRemetente;
	}
	public String getCepRemetente() {
		return this.cepRemetente;
	}
	public void setCepRemetente(final String cepRemetente) {
		this.cepRemetente = cepRemetente;
	}
	public String getCidadeRemetente() {
		return this.cidadeRemetente;
	}
	public void setCidadeRemetente(final String cidadeRemetente) {
		this.cidadeRemetente = cidadeRemetente;
	}
	public String getUfRemetente() {
		return this.ufRemetente;
	}
	public void setUfRemetente(final String ufRemetente) {
		this.ufRemetente = ufRemetente;
	}
	public String getTelefoneRemetente() {
		return this.telefoneRemetente;
	}
	public void setTelefoneRemetente(final String telefoneRemetente) {
		this.telefoneRemetente = telefoneRemetente;
	}
	public String getFaxRemetente() {
		return this.faxRemetente;
	}
	public void setFaxRemetente(final String faxRemetente) {
		this.faxRemetente = faxRemetente;
	}
	public String getTipoArquivo() {
		return this.tipoArquivo;
	}
	public void setTipoArquivo(final String tipoArquivo) {
		this.tipoArquivo = tipoArquivo;
	}
	public String getVersaoArquivo() {
		return this.versaoArquivo;
	}
	public void setVersaoArquivo(final String versaoArquivo) {
		this.versaoArquivo = versaoArquivo;
	}

	public String getEmailRemetente() {
		return this.emailRemetente;
	}

	public void setEmailRemetente(final String emailRemetente) {
		this.emailRemetente = emailRemetente;
	}

	public void setNplp(String nplp) {
		// TODO Auto-generated method stub
		
	}
}
