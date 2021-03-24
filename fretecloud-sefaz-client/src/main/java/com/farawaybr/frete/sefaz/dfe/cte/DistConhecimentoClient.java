package com.farawaybr.frete.sefaz.dfe.cte;

import java.io.IOException;

import javax.xml.transform.TransformerException;

import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

import com.example.consumingwebservice.wsdl.CteDistDFeInteresse.CteDadosMsg;
import com.example.consumingwebservice.wsdl.CteDistDFeInteresseResponse;
import com.farawaybr.frete.domain.CertificateKeystore;
import com.farawaybr.frete.sefaz.httpclient.CloseableHttpClientSslFactory;
import com.farawaybr.frete.sefaz.keystore.KeyTrustStoreLoader;
import com.farawaybr.frete.sefaz.properties.SefazProperties;
import com.farawaybr.frete.sefaz.wsdl.DistDFeInt;

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

		com.example.consumingwebservice.wsdl.CteDistDFeInteresse interesse = new com.example.consumingwebservice.wsdl.CteDistDFeInteresse();
		CteDadosMsg dadosMsg = new CteDadosMsg();
		dadosMsg.getContent().add(new DistDFeInt());
		interesse.setCteDadosMsg(dadosMsg);
		WebServiceTemplate webServiceTemplate = getWebServiceTemplate();

		HttpComponentsMessageSender httpComponentsMessageSender = new HttpComponentsMessageSender();

		CloseableHttpClientSslFactory httpClientFactory = CloseableHttpClientSslFactory.create();

		httpClientFactory.setKeyManager(certificateKeystore.new KeystoreLoader().keysManager())
				.setTrustManager(keyTrustLoader.trustManager());

		httpComponentsMessageSender.setHttpClient(httpClientFactory.getCloseableHttpClient());
		webServiceTemplate.setMessageSender(httpComponentsMessageSender);
		CteDistDFeInteresseResponse response = (CteDistDFeInteresseResponse) getWebServiceTemplate()
				.marshalSendAndReceive(interesse, new WebServiceMessageCallback() {

					@Override
					public void doWithMessage(WebServiceMessage message) throws IOException, TransformerException {
						((SoapMessage) message).setSoapAction(
								"http://www.portalfiscal.inf.br/cte/wsdl/CTeDistribuicaoDFe/cteDistDFeInteresse");

						System.out.println("SoapBody" + ((SoapMessage) message).getSoapBody());
					}
				});

		System.out.println(response.getCteDistDFeInteresseResult().getContent());
	}
}
