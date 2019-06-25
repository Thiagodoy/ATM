package br.com.agencia.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.agencia.tpa.rest.request.PrecoPrazoRequest;
import br.com.agencia.tpa.rest.response.ListaPrecoPrazoResponse;
import br.com.agencia.tpa.rest.response.PrecoPrazoResponse;
import br.com.agencialove.tpa.dao.AgenciaDao;
import br.com.agencialove.tpa.model.Agencia;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class CorreiosPreAtendimentoImpl {
	
	private CorreiosPreAtendimentoApi service;
	private Agencia agencia;
	
	public CorreiosPreAtendimentoImpl() {
		HttpLoggingInterceptor i = new HttpLoggingInterceptor();
		i.setLevel(Level.BODY);
		
		OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(i).build();
		
		Retrofit retrofit2 = new Retrofit.Builder()
						  
			    .baseUrl("http://wsmobile.correios.com.br/DNECWService/rest/")
			    .addConverterFactory(JacksonConverterFactory.create())	
			    .client(client)
			    .build();
		
		service = retrofit2.create(CorreiosPreAtendimentoApi.class);
		
		this.agencia = AgenciaDao.getAgencia();
		
		
		
		
	}
	
	
	public List<PrecoPrazoResponse> servicosDisponiveis(PrecoPrazoRequest request) {		
		
		try {
			Response<ListaPrecoPrazoResponse> response = service.precoPrazoCartao(request, agencia.getCartaoPostagem()).execute();
			return response.body().listaPrecoPrazo;
		} catch (IOException e) {
			e.printStackTrace();
			return new ArrayList<PrecoPrazoResponse>();
		}
	}

}
