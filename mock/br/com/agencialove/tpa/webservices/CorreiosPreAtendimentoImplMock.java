package br.com.agencialove.tpa.webservices;

import java.util.Arrays;
import java.util.List;

import br.com.agencia.rest.CorreiosPreAtendimentoApi;
import br.com.agencia.tpa.rest.request.EmiteRequest;
import br.com.agencia.tpa.rest.request.PrePostagemRequest;
import br.com.agencia.tpa.rest.request.PrecoPrazoRequest;
import br.com.agencia.tpa.rest.response.EtiquetaResponse;
import br.com.agencia.tpa.rest.response.PrePostagemResponse;
import br.com.agencia.tpa.rest.response.PrecoPrazoResponse;

public class CorreiosPreAtendimentoImplMock implements CorreiosPreAtendimentoApi {

	@Override
	public List<PrecoPrazoResponse> servicosDisponiveis(PrecoPrazoRequest request) {

		PrecoPrazoResponse prazoResponse = new PrecoPrazoResponse();
		prazoResponse.setCodigoServico("04022");
		prazoResponse.setDescricaoServico("SEDEX POC ATENDIMENTO");	
		prazoResponse.setValor("59,00");
		prazoResponse.setPrazoEntrega("9");
		prazoResponse.setValorMaoPropria("6.80");
		prazoResponse.setValorAvisoRecebimento("0,00");
		prazoResponse.setValorValorDeclarado("0,00");
		prazoResponse.setEntregaDomiciliar("S");
		prazoResponse.setEntregaDomiciliar("S");
		prazoResponse.setEntregaSabado("S");
		prazoResponse.setObservacao("O CEP de destino está sujeito a condições especiais de entrega  pela  ECT e será realizada com o acréscimo de até 7 (sete) dias úteis ao prazo regular.");
		prazoResponse.setValorSemAdicionais("52,20");	
		
		
		PrecoPrazoResponse prazoResponse1 = new PrecoPrazoResponse();
		prazoResponse1.setCodigoServico("04030");
		prazoResponse1.setDescricaoServico("PAC POC AUTOATENDIMENTO");
		prazoResponse1.setValor("40,00");
		prazoResponse1.setPrazoEntrega("9");
		prazoResponse1.setValorMaoPropria("6.80");
		prazoResponse1.setValorAvisoRecebimento("0,00");
		prazoResponse1.setValorValorDeclarado("0,00");
		prazoResponse1.setEntregaDomiciliar("S");
		prazoResponse1.setEntregaDomiciliar("S");
		prazoResponse1.setEntregaSabado("S");
		prazoResponse1.setObservacao("O CEP de destino está sujeito a condições especiais de entrega  pela  ECT e será realizada com o acréscimo de até 7 (sete) dias úteis ao prazo regular.");
		prazoResponse1.setValorSemAdicionais("52,20");
		
		

		return Arrays.asList(prazoResponse, prazoResponse1);
	}

	@Override
	public EtiquetaResponse gerarPrePostagem(PrePostagemRequest request, boolean emitiEtiqueta) {
		EtiquetaResponse response = new EtiquetaResponse();
		response.setNumeroEtiqueta("EC384261522BR");
		response.setNumeroPlp("2523557");
		return response;
	}

	@Override
	public byte[] emitirEtiqueta(EmiteRequest request) {
		return "emitirEtiqueta".getBytes();
	}

	@Override
	public byte[] emitirAvisoRecebimento(EmiteRequest request) {
		return "emitirAvisoRecebimento".getBytes();
	}

	@Override
	public byte[] emitirDeclaracaoDeConteudo(EmiteRequest request) {
		return "emitirDeclaracaoDeConteudo".getBytes();
	}

	@Override
	public PrePostagemResponse informacaoPlp(EmiteRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
