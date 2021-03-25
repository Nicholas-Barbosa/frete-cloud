
package com.farawaybr.frete.sefaz.client.distDFe.cte;

import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

import com.farawaybr.frete.domain.CertificateKeystore;
import com.farawaybr.frete.sefaz.client.distDFe.cte.unmarshaller.UnCteDistResponse;
import com.farawaybr.frete.sefaz.httpclient.CloseableHttpClientSslFactory;
import com.farawaybr.frete.sefaz.keystore.KeyTrustStoreLoader;
import com.farawaybr.frete.sefaz.properties.SefazProperties;

import br.inf.portalfiscal.cte.DistDFeInt;

public class DistDFeConhecimentoWSClient extends WebServiceGatewaySupport {

	private final SefazProperties sefazProperties;

	private final KeyTrustStoreLoader keyTrustLoader;

	private final DistDFeCteRequestObjectsFactory distDfeCteFactory;

	public DistDFeConhecimentoWSClient(SefazProperties sefazProperties, KeyTrustStoreLoader keyTrustLoader,
			DistDFeCteRequestObjectsFactory distDfeCteFactory) {
		super();
		this.sefazProperties = sefazProperties;
		this.keyTrustLoader = keyTrustLoader;
		this.distDfeCteFactory = distDfeCteFactory;
	}

	public void send(CertificateKeystore certificateKeystore) throws Exception {

		DistDFeInt distDFeInt = distDfeCteFactory.getRequestObjectInstance();
		setDistDFeIntAttributes(distDFeInt, certificateKeystore);

		WebServiceTemplate webServiceTemplate = getWebServiceTemplate();

		HttpComponentsMessageSender httpComponentsMessageSender = new HttpComponentsMessageSender();

		CloseableHttpClientSslFactory httpClientFactory = CloseableHttpClientSslFactory.create();

		httpClientFactory.setKeyManager(certificateKeystore.new KeystoreLoader().keysManager())
				.setTrustManager(keyTrustLoader.trustManager());

		httpComponentsMessageSender.setHttpClient(httpClientFactory.getCloseableHttpClient());
		webServiceTemplate.setMessageSender(httpComponentsMessageSender);
		UnCteDistResponse response = (UnCteDistResponse) getWebServiceTemplate()

				.marshalSendAndReceive(distDfeCteFactory.getAllRequestObject(), message -> {
					SoapMessage soapMessage = (SoapMessage) message;
					soapMessage.setSoapAction(
							sefazProperties.getDocumentoFiscalEletronico().getConhecimento().getSoapAction());

				});

		System.out.println("ret " + response.getUnCteDistInteresseResult().getUnRetDistDFeInt().getxMotivo());
	}

	private void setDistDFeIntAttributes(DistDFeInt distDFeInt, CertificateKeystore certificateKeystore) {
		distDFeInt.setVersao("1.00");
		distDFeInt.setTpAmb("1");
		distDFeInt.setCUFAutor("41");
		distDFeInt.setCNPJ(certificateKeystore.getCnpj());
		distDfeCteFactory.setUltNsu(certificateKeystore.getNsuToFetch());
	}
}
