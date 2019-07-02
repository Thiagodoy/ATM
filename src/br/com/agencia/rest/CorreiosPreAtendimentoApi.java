package br.com.agencia.rest;


import java.util.List;

import br.com.agencia.tpa.rest.request.EmiteRequest;
import br.com.agencia.tpa.rest.request.PrePostagemRequest;
import br.com.agencia.tpa.rest.request.PrecoPrazoRequest;
import br.com.agencia.tpa.rest.response.EtiquetaResponse;
import br.com.agencia.tpa.rest.response.PrePostagemResponse;
import br.com.agencia.tpa.rest.response.PrecoPrazoResponse;

public interface CorreiosPreAtendimentoApi {

	
	public List<PrecoPrazoResponse> servicosDisponiveis(PrecoPrazoRequest request);

	public EtiquetaResponse gerarPrePostagem(PrePostagemRequest request, boolean emitiEtiqueta);

	public byte[] emitirEtiqueta(EmiteRequest request);
	
	public PrePostagemResponse informacaoPlp(EmiteRequest request);

	public byte[] emitirAvisoRecebimento(EmiteRequest request) ;

	byte[] emitirDeclaracaoDeConteudo(EmiteRequest request);

	
	
}

