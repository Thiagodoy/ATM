package br.com.agencialove.tpa.webservices;

import java.util.List;

import br.com.agencia.tpa.rest.request.EmiteRequest;
import br.com.agencia.tpa.rest.request.PrePostagemRequest;
import br.com.agencia.tpa.rest.request.PrecoPrazoRequest;
import br.com.agencia.tpa.rest.response.PrePostagemResponse;
import br.com.agencia.tpa.rest.response.PrecoPrazoResponse;

public interface CorreiosPreAtendimentoApi {

	
	public List<PrecoPrazoResponse> servicosDisponiveis(PrecoPrazoRequest request);

	public PrePostagemResponse gerarPrePostagem(PrePostagemRequest request, boolean emitiEtiqueta);

	public byte[] emitirEtiqueta(EmiteRequest request);

	public byte[] emitirAvisoRecebimento(EmiteRequest request) ;

	byte[] emitirDeclaracaoDeConteudo(EmiteRequest request);

	
	
}
