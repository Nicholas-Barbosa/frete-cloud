
package com.farawaybr.frete.sefaz.dfe.cte;

import java.io.FileOutputStream;

import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

import com.example.consumingwebservice.wsdl.CteDistDFeInteresse.CteDadosMsg;
import com.farawaybr.frete.data.xml.GenericSerialiazerDeserializer;
import com.farawaybr.frete.domain.CertificateKeystore;
import com.farawaybr.frete.sefaz.ctedistdfe.template.CteDistDfeRequestPayload;
import com.farawaybr.frete.sefaz.ctedistdfe.template.CteDistDfeRequestPayload.DistNsuTemplate;
import com.farawaybr.frete.sefaz.dfe.cte.unmarshaller.UnCteDistResponse;
import com.farawaybr.frete.sefaz.httpclient.CloseableHttpClientSslFactory;
import com.farawaybr.frete.sefaz.keystore.KeyTrustStoreLoader;
import com.farawaybr.frete.sefaz.properties.SefazProperties;

import br.inf.portalfiscal.cte.DistDFeInt;
import br.inf.portalfiscal.cte.DistDFeInt.DistNSU;

public class DistConhecimentoClient extends WebServiceGatewaySupport {

	private final SefazProperties sefazProperties;

	private final KeyTrustStoreLoader keyTrustLoader;

	public DistConhecimentoClient(SefazProperties sefazProperties, KeyTrustStoreLoader keyTrustLoader) {
		super();
		this.sefazProperties = sefazProperties;
		this.keyTrustLoader = keyTrustLoader;
	}

	public void send(CertificateKeystore certificateKeystore) throws Exception {

		// CteDistDFeInteresse cteDistDFeInteresse = new CteDistDFeInteresse();

//	

		com.example.consumingwebservice.wsdl.CteDistDFeInteresse interesse = new com.example.consumingwebservice.wsdl.CteDistDFeInteresse();
		CteDadosMsg dadosMsg = new CteDadosMsg();

		DistDFeInt cte = new DistDFeInt();
		cte.setVersao("1.00");
		cte.setTpAmb("1");
		cte.setCUFAutor("41");
		cte.setCNPJ("09512164000172");

		DistNSU distNsu = new DistNSU();
		distNsu.setUltNSU("000000000000000");

		cte.setDistNSU(distNsu);
		dadosMsg.getContent().add(cte);

		interesse.setCteDadosMsg(dadosMsg);

		WebServiceTemplate webServiceTemplate = getWebServiceTemplate();

		HttpComponentsMessageSender httpComponentsMessageSender = new HttpComponentsMessageSender();

		CloseableHttpClientSslFactory httpClientFactory = CloseableHttpClientSslFactory.create();

		httpClientFactory.setKeyManager(certificateKeystore.new KeystoreLoader().keysManager())
				.setTrustManager(keyTrustLoader.trustManager());

		httpComponentsMessageSender.setHttpClient(httpClientFactory.getCloseableHttpClient());
		webServiceTemplate.setMessageSender(httpComponentsMessageSender);
		UnCteDistResponse response = (UnCteDistResponse) getWebServiceTemplate()

				.marshalSendAndReceive(interesse, message -> {
					SoapMessage soapMessage = (SoapMessage) message;
					soapMessage.setSoapAction(
							"http://www.portalfiscal.inf.br/cte/wsdl/CTeDistribuicaoDFe/cteDistDFeInteresse");
					soapMessage.writeTo(new FileOutputStream(
							"C:\\Users\\nicho\\OneDrive\\Documentos\\teste soapmessage\\distcte.xml"));

					soapMessage.getSoapBody().getAllAttributes()
							.forEachRemaining(x -> System.out.println("namespace " + x.getNamespaceURI()));
				});

		System.out.println("ret " + response.getUnCteDistInteresseResult().getUnRetDistDFeInt().getxMotivo());
	}

}
