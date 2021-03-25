
package com.farawaybr.frete.sefaz.client.distDFe.cte;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

import com.farawaybr.frete.domain.CertificateKeystore;
import com.farawaybr.frete.sefaz.client.distDFe.cte.unmarshal.TipoAmbient;
import com.farawaybr.frete.sefaz.client.distDFe.cte.unmarshal.UnCteDistResponse;
import com.farawaybr.frete.sefaz.httpclient.CloseableHttpClientSslFactory;
import com.farawaybr.frete.sefaz.keystore.KeyTrustStoreLoader;
import com.farawaybr.frete.sefaz.properties.SefazProperties;

import br.inf.portalfiscal.cte.DistDFeInt;

public class DistDFeConhecimentoWSClient extends WebServiceGatewaySupport implements DistDFeConhecimentoWSOperations {

	private final Logger log = LoggerFactory.getLogger(DistDFeConhecimentoWSClient.class);

	private final SefazProperties sefazProperties;

	private final KeyTrustStoreLoader keyTrustLoader;

	private final DistDFeCteRequestObjectsFactory distDfeCteFactory;

	private final CloseableHttpClientSslFactory httpClientFactory;

	private CertificateKeystore certificateKeystore;

	private String ultNSU;

	public DistDFeConhecimentoWSClient(SefazProperties sefazProperties, KeyTrustStoreLoader keyTrustLoader,
			DistDFeCteRequestObjectsFactory distDfeCteFactory) {
		super();
		this.sefazProperties = sefazProperties;
		this.keyTrustLoader = keyTrustLoader;
		this.distDfeCteFactory = distDfeCteFactory;
		this.httpClientFactory = CloseableHttpClientSslFactory.create();
	}

	/**
	 * Set certificate containing keystore for mutual authentication with sefaz. And
	 * set ultNSU field according to nsu of the specified certificate.
	 * 
	 * @param certificateKeystore
	 * @throws IOException
	 * @throws NoSuchProviderException
	 * @throws CertificateException
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws UnrecoverableKeyException
	 */
	@Override
	public void setDefaultCertificateKeystore(CertificateKeystore certificateKeystore) throws UnrecoverableKeyException,
			NoSuchAlgorithmException, KeyStoreException, CertificateException, NoSuchProviderException, IOException {
		log.info("Setting keystore and truststore for mutual authentication...");
		this.certificateKeystore = certificateKeystore;

		httpClientFactory.setKeyManager(certificateKeystore.new KeystoreLoader().keysManager())
				.setTrustManager(keyTrustLoader.trustManager());

		this.ultNSU = certificateKeystore.getNsuToFetch();
	}

	/**
	 * Call unzipSendAndReceiveFull() until max NSU is reached.When max NSU is
	 * reached or sendAndRecive() return null, docZip in loteDistDFeInt collection
	 * will be
	 * 
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	@Override
	public void unzipSendAndReceiveFull() throws KeyManagementException, NoSuchAlgorithmException {
		String status = null;
		List<UnCteDistResponse> zippedResponses = new ArrayList<>();
		do {

			UnCteDistResponse response = sendAndReceive();
			status = response == null ? null : response.getUnCteDistInteresseResult().getUnRetDistDFeInt().getcStat();
			if (response != null) {
				zippedResponses.add(response);
			}
			zippedResponses.add(response);
		} while (status != null);
		log.info("Finished! All ctes were obtained!");
		
	}

	/**
	 * Request ctes to sefaz.
	 * 
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	@Override
	public UnCteDistResponse sendAndReceive() throws KeyManagementException, NoSuchAlgorithmException {
		log.info("Sending request to " + getDefaultUri() + " with NSU # " + ultNSU);

		DistDFeInt distDFeInt = distDfeCteFactory.getRequestObjectInstance();
		setDistDFeIntAttributes(distDFeInt, certificateKeystore);

		WebServiceTemplate webServiceTemplate = getWebServiceTemplate();

		HttpComponentsMessageSender httpComponentsMessageSender = new HttpComponentsMessageSender();

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
		String ultNSU = response.getUnCteDistInteresseResult().getUnRetDistDFeInt().getUltNSU();

		log.info("Got reponse from " + getDefaultUri() + ", status: " + status + " xMotivo: " + xMotivo);

		return checkStatusAndChangeUltNsu(status, ultNSU) ? response : null;
	}

	private void setDistDFeIntAttributes(DistDFeInt distDFeInt, CertificateKeystore certificateKeystore) {
		log.debug("Setting attributes to request object...");
		distDFeInt.setVersao("1.00");
		distDFeInt.setTpAmb(TipoAmbient.PRODUCAO.getAmbient());
		distDFeInt.setCUFAutor("41");
		distDFeInt.setCNPJ(certificateKeystore.getCnpj());
		distDfeCteFactory.setUltNsu(this.ultNSU);
	}

	private boolean checkStatusAndChangeUltNsu(String status, String nsu) {
		boolean statusOk = status.equals("138") || status.equals("137");
		this.ultNSU = statusOk ? nsu : ultNSU;

		return statusOk;
	}

	public void setUltNSU(String ultNSU) {
		this.ultNSU = ultNSU;
	}

	public String getUltNSU() {
		return ultNSU;
	}

}
