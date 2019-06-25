package br.com.agencia.rest;



import br.com.agencia.tpa.rest.response.ListaCepResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CorreiosApi {

	@GET("cep/{cep}")
	Call<ListaCepResponse> listarPorCep(@Path("cep") String cep);
	
	@GET("personalizada/listar")
	Call<ListaCepResponse> listarPorEndereco(@Query("q") String cep);
	
}
