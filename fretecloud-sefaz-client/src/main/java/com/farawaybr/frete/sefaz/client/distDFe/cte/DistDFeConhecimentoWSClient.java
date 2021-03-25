
package com.farawaybr.frete.sefaz.client.distDFe.cte;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

import com.farawaybr.frete.domain.CertificateKeystore;
import com.farawaybr.frete.sefaz.client.distDFe.cte.unmarshaller.TipoAmbient;
import com.farawaybr.frete.sefaz.client.distDFe.cte.unmarshaller.UnCteDistResponse;
import com.farawaybr.frete.sefaz.httpclient.CloseableHttpClientSslFactory;
import com.farawaybr.frete.sefaz.keystore.KeyTrustStoreLoader;
import com.farawaybr.frete.sefaz.properties.SefazProperties;

import br.inf.portalfiscal.cte.DistDFeInt;

public class DistDFeConhecimentoWSClient extends WebServiceGatewaySupport implements DistDFeConhecimentoWSOperations {

	private final Logger log = LoggerFactory.getLogger(DistDFeConhecimentoWSClient.class);

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

	/**
	 * Call sendAndRecive() until max NSU is reached.
	 * 
	 * @throws IOException
	 * @throws NoSuchProviderException
	 * @throws CertificateException
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * @throws UnrecoverableKeyException
	 * 
	 */
	@Override
	public void sendAndReceiveFull(CertificateKeystore certificateKeystore)
			throws UnrecoverableKeyException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException,
			CertificateException, NoSuchProviderException, IOException {
		String status = null;
		do {

			UnCteDistResponse response = sendAndReceive(certificateKeystore);
			status = response == null ? null : response.getUnCteDistInteresseResult().getUnRetDistDFeInt().getcStat();

		} while (status != null);
	}

	/**
	 * Request ctes to sefaz.
	 * 
	 * @throws IOException
	 * @throws NoSuchProviderException
	 * @throws CertificateException
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws UnrecoverableKeyException
	 * @throws KeyManagementException
	 */
	@Override
	public UnCteDistResponse sendAndReceive(CertificateKeystore certificateKeystore)
			throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, CertificateException,
			NoSuchProviderException, IOException, KeyManagementException {
		log.info("Sending request to " + getDefaultUri() + "...");

		DistDFeInt distDFeInt = distDfeCteFactory.getRequestObjectInstance();
		setDistDFeIntAttributes(distDFeInt, certificateKeystore);

		WebServiceTemplate webServiceTemplate = getWebServiceTemplate();

		HttpComponentsMessageSender httpComponentsMessageSender = new HttpComponentsMessageSender();

		log.info("Setting keystore and truststore to host: " + getDefaultUri() + "...");
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
		String status = response.getUnCteDistInteresseResult().getUnRetDistDFeInt().getcStat();
		String xMotivo = response.getUnCteDistInteresseResult().getUnRetDistDFeInt().getxMotivo();

		log.info("Got reponse from" + getDefaultUri() + ", status: " + status + " xMotivo: " + xMotivo);

		return status.equals("138") ? response : (status.equals("137") ? response : null);
	}

	private void setDistDFeIntAttributes(DistDFeInt distDFeInt, CertificateKeystore certificateKeystore) {
		log.debug("Setting attributes to request object...");
		distDFeInt.setVersao("1.00");
		distDFeInt.setTpAmb(TipoAmbient.PRODUCAO.getAmbient());
		distDFeInt.setCUFAutor("41");
		distDFeInt.setCNPJ(certificateKeystore.getCnpj());
		distDfeCteFactory.setUltNsu(certificateKeystore.getNsuToFetch());
	}

}
