package br.com.agencia.rest;

import br.com.agencia.tpa.rest.request.EmiteRequest;
import br.com.agencia.tpa.rest.request.PrePostagemRequest;
import br.com.agencia.tpa.rest.request.PrecoPrazoRequest;
import br.com.agencia.tpa.rest.response.ListaPrecoPrazoResponse;
import br.com.agencia.tpa.rest.response.PrePostagemResponse;
import br.com.agencia.tpa.rest.response.EtiquetaResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;



public interface CorreiosPreAtendimentoService {
	
	
	@Headers("Authorization:Basic YXRtMTIzNDU6YXRtMTIzNDU=")
	@POST(value = "precoPrazoCartao")
	Call<ListaPrecoPrazoResponse> precoPrazoCartao(@Body PrecoPrazoRequest request, @Query("cartaopostagem") String cartaopostagem);
	
	@Headers("Authorization:Basic YXRtMTIzNDU6YXRtMTIzNDU=")
	@POST(value = "emiteEtiqueta")
	Call<ResponseBody> emitiEtiqueta(@Body EmiteRequest request, @Query("cartaopostagem") String cartaopostagem);

	@Headers("Authorization:Basic YXRtMTIzNDU6YXRtMTIzNDU=")
	@POST(value = "emitiAvisoRecebimento")
	Call<ResponseBody> emitiAvisoRecebimento(@Body EmiteRequest request, @Query("cartaopostagem") String cartaopostagem);	
	
	@Headers("Authorization:Basic YXRtMTIzNDU6YXRtMTIzNDU=")
	@POST(value = "emitiAvisoRecebimento")
	Call<ResponseBody> emitiDeclaracaoConteudo(@Body EmiteRequest request, @Query("cartaopostagem") String cartaopostagem);

	
	@Headers("Authorization:Basic YXRtMTIzNDU6YXRtMTIzNDU=")
	@POST(value = "unico")
	Call<EtiquetaResponse> gerarPrePostagem(@Body PrePostagemRequest request, @Query("cartaopostagem") String cartaopostagem,@Query("emiteetiqueta") boolean emiteetiqueta);
	
	@Headers("Authorization:Basic YXRtMTIzNDU6YXRtMTIzNDU=")
	@POST(value = "plp")
	Call<PrePostagemResponse> informacaoPlp(@Body EmiteRequest request);
	
	@Headers("Authorization:Basic YXRtMTIzNDU6YXRtMTIzNDU=")
	@POST(value = "cancela-objeto")
	Call<ResponseBody> cancelaObjeto(@Body EmiteRequest request);
	
}
