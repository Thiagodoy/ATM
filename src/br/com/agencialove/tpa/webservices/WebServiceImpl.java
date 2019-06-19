package br.com.agencialove.tpa.webservices;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

public class WebServiceImpl implements IWebService{

	@Override
	public List<Address> getAddressFromZip(final String zipCode) {
		final List<Address> ret = new ArrayList<>();
		
		final String url = this.mountUrl(zipCode);
		try {
			final OkHttpClient client = new OkHttpClient();
			final Request request = new Request.Builder()
					.url(url)
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

	private String mountUrl(String zip) {
		Pattern p = Pattern.compile("^\\d{2}\\.?\\d{3}-?\\d{3}$");
		Matcher m = p.matcher(zip);
		boolean isZipCode = m.find();
		
		String urlZip = "http://wsmobile.correios.com.br/DNECWService/rest/cep/" + zip.replace(".", "").replace("-", "");
		String urlCustom = "http://wsmobile.correios.com.br/DNECWService/rest/personalizada/listar?q=" + zip;
		
		
		return isZipCode ? urlZip : urlCustom;
	}	
	
	@Override
	public List<ServicesResponse> getAvailableServices(final ServicesRequest ppr) {
		final List<ServicesResponse> lstResponse = new ArrayList<>();

		try {
			final OkHttpClient client = new OkHttpClient();
			client.setConnectTimeout(60, TimeUnit.SECONDS); // connect timeout
			client.setReadTimeout(60, TimeUnit.SECONDS);    // socket timeout


			final String bodyContent = ppr.toString().replace("'", "\"");

			final MediaType mediaType = MediaType.parse("application/json");

			final RequestBody body = RequestBody.create(mediaType, bodyContent);
			final Request request = new Request.Builder()
					.header("Authorization", "Basic YXRtMTIzNDU6YXRtMTIzNDU=")
					.url("https://apphom.correios.com.br/preatendimento-rs/v1/atendimento/precoPrazoCartao?cartaopostagem=0074290380")
					.post(body)
					.build();
			final Response response = client.newCall(request).execute();

			if (response.code() != 200) {
				//TODO to treat the webservices requestion errors
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.body().string());
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

	//Metódo que retorna o numero PLP
	@Override
	public PrePostResponse serviceRegisterResponse(final PrePost prePost) {
		final PrePostResponse ret = new PrePostResponse();
		try {
			final OkHttpClient client = new OkHttpClient();

			final String bodyContent = prePost.toJSON();
			//TODO Remove this sysout
			System.out.println(bodyContent);

			final MediaType mediaType = MediaType.parse("application/json");
			final RequestBody body = RequestBody.create(mediaType, bodyContent);
			final Request request = new Request.Builder()
					.header("Authorization", "Basic YXRtMTIzNDU6YXRtMTIzNDU=")
					.url("https://apphom.correios.com.br/preatendimento-rs/v1/atendimento/unico?cartaopostagem=0074290380&emiteetiqueta=true")
					.post(body)
					.build();
			final Response response = client.newCall(request).execute();

			if (response.code() != 200) {
				//TODO Remove this sysout
				System.out.println(response.body().string());
				throw new RuntimeException("Failed : HTTP error code : " + response.code());
			} else {


				final String json = response.body().string();
				//System.out.println(json);
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

	@Override
	public PrePostResponse serviceRegisterResponseUnpaid(final PrePost prePost) {
		final PrePostResponse ret = new PrePostResponse();
		try {
			final OkHttpClient client = new OkHttpClient();

			final String bodyContent = prePost.toJSON();

			final MediaType mediaType = MediaType.parse("application/json");
			final RequestBody body = RequestBody.create(mediaType, bodyContent);
			final Request request = new Request.Builder()
					.header("Authorization", "Basic YXRtMTIzNDU6YXRtMTIzNDU=")
					.url("https://apphom.correios.com.br/preatendimento-rs/v1/atendimento/unico?cartaopostagem=0074290380&emiteetiqueta=false")
					.post(body)
					.build();
			final Response response = client.newCall(request).execute();

			if (response.code() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.code());
			} else {


				final String json = response.body().string();
				//System.out.println(json);
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
					.header("Authorization", "Basic YXRtMTIzNDU6YXRtMTIzNDU=")
					.url("http://apphom.correios.com.br/serviceExterno/rest/atendimentoRest/atendimento/retornoEtiqueta")
					.post(body)
					.build();
			final Response response = client.newCall(request).execute();

			if (response.code() != 200) {
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
						ret.setCpf_cnpj_destinatario("cpf_cnpj_destinatario");
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
					ret.setCelularRemetente("cpf_cnpj_remetente");
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

	@Override
	public byte[] getPdfBytesEmiteEtiqueta(final EmiteEtiquetaRequest ppr) {
		byte[] pdfContent = null;
		try {
			final OkHttpClient client = new OkHttpClient();


			final String bodyContent = ppr.toJSON();


			final MediaType mediaType = MediaType.parse("application/json");

			final RequestBody body = RequestBody.create(mediaType, bodyContent);
			final Request request = new Request.Builder()
					.header("Authorization", "Basic YXRtMTIzNDU6YXRtMTIzNDU=")
					.url("https://apphom.correios.com.br/preatendimento-rs/v1/atendimento/emiteEtiqueta")
					.post(body)
					.build();
			final Response response = client.newCall(request).execute();

			if (response.code() != 200) {
				//TODO to treat the webservices requestion errors
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.code());
			} else{
				pdfContent = response.body().bytes();
				final FileOutputStream fos = new FileOutputStream("xxx.pdf");
				fos.write(pdfContent);
				fos.flush();
				fos.close();
				//final String json = response.body().string();
				//final Map<String, Object> objectMap = JsonUtils.fromJson(json);

			}

			return pdfContent;

		} catch (final IOException e) {
			//TODO to treat the webservices requestion errors
			e.printStackTrace();
		}
		return pdfContent;

	}

	@Override
	public byte[] getPdfBytesAvisoRecebimento(final EmiteEtiquetaRequest ppr) {
		byte[] pdfContent = null;
		try {
			final OkHttpClient client = new OkHttpClient();


			final String bodyContent = ppr.toJSON();


			final MediaType mediaType = MediaType.parse("application/json");

			final RequestBody body = RequestBody.create(mediaType, bodyContent);
			final Request request = new Request.Builder()
					.header("Authorization", "Basic YXRtMTIzNDU6YXRtMTIzNDU=")
					.url("https://apphom.correios.com.br/preatendimento-rs/v1/atendimento/emiteAvisoRecebimento")
					.post(body)
					.build();
			final Response response = client.newCall(request).execute();

			if (response.code() != 200) {
				//TODO to treat the webservices requestion errors
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.code());
			} else{
				pdfContent = response.body().bytes();
				//final String json = response.body().string();
				//final Map<String, Object> objectMap = JsonUtils.fromJson(json);
				System.out.println(pdfContent);
			}

			return pdfContent;

		} catch (final IOException e) {
			//TODO to treat the webservices requestion errors
			e.printStackTrace();
		}
		return pdfContent;

	}

	@Override
	public byte[] getPdfBytesDeclaracaoDeConteudo(final EmiteEtiquetaRequest ppr) {
		byte[] pdfContent = null;
		try {
			final OkHttpClient client = new OkHttpClient();


			final String bodyContent = ppr.toJSON();


			final MediaType mediaType = MediaType.parse("application/json");

			final RequestBody body = RequestBody.create(mediaType, bodyContent);
			final Request request = new Request.Builder()
					.header("Authorization", "Basic YXRtMTIzNDU6YXRtMTIzNDU=")
					.url("https://apphom.correios.com.br/preatendimento-rs/v1/atendimento/emiteDeclaracaoConteudo")
					.post(body)
					.build();
			final Response response = client.newCall(request).execute();

			if (response.code() != 200) {
				//TODO to treat the webservices requestion errors
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.code());
			} else{
				pdfContent = response.body().bytes();
				//final String json = response.body().string();
				//final Map<String, Object> objectMap = JsonUtils.fromJson(json);
				System.out.println(pdfContent);
			}

			return pdfContent;

		} catch (final IOException e) {
			//TODO to treat the webservices requestion errors
			e.printStackTrace();
		}
		return pdfContent;

	}

	@Override
	public PrePost getPrePostRequest(final Address pSender, final Address pReceiver, final AdditionalServices pServices,  final PackageMeasures pMeasures, final ServicesResponse respService){
		final PrePost req = new PrePost();

		req.setBairroDestinatario(pReceiver.getNeighborhood());
		req.setComplementoDestinatario(pReceiver.getComplement());
		req.setEmailDestinatario(pReceiver.getPerson().getEmail());
		req.setNomeDestinatario(pReceiver.getPerson().getName());
		req.setNumeroEndDestinatario(pReceiver.getNumber());
		req.setCelularDestinatario(pReceiver.getPerson().getCellPhone());
		req.setTelefoneDestinatario(pReceiver.getPerson().getCellPhone());
		req.setCpf_cnpj_destinatario(pReceiver.getPerson().getCPF_CPNJ());
		req.setUfDestinatario(pReceiver.getState());
		req.setCepDestinatario(pReceiver.getZip());
		req.setCidadeDestinatario(pReceiver.getCity());
		req.setLogradouroDestinatario(pReceiver.getStreet());

		if(pSender.getPerson().getType() == ZipType.SENDER) {
			req.setBairroRemetente(pSender.getNeighborhood());
			req.setComplementoRemetente(pSender.getComplement());
			req.setEmailRemetente(pSender.getPerson().getEmail());
			req.setNomeRemetente(pSender.getPerson().getName());
			req.setNumeroEndRemetente(pSender.getNumber());
			req.setCelularRemetente(pSender.getPerson().getCellPhone());
			req.setTelefoneRemetente(pSender.getPerson().getCellPhone());
			req.setCpf_cnpj_remetente(pSender.getPerson().getCPF_CPNJ());
			req.setUfRemetente(pSender.getState());
			req.setCepRemetente(pSender.getZip());
			req.setCidadeRemetente(pSender.getCity());
			req.setLogradouroRemetente(pSender.getStreet());
		}

		req.setFormaPagamento("1");
		req.setCartaoPostagem("");
		req.setCentroCustoCliente("0069950016");
		req.setCodigoAdministrativo("17000190");
		req.setCodigoObjetoCliente("");
		req.setCodigoServicoAdicional("025");
		req.setCodigoServicoPostagem(respService.getCodigoServico());
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

		return req;
	}

	@Override
	public ServicesRequest getServiceRequest(final Address pSender, final Address pReceiver, final AdditionalServices pServices, final PackageMeasures pMeasures) {
		final ServicesRequest req = new ServicesRequest();
		req.setCepOrigem(pSender.getZip());
		req.setCepDestino(pReceiver.getZip());
		req.setPeso(pMeasures.getWeight());
		req.setFormato("1");
		req.setComprimento(pMeasures.getDepth());
		req.setAltura(pMeasures.getHeight());
		req.setLargura(pMeasures.getWidth());
		req.setDiametro(pMeasures.getDiameter());
		req.setMaoPropria(pServices.isOnwHands() ? "S" : "N"); //$NON-NLS-1$ //$NON-NLS-2$
		req.setValorDeclarado(pServices.isValueDeclaration() ? pServices.getContentValue() : "0"); //$NON-NLS-1$
		req.setAvisoRecebimento(pServices.isDeliveryNotice() ? "S" : "N"); //$NON-NLS-1$ //$NON-NLS-2$

		System.out.println(req.toString());

		return req;
	}

	@Override
	public List<PrePostGetResponse> getNplp(final PrePostGet ppr) {
		// TODO Auto-generated method stub
		return null;
	}
}
