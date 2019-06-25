package br.com.agencia.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.agencia.tpa.rest.response.CepResponse;
import br.com.agencia.tpa.rest.response.ListaCepResponse;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


public class CorreiosImpl{
	
	private CorreiosApi service;
	

	public CorreiosImpl() {
		HttpLoggingInterceptor i = new HttpLoggingInterceptor();
		i.setLevel(Level.BODY);
		
		OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(i).build();
		
		Retrofit retrofit2 = new Retrofit.Builder()
						  
			    .baseUrl("http://wsmobile.correios.com.br/DNECWService/rest/")
			    .addConverterFactory(JacksonConverterFactory.create())	
			    .client(client)
			    .build();
		
		service = retrofit2.create(CorreiosApi.class);
	}

	
	public List<CepResponse> buscarEndereco(String cep){
		
		Pattern p = Pattern.compile("^\\d{2}\\.?\\d{3}-?\\d{3}$");
		Matcher m = p.matcher(cep);
		boolean isZipCode = m.find();
		
		
		Response<ListaCepResponse> response = null;
		try {
			response = isZipCode ? service.listarPorCep(cep).execute() : service.listarPorEndereco(cep).execute();
		} catch (IOException e) {
			return new ArrayList<CepResponse>();
		}
		
		return response.body().ceps;
		
	}
	
	

}
