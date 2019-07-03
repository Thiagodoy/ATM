package br.com.agencialove.tpa;

import java.util.List;

import br.com.agencia.rest.CorreiosPreAtendimentoApi;
import br.com.agencia.tpa.rest.request.PrecoPrazoRequest;
import br.com.agencialove.tpa.model.rest.ServicesRequest;
import br.com.agencialove.tpa.model.rest.ServicesResponse;
import br.com.agencialove.tpa.webservices.IWebService;
import br.com.agencialove.tpa.workflow.Session;

public class TestWebService {

	public static void main(final String[] args) {
		final IWebService ws = Session.getWebService();


		Session.setAllMocks();
		
		CorreiosPreAtendimentoApi api = Session.getCorreiosPreAtentimentoWebService();
		
		PrecoPrazoRequest request = new PrecoPrazoRequest();
		request.setCepDestino("14806005");
		request.setCepOrigem("14806005");
		request.setPeso("10");
		request.setFormato("1");
		request.setAltura("9");
		request.setLargura("13.5");
		request.setDiametro("0");
		request.setMaoPropria("S");
		request.setValorDeclarado("0");
		request.setAvisoRecebimento("S");
		request.setComprimento("18");
		
		try {
			api.servicosDisponiveis(new PrecoPrazoRequest());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
