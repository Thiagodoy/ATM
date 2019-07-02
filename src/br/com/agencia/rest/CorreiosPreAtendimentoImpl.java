package br.com.agencia.rest;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.agencia.tpa.rest.request.EmiteRequest;
import br.com.agencia.tpa.rest.request.PrePostagemRequest;
import br.com.agencia.tpa.rest.request.PrecoPrazoRequest;
import br.com.agencia.tpa.rest.response.ListaPrecoPrazoResponse;
import br.com.agencia.tpa.rest.response.PrePostagemResponse;
import br.com.agencia.tpa.rest.response.EtiquetaResponse;
import br.com.agencia.tpa.rest.response.PrecoPrazoResponse;
import br.com.agencialove.tpa.dao.AgenciaDao;
import br.com.agencialove.tpa.model.Agencia;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class CorreiosPreAtendimentoImpl implements CorreiosPreAtendimentoApi  {

	private CorreiosPreAtendimentoService service;
	private Agencia agencia;

	public CorreiosPreAtendimentoImpl() {
		HttpLoggingInterceptor i = new HttpLoggingInterceptor();
		i.setLevel(Level.BODY);
		

		OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(i).build();

		Retrofit retrofit2 = new Retrofit.Builder()

				.baseUrl("https://apphom.correios.com.br/preatendimento-rs/v1/atendimento/")
				.addConverterFactory(JacksonConverterFactory.create()).client(client).build();

		service = retrofit2.create(CorreiosPreAtendimentoService.class);

		this.agencia = AgenciaDao.getAgencia();

	}

	public List<PrecoPrazoResponse> servicosDisponiveis(PrecoPrazoRequest request) {

		Response<ListaPrecoPrazoResponse> response = null;

		try {
			System.out.println(new ObjectMapper().writeValueAsString(request));
			response = service.precoPrazoCartao(request, agencia.getCartaoPostagem()).execute();
		} catch (IOException e) {
			System.err.println("erro ->" + response.errorBody().toString());
			e.printStackTrace();
		}
		return response.body().listaPrecoPrazo;

	}

	public EtiquetaResponse gerarPrePostagem(PrePostagemRequest request, boolean emitiEtiqueta) {

		Response<EtiquetaResponse> response = null;
		try {			
			System.out.println(new ObjectMapper().writeValueAsString(request));
			response = service.gerarPrePostagem(request, agencia.getCartaoPostagem(), emitiEtiqueta).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response.body();

	}

	public byte[] emitirEtiqueta(EmiteRequest request) {

		byte[] response = null;
		try {
			System.out.println(new ObjectMapper().writeValueAsString(request));
			response = service.emitiEtiqueta(request, agencia.getCartaoPostagem()).execute().body().bytes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;

	}

	public byte[] emitirAvisoRecebimento(EmiteRequest request) {

		byte[] response = null;
		try {
			System.out.println(new ObjectMapper().writeValueAsString(request));
			response = service.emitiAvisoRecebimento(request, agencia.getCartaoPostagem()).execute().body().bytes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;

	}

	public byte[] emitirDeclaracaoDeConteudo(EmiteRequest request) {

		byte[] response = null;
		try {
			System.out.println(new ObjectMapper().writeValueAsString(request));
			response = service.emitiDeclaracaoConteudo(request, agencia.getCartaoPostagem()).execute().body().bytes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;

	}

	@Override
	public PrePostagemResponse informacaoPlp(EmiteRequest request) {
		

		Response<PrePostagemResponse> response = null;
		try {			
			System.out.println(new ObjectMapper().writeValueAsString(request));
			response = service.informacaoPlp(request).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response.body();
	}

}
