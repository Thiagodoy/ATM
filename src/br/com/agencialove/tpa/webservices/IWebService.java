package br.com.agencialove.tpa.webservices;

import java.util.List;

import br.com.agencialove.tpa.model.AdditionalServices;
import br.com.agencialove.tpa.model.Address;
import br.com.agencialove.tpa.model.PackageMeasures;
import br.com.agencialove.tpa.model.rest.EmiteEtiquetaRequest;
import br.com.agencialove.tpa.model.rest.PrePost;
import br.com.agencialove.tpa.model.rest.PrePostGet;
import br.com.agencialove.tpa.model.rest.PrePostGetResponse;
import br.com.agencialove.tpa.model.rest.PrePostResponse;
import br.com.agencialove.tpa.model.rest.ServicesRequest;
import br.com.agencialove.tpa.model.rest.ServicesResponse;

public interface IWebService {
	List<Address> getAddressFromZip(final String zipCode);

	List<ServicesResponse> getAvailableServices(final ServicesRequest ppr);

	List<PrePostGetResponse> getNplp(final PrePostGet ppr);


	PrePostResponse serviceRegisterResponse(final PrePost prePost);

	PrePostResponse serviceRegisterResponseUnpaid(PrePost prePost);

	PrePost prePostGet(final PrePostResponse prePost);

	PrePost getPrePostRequest(Address pSender, Address pReceiver, AdditionalServices pServices, PackageMeasures pMeasures, ServicesResponse respService);

	ServicesRequest getServiceRequest(Address pSender, Address pReceiver, AdditionalServices pServices, PackageMeasures pMeasures);

	byte[] getPdfBytesEmiteEtiqueta(EmiteEtiquetaRequest ppr);

	byte[] getPdfBytesAvisoRecebimento(EmiteEtiquetaRequest ppr);

	byte[] getPdfBytesDeclaracaoDeConteudo(EmiteEtiquetaRequest ppr);

}
