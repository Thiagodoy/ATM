package br.com.agencialove.tpa.webservice;

import java.io.IOException;
import java.util.List;

import javax.print.PrintException;

import org.junit.jupiter.api.Test;

import br.com.agencialove.tpa.hardware.IHardwareService;
import br.com.agencialove.tpa.hardware.IPrinterService;
import br.com.agencialove.tpa.model.AdditionalServices;
import br.com.agencialove.tpa.model.Address;
import br.com.agencialove.tpa.model.PackageMeasures;
import br.com.agencialove.tpa.model.Person;
import br.com.agencialove.tpa.model.ZipType;
import br.com.agencialove.tpa.model.rest.EmiteEtiquetaRequest;
import br.com.agencialove.tpa.model.rest.PrePost;
import br.com.agencialove.tpa.model.rest.PrePostResponse;
import br.com.agencialove.tpa.model.rest.ServicesRequest;
import br.com.agencialove.tpa.model.rest.ServicesResponse;
import br.com.agencialove.tpa.webservices.IWebService;
import br.com.agencialove.tpa.workflow.Session;

class TestRequestBuild {

	private final String expectedBodyContent="{\"forma_pagamento\":\"1\","
			+ "\"objeto_postal\":[{\"codigo_objeto_cliente\":\"\","
			+ "\"codigo_servico_postagem\":\"04014\","
			+ "\"cubagem\":\"0,0000\","
			+ "\"data_postagem_sara\":\"\","
			+ "\"destinatario\":{"
			+ "\"celular_destinatario\":\"75991787692\","
			+ "\"complemento_destinatario\":\"\","
			+ "\"cpf_cnpj_destinatario\":\"\","
			+ "\"email_destinatario\":\"\","
			+ "\"logradouro_destinatario\":\"Rua 21 de Abril\"," /*Erro nesta linha*/
			+ "\"nome_destinatario\":\"sadlkfj\","
			+ "\"numero_end_destinatario\":\"12\","
			// LINHA DUPLICADA + "\"cpf_cnpj_destinatario\":\"\","
			+ "\"telefone_destinatario\":\"99999999999\","
			+ "\"tipo_logradouro_destinatario\":\"2\"},"
			+ "\"dimensao_objeto\":{"
			+ "\"dimensao_altura\":\"18\","
			+ "\"dimensao_comprimento\":\"18\","
			+ "\"dimensao_diametro\":\"0\","
			+ "\"dimensao_largura\":\"13.5\","
			+ "\"tipo_objeto\":\"001\"},"
			+ "\"nacional\":{\"bairro_destinatario\":\"Daniela\","
			+ "\"centro_custo_cliente\":\"0069950016\","
			+ "\"cep_destinatario\":\"88053110\","
			+ "\"cidade_destinatario\":\"Florianopolis\"," /*Erro nesta linha*/
			+ "\"codigo_usuario_postal\":\"\","
			+ "\"descricao_objeto\":\"\","
			+ "\"natureza_nota_fiscal\":\"\","
			+ "\"numero_nota_fiscal\":\"000000000\","
			+ "\"serie_nota_fiscal\":\"\","
			+ "\"uf_destinatario\":\"SP\"," /*Erro nesta linha*/
			//LINHA DUPLICADA + "\"uf_destinatario\":\"SP\","
			+ "\"valor_a_cobrar\":\"000000,00\","
			// LINHA DUPLICADA + "\"valor_nota_fiscal\","
			+ "\"valor_nota_fiscal\":\"\"},"
			+ "\"numero_comprovante_postagem\":\"\","
			+ "\"numero_etiqueta\":\"XX999999999BB\","
			+ "\"peso\":\"15\","
			+ "\"rt1\":\"\","
			+ "\"rt2\":\"\","
			+ "\"servico_adicional\":{\"codigo_servico_adicional\":[\"025\"]},"
			+ "\"status_processamento\":\"0\","
			+ "\"valor_cobrado\":\"\"}],"
			+ "\"plp\":{"
			+ "\"cartao_postagem\":\"0074290380\"," /*Erro nesta linha*/
			+ "\"id_plp\":\"\","
			+ "\"mcu_unidade_postagem\":\"\","
			+ "\"nome_unidade_postagem\":\"\","
			+ "\"valor_global\":\"\"},"
			+ "\"remetente\":{\"bairro_remetente\":\"Daniela\","
			+ "\"cep_remetente\":\"88053110\","
			+ "\"cidade_remetente\":\"Florianopolis\","
			+ "\"codigo_administrativo\":\"17000190\","
			+ "\"complemento_remetente\":\"\","
			+ "\"cpf_cnpj_remetente\":\"97147338049\","
			+ "\"email_remetente\":\"\","
			+ "\"fax_remetente\":\"\","
			+ "\"logradouro_remetente\":\"Avenida A\"," /*Erro nesta linha*/
			+ "\"nome_remetente\":\"alsdfjaslkd\","
			+ "\"numero_contrato\":\"9992157880\","
			+ "\"numero_diretoria\":\"10\","
			+ "\"numero_remetente\":\"12\","
			+ "\"telefone_remetente\":\"99999999999\","
			+ "\"tipo_logradouro_remetente\":\"\","
			+ "\"uf_remetente\":\"SP\"}," /*Erro nesta linha*/
			+ "\"tipo_arquivo\":\"Postagem\","
			+ "\"versao_arquivo\":\"2.3\"}";

	@Test
	void testRequestBuild() throws IOException {
		Address sender = new Address();
		Address receiver = new Address();
		final AdditionalServices additionalServices = new AdditionalServices();
		final PackageMeasures measures = new PackageMeasures();
		final Person senderPerson = new Person("remetente 1","00401183394","zeroela@gmail.com",ZipType.SENDER);
		final Person receiverPerson = new Person("destinatario 1","00401183394","zeroela@gmail.com",ZipType.RECEIVER);
		System.out.println(this.expectedBodyContent);


		additionalServices.setOnwHands(true);
		additionalServices.setDeliveryNotice(true);
		additionalServices.setValueDeclaration(true);
		additionalServices.setContentValue("0");

		measures.setBarcode("");
		measures.setWidth("13.5");
		measures.setHeight("18");
		measures.setDepth("0");
		measures.setType("001");
		measures.setVolume("0,0000");
		measures.setWeight("6");
		measures.setDiameter("0");

		final IWebService ws = Session.getWebService();
		sender = ws.getAddressFromZip("08130020").get(0);
		receiver = ws.getAddressFromZip("07040040").get(0);

		sender.setPerson(senderPerson);
		sender.setNumber("48999999991");
		receiver.setPerson(receiverPerson);
		sender.setNumber("48999999992");

		final ServicesRequest sReq = Session.getWebService().getServiceRequest(sender, receiver, additionalServices, measures);

		final List<ServicesResponse> availableServices =  ws.getAvailableServices(sReq);
		final ServicesResponse serviceResponse = availableServices.get(0);

		final PrePost pReq = ws.getPrePostRequest(sender, receiver, additionalServices, measures, serviceResponse);

		//final String bodyContent = pReq.toJSON();
		//Assertions.assertEquals(bodyContent, this.expectedBodyContent);

		final PrePostResponse pResp = ws.serviceRegisterResponse(pReq);

		//prepara objeto para as requisições ao WebService
		final EmiteEtiquetaRequest eer = new EmiteEtiquetaRequest();
		eer.setNumeroPLP(pResp.getNumeroPLP());
		eer.setNumeroEtiqueta(pResp.getNumeroEtiqueta());

		final IPrinterService ps = Session.getPrinterService();

		//emite etiqueta [IWebService.getPdfBytesEmiteEtiqueta]
		final byte[] lbl = ws.getPdfBytesEmiteEtiqueta(eer);
		//TODO exitir alert e continuar
		try {ps.printLabel(lbl);}catch(final PrintException e) {e.printStackTrace();};

		//imprime aviso de recebimento
		if(additionalServices.isDeliveryNotice()) {
			final byte[] dNtc = ws.getPdfBytesAvisoRecebimento(eer);
			//TODO exitir alert e continuar
			try{ps.printTicket(dNtc);}catch(final PrintException e) {e.printStackTrace();};
		}

		//imprime declaração de conteudo
		if(additionalServices.isValueDeclaration()) {
			final byte[] vd = ws.getPdfBytesDeclaracaoDeConteudo(eer);
			//TODO exitir alert e continuar
			try{ps.printTicket(vd);}catch(final PrintException e) {e.printStackTrace();};
		}

		final IHardwareService hs = Session.getHardwareService();
		hs.openBox(4);
		hs.closeBox(4);
	}

}
