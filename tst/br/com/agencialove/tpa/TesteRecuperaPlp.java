package br.com.agencialove.tpa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.agencia.tpa.rest.request.EmiteRequest;
import br.com.agencia.tpa.rest.response.PrePostagemResponse;
import br.com.agencialove.tpa.workflow.Session;

public class TesteRecuperaPlp {

	public static void main(String[] args) throws JsonProcessingException {

		
		EmiteRequest request = new EmiteRequest();
		request.setNumeroPlp("27933532");
		
		PrePostagemResponse response = Session.getCorreiosPreAtentimentoWebService().informacaoPlp(request);
		
		System.out.println(new ObjectMapper().writeValueAsString(response));
		
		

	}

}
