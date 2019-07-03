package br.com.agencia.rest;


import java.util.List;

import br.com.agencia.tpa.rest.request.EmiteRequest;
import br.com.agencia.tpa.rest.request.PrePostagemRequest;
import br.com.agencia.tpa.rest.request.PrecoPrazoRequest;
import br.com.agencia.tpa.rest.response.EtiquetaResponse;
import br.com.agencia.tpa.rest.response.PrePostagemResponse;
import br.com.agencia.tpa.rest.response.PrecoPrazoResponse;

public interface CorreiosPreAtendimentoApi {

	
	public List<PrecoPrazoResponse> servicosDisponiveis(PrecoPrazoRequest request) throws ApiException;

	public EtiquetaResponse gerarPrePostagem(PrePostagemRequest request, boolean emitiEtiqueta) throws ApiException;

	public byte[] emitirEtiqueta(EmiteRequest request) throws ApiException;
	
	public PrePostagemResponse informacaoPlp(EmiteRequest request) throws ApiException;

	public byte[] emitirAvisoRecebimento(EmiteRequest request) throws ApiException ;

	byte[] emitirDeclaracaoDeConteudo(EmiteRequest request) throws ApiException;

	
	
}

