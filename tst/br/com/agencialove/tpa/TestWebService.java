package br.com.agencialove.tpa;

import java.util.List;

import br.com.agencialove.tpa.model.rest.ServicesRequest;
import br.com.agencialove.tpa.model.rest.ServicesResponse;
import br.com.agencialove.tpa.webservices.IWebService;
import br.com.agencialove.tpa.workflow.Session;

public class TestWebService {

	public static void main(final String[] args) {
		final IWebService ws = Session.getWebService();


		final ServicesRequest req = new ServicesRequest();
		req.setCepOrigem("01212001");
		req.setCepDestino("71931180");
		req.setPeso("10");
		req.setFormato("1");
		req.setComprimento("18");
		req.setAltura("10");
		req.setLargura("20");
		req.setDiametro("0");
		req.setMaoPropria("N");
		req.setValorDeclarado("0");
		req.setAvisoRecebimento("N");
		final List<ServicesResponse> resp = ws.getAvailableServices(req);
		for(final ServicesResponse r : resp)
			System.out.println(r.toString());

	}

}
