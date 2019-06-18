package br.com.agencialove.tpa.webservices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import br.com.agencialove.tpa.model.AdditionalServices;
import br.com.agencialove.tpa.model.Address;
import br.com.agencialove.tpa.model.PackageMeasures;
import br.com.agencialove.tpa.model.ZipType;
import br.com.agencialove.tpa.model.rest.EmiteEtiquetaRequest;
import br.com.agencialove.tpa.model.rest.PrePost;
import br.com.agencialove.tpa.model.rest.PrePostGet;
import br.com.agencialove.tpa.model.rest.PrePostGetResponse;
import br.com.agencialove.tpa.model.rest.PrePostResponse;
import br.com.agencialove.tpa.model.rest.ServicesRequest;
import br.com.agencialove.tpa.model.rest.ServicesResponse;;

public class WebServiceMock implements IWebService{

	@Override
	public List<Address> getAddressFromZip(final String zipCode) {
		final List<Address> ret = new ArrayList<>();

		try {
			final OkHttpClient client = new OkHttpClient();
			final Request request = new Request.Builder()

					.url("http://wsmobile.correios.com.br/DNECWService/rest/cep/" + zipCode)
					.get()
					.build();

			final Response response = client.newCall(request).execute();

			if (response.code() != 200) {
				//TODO to treat the webservices requestion errors
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.code());
			} else {
				final String json = response.body().string();
				final Map<String, Object> objectMap = JsonUtils.fromJson(json);

				if(objectMap == null || !objectMap.containsKey("ceps"))
					return ret;

				for (final Object obj : (List) objectMap.get("ceps")) {
					final Map<String, String> m = (Map) obj;
					final Address address = new Address();
					address.setZip(m.get("CEP"));
					address.setState(m.get("UF"));
					address.setCity(m.get("LOCAL"));
					address.setStreet(m.get("ENDERECO"));
					address.setNeighborhood(m.get("BAIRRO"));

					ret.add(address);
				}
			}

		} catch (final Exception e) {
			//TODO to treat the webservices requestion errors
			e.printStackTrace();
		}

		return ret;
	}

	@Override
	public List<ServicesResponse> getAvailableServices(final ServicesRequest ppr) {
		final List<ServicesResponse> lstResponse = new ArrayList<>();

		try {
			final OkHttpClient client = new OkHttpClient();


			final String bodyContent = ppr.toString().replace("'", "\"");



			new ServicesResponse(
					"04014",
					"SEDEX",
					"94,10",
					"3",
					"0,00",
					"0,00",
					"0,00",
					"S",
					"S",
					"0",
					"",
					"",
					"94,10");

			final MediaType mediaType = MediaType.parse("application/json");

			final RequestBody body = RequestBody.create(mediaType, bodyContent);
			final Request request = new Request.Builder()
					.header("Authorization", "Basic YWdlbmNpYWxvdmU6NzUzNjIzMTA3NA==")
					.url("https://apps.correios.com.br/preatendimento-rs/v1/atendimento/precoPrazoCartao?cartaopostagem=0074290380")
					.post(body)
					.build();
			final Response response = client.newCall(request).execute();

			if (response.code() != 200) {
				//TODO to treat the webservices requestion errors
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.code());
			} else{
				final String json = response.body().string();

				final Map<String, Object> objectMap = JsonUtils.fromJson(json);


				for (final Object obj : (List) objectMap.get("listaPrecoPrazo")) {
					final Map<String, String> m = (Map) obj;
					lstResponse.add(new ServicesResponse(m.get("codigoServico"), m.get("descricaoServico"),
							m.get("valor"), m.get("prazoEntrega"), m.get("valorMaoPropria"), m.get("valorAvisoRecebimento"),
							m.get("valorValorDeclarado"), m.get("entregaDomiciliar"), m.get("entregaSabado"), m.get("codigoErro"),
							m.get("msgErro"), m.get("observacao"), m.get("valorSemAdicionais")));

				}
				return lstResponse;
			}


		} catch (final IOException e) {
			//TODO to treat the webservices requestion errors
			e.printStackTrace();
		}
		return lstResponse;
	}



	@Override
	public PrePostResponse serviceRegisterResponse(final PrePost prePost) {
		final PrePostResponse ret = new PrePostResponse();
		try {
			final OkHttpClient client = new OkHttpClient();

			final String bodyContent = prePost.toJSON();

			final MediaType mediaType = MediaType.parse("application/json");
			final RequestBody body = RequestBody.create(mediaType, bodyContent);
			final Request request = new Request.Builder()
					.header("Authorization", "Basic YWdlbmNpYWxvdmU6NzUzNjIzMTA3NA==")
					.url("https://apps.correios.com.br/preatendimento-rs/v1/atendimento/unico?cartaopostagem=0074290380&emiteetiqueta=true")
					.post(body)
					.build();
			final Response response = client.newCall(request).execute();

			if (response.code() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.code());
			} else {
				final String json = response.body().string();
				System.out.println(json);


				final Map<String, Object> objectMap = JsonUtils.fromJson(json);

				ret.setNumeroPLP((String)objectMap.get("numeroPLP"));
				ret.setNumeroEtiqueta((String)objectMap.get("numeroEtiqueta"));
			}
		} catch (final IOException e) {
			//TODO to treat the webservices requestion errors
			e.printStackTrace();
		}

		return ret;

	}

	/*@Override
	public PrePost prePostGet(final PrePostResponse prePost) {
		final List<PrePost> lstResponse = new ArrayList<>();
		final PrePost ret = new PrePost();
		try {
			final OkHttpClient client = new OkHttpClient();

			final String bodyContent = prePost.toJSON();

			final MediaType mediaType = MediaType.parse("application/json");
			final RequestBody body = RequestBody.create(mediaType, bodyContent);
			final Request request = new Request.Builder()
					.header("Authorization", "Basic YWdlbmNpYWxvdmU6NzUzNjIzMTA3NA==")
					.url("http://apphom.correios.com.br/serviceExterno/rest/atendimentoRest/atendimento/retornoEtiqueta")
					.post(body)
					.build();
			final Response response = client.newCall(request).execute();

			if (response.code() != 200) {
				System.out.println("\n" + bodyContent);
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.code());
			} else{
				final String json = response.body().string();
				System.out.println(json);

				final Map<String, Object> objectMap = JsonUtils.fromJson(json);


				final List<Object> jsonResponse = new ArrayList<>();

				jsonResponse.add(objectMap.get("plp"));

				System.out.println("jsonResponse = "  + jsonResponse.toString());

				for (final Object obj : (List) jsonResponse) {
					final Map<Object, String> m = (Map) obj;
					/*Metodos para retornar os dados para Full Fill Prepost
					 m.get("id_plp"), m.get("valor_global"), m.get("mcu_unidade_postagem"),
						    m.get("nome_unidade_postagem"), m.get("cartao_postagem")

					ret.setIdPlp(m.get("id_plp"));
					ret.setValorGlobal(m.get("valor_global"));
					ret.setMcuUnidadePostagem(m.get("mcu_unidade_postagem"));
					ret.setNomeUnidadePostagem(m.get("nome_unidade_postagem"));
					ret.setCartaoPostagem(m.get("cartao_postagem"));
				}
				System.out.println("ReturValues: " + ret.returnValues());
				return ret;


				for (final Object obj : (List) jsonResponse) {
					System.out.println("jsonResponse = "  + jsonResponse);
					final Map<String, String> m = (Map) obj;
					lstResponse.add(new PrePost(m.get("id_plp"), m.get("valor_global"), m.get("mcu_unidade_postagem"),
											    m.get("nome_unidade_postagem"), m.get("cartao_postagem")));

				}
				System.out.println("For: " + lstResponse);
				return lstResponse;


				//ret.setNumeroPLP((String)objectMap.get("numeroPLP"));
				//ret.setNumeroEtiqueta((String)objectMap.get("numeroEtiqueta"));
			}
		} catch (final IOException e) {
			//TODO to treat the webservices requestion errors
			e.printStackTrace();
		}

		return ret;

	}*/

	@Override
	public PrePost prePostGet(final PrePostResponse prePost) {
		new ArrayList<>();
		final PrePost ret = new PrePost();
		try {
			final OkHttpClient client = new OkHttpClient();

			final String bodyContent = prePost.toJSON();

			final MediaType mediaType = MediaType.parse("application/json");
			final RequestBody body = RequestBody.create(mediaType, bodyContent);
			final Request request = new Request.Builder()
					.header("Authorization", "Basic YWdlbmNpYWxvdmU6NzUzNjIzMTA3NA==")
					.url("http://apphom.correios.com.br/serviceExterno/rest/atendimentoRest/atendimento/retornoEtiqueta")
					.post(body)
					.build();
			final Response response = client.newCall(request).execute();

			if (response.code() != 200) {
				System.out.println("\n" + bodyContent);
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.code());
			} else{
				final String json = response.body().string();
				final Map<String, Object> objectMap = JsonUtils.fromJson(json);


				//Retornar dados do Destinatario
				for (final Object obj : (List) objectMap.get("objeto_postal")) {
					final Map<String, String> m = (Map) obj;
					final List<Object> jsonResponseDestinatario = new ArrayList<>();
					final List<Object> jsonResponseNacional = new ArrayList<>();
					final List<Object> jsonResponseServicoAdicional = new ArrayList<>();
					final List<Object> jsonResponseDimensaoObjeto = new ArrayList<>();


					jsonResponseDestinatario.add(m.get("destinatario"));

					for (final Object objResponse : (List) jsonResponseDestinatario) {
						final Map<Object, String> m2 = (Map) objResponse;
						ret.setNomeDestinatario(m2.get("nome_destinatario"));
						ret.setNumeroEndDestinatario(m2.get("numero_end_destinatario"));
						ret.setLogradouroDestinatario(m2.get("logradouro_destinatario"));
						ret.setEmailDestinatario(m2.get("email_destinatario"));
						ret.setTelefoneDestinatario(m2.get("celular_destinatario"));
					}

					jsonResponseNacional.add(m.get("nacional"));
					for (final Object objResponse : (List) jsonResponseNacional) {
						final Map<Object, String> m2 = (Map) objResponse;
						ret.setCepDestinatario(m2.get("cep_destinatario"));
					}

					jsonResponseServicoAdicional.add(m.get("servico_adicional"));
					for (final Object objResponse : (List) jsonResponseServicoAdicional) {
						final List<Object> jsonResponseCodigo = new ArrayList<>();
						final Map<Object, String> m2 = (Map) objResponse;
						jsonResponseCodigo.add(m2.get("codigo_servico_adicional"));
						for(final Object objResponseCodigo : (List) jsonResponseCodigo) {


						}

					}

					jsonResponseDimensaoObjeto.add(m.get("dimensao_objeto"));
					for (final Object objResponse : (List) jsonResponseDimensaoObjeto) {
						final Map<Object, String> m2 = (Map) objResponse;
						ret.setTipoObjeto(m2.get("tipo_objeto"));
						ret.setDimensaoLargura(m2.get("dimensao_largura"));
						ret.setDimensaoAltura(m2.get("dimensao_altura"));
						ret.setDimensaoComprimento(m2.get("dimensao_comprimento"));
						ret.setDimensaoDiametro(m2.get("dimensao_diametro"));
					}
				}

				//Retornar dados do Remetente
				final List<Object> jsonResponse = new ArrayList<>();
				jsonResponse.add(objectMap.get("remetente"));
				for (final Object obj : (List) jsonResponse) {
					final Map<Object, String> m = (Map) obj;

					ret.setNomeRemetente(m.get("nome_remetente"));
					ret.setCepRemetente(m.get("cep_remetente"));
					ret.setNumeroEndRemetente(m.get("numero_remetente"));
					ret.setLogradouroRemetente(m.get("logradouro_remetente"));
					ret.setEmailRemetente(m.get("email_remetente"));
					ret.setCelularRemetente(m.get("telefone_remetente"));
				}

				//System.out.println("ReturValues: " + ret.returnValues() + "\n");
				return ret;

				//ret.setNumeroPLP((String)objectMap.get("numeroPLP"));
				//ret.setNumeroEtiqueta((String)objectMap.get("numeroEtiqueta"));
			}
		} catch (final IOException e) {
			//TODO to treat the webservices requestion errors
			e.printStackTrace();
		}

		return ret;

	}

	//	@Override
	//	public byte[] getPdfBytes(final EmiteEtiquetaRequest ppr) {
	//		byte[] pdfContent = null;
	//		try {
	//			final OkHttpClient client = new OkHttpClient();
	//
	//
	//			final String bodyContent = ppr.toJSON();
	//
	//
	//			final MediaType mediaType = MediaType.parse("application/json");
	//
	//			final RequestBody body = RequestBody.create(mediaType, bodyContent);
	//			final Request request = new Request.Builder()
	//					.header("Authorization", "Basic YWdlbmNpYWxvdmU6NzUzNjIzMTA3NA==")
	//					.url("https://apps.correios.com.br/preatendimento-rs/v1/atendimento/emiteEtiqueta")
	//					.post(body)
	//					.build();
	//			final Response response = client.newCall(request).execute();
	//
	//			if (response.code() != 200) {
	//				//TODO to treat the webservices requestion errors
	//				throw new RuntimeException("Failed : HTTP error code : "
	//						+ response.code());
	//			} else{
	//				pdfContent = response.body().bytes();
	//				//final String json = response.body().string();
	//				//final Map<String, Object> objectMap = JsonUtils.fromJson(json);
	//
	//				}
	//
	//				return pdfContent;
	//
	//		} catch (final IOException e) {
	//			//TODO to treat the webservices requestion errors
	//			e.printStackTrace();
	//		}
	//		return pdfContent;
	//
	//	}

	@Override
	public List<PrePostGetResponse> getNplp(final PrePostGet ppr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getPdfBytesEmiteEtiqueta(final EmiteEtiquetaRequest ppr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getPdfBytesAvisoRecebimento(final EmiteEtiquetaRequest ppr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getPdfBytesDeclaracaoDeConteudo(final EmiteEtiquetaRequest ppr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PrePostResponse serviceRegisterResponseUnpaid(final PrePost prePost) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PrePost getPrePostRequest(final Address pSender, final Address pReceiver, final AdditionalServices pServices,
			final PackageMeasures pMeasures, final ServicesResponse servicesResponse) {
		
		
		final PrePost req = new PrePost();
		req.setBairroDestinatario("Vila Harmonia");
		req.setComplementoDestinatario("Próximo ao balão das roseira");
		req.setEmailDestinatario("danilopietroheitordossantos..danilopietroheitordossantos@santander.com.br");
		req.setNomeDestinatario("Danilo Pietro Heitor dos Santos");
		req.setNumeroEndDestinatario("2323");
		req.setCelularDestinatario("(91) 98537-7810");
		req.setTelefoneDestinatario("(91) 3888-2603");
		req.setCpf_cnpj_destinatario("339.861.195-82");
		req.setUfDestinatario("MG");
		req.setCepDestinatario("66030-050");
		req.setCidadeDestinatario("Belém");
		req.setLogradouroDestinatario("Passagem Paulo Roberto\n");

		if(pSender.getPerson().getType() == ZipType.SENDER) {
			req.setBairroRemetente(pSender.getNeighborhood());
			req.setComplementoRemetente(pSender.getComplement());
			req.setEmailRemetente(pSender.getPerson().getEmail());
			req.setNomeRemetente("Patrícia Emanuelly Marina Assis");
			req.setNumeroEndRemetente("Cajazeiras");
			req.setCelularRemetente("(71) 99126-8615");
			req.setTelefoneRemetente("(71) 3608-7243");
			req.setCpf_cnpj_remetente("461.169.241-82");
			req.setUfRemetente("SP");
			req.setCepRemetente("41342-1000");
			req.setCidadeRemetente("Rio Preto");
			req.setLogradouroRemetente("");
		}

		req.setFormaPagamento("1");
		req.setCartaoPostagem("");
		req.setCentroCustoCliente("0069950016");
		req.setCodigoAdministrativo("17000190");
		req.setCodigoObjetoCliente("");
		req.setCodigoServicoAdicional("025");
		req.setCodigoServicoPostagem("");
		req.setCodigoUsuarioPostal("");
		req.setDataPostagemSara("");
		req.setDescricaoObjeto("");
		req.setDimensaoAltura("18");
		req.setDimensaoComprimento("18");
		req.setDimensaoDiametro("0");
		req.setDimensaoLargura("13.5");
		req.setFormaPagamento("1");
		req.setIdPlp("");
		req.setMcuUnidadePostagem("");
		req.setTipoArquivo("Postagem");
		req.setNaturezaNotaFiscal("");
		//AQui
		req.setNomeUnidadePostagem("");
		req.setNumeroComprovantePostagem("");
		req.setNumeroContrato("9992157880");
		req.setNumeroDiretoria("10");
		req.setNumeroEndDestinatario(pReceiver.getNumber());
		req.setNumeroEtiqueta("XX999999999BB");
		req.setNumeroNotaFiscal("000000000");
		req.setPeso("15");
		req.setRt1("");
		req.setRt2("");
		req.setSerieNotaFiscal("");
		req.setStatusProcessamento("0");
		req.setTipoObjeto("001");
		req.setUfDestinatario(pReceiver.getStreet());
		req.setValorACobrar("000000,00");
		req.setValorCobrado("");
		req.setValorDeclarado(pServices.getContentValue());
		req.setValorGlobal("");
		req.setValorNotaFiscal("");
		req.setVersaoArquivo("2.3");
		
		
		
		
		
		return null;
	}

	@Override
	public ServicesRequest getServiceRequest(final Address pSender, final Address pReceiver, final AdditionalServices pServices, final PackageMeasures pMeasures) {
		// TODO Auto-generated method stub
		return null;
	}
}
