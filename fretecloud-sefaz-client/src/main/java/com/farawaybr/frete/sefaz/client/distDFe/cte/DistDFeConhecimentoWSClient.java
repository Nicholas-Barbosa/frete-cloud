
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
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

import com.farawaybr.frete.domain.CertificateKeystore;
import com.farawaybr.frete.domain.State;
import com.farawaybr.frete.sefaz.client.distDFe.cte.deserilize.DistDFeConhecimentoWSDeserializer;
import com.farawaybr.frete.sefaz.client.distDFe.cte.unmarshal.TipoAmbient;
import com.farawaybr.frete.sefaz.client.distDFe.cte.unmarshal.UnCteDistResponse;
import com.farawaybr.frete.sefaz.client.distDFe.cte.unmarshal.UnRetDistDFeInt;
import com.farawaybr.frete.sefaz.client.distDFe.cte.unmarshal.decompressed.RetDistDFeIntDecompressed;
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

	private final DistDFeConhecimentoWSDeserializer distDFeConhecimentoDeserializer;

	public DistDFeConhecimentoWSClient(SefazProperties sefazProperties, KeyTrustStoreLoader keyTrustLoader,
			DistDFeCteRequestObjectsFactory distDfeCteFactory,
			DistDFeConhecimentoWSDeserializer distDFeConhecimentoDeserializer) {
		super();
		this.sefazProperties = sefazProperties;
		this.keyTrustLoader = keyTrustLoader;
		this.distDfeCteFactory = distDfeCteFactory;
		this.httpClientFactory = CloseableHttpClientSslFactory.create();
		this.distDFeConhecimentoDeserializer = distDFeConhecimentoDeserializer;
	}

	/**
	 * Set certificate containing keystore for mutual authentication with sefaz, and
	 * create a WebServiceMessageSender object using certificate keystore and cte
	 * server truststore. The resulting message object will be wrapped in
	 * WebServiceTemplate setMessageSender().
	 * 
	 * The provided certificate will be set to DistDFeConhecimentoDeserializer to
	 * create a RetDistDFeIntDecompressed object.
	 * 
	 * @param certificateKeystore
	 * @throws IOException
	 * @throws NoSuchProviderException
	 * @throws CertificateException
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws UnrecoverableKeyException
	 * @throws KeyManagementException
	 */
	@Override
	public void setDefaultCertificateKeystore(CertificateKeystore certificateKeystore)
			throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, CertificateException,
			NoSuchProviderException, IOException, KeyManagementException {
		log.info("Setting keystore and truststore for mutual authentication...");
		this.certificateKeystore = certificateKeystore;

		httpClientFactory.setKeyManager(certificateKeystore.new KeystoreLoader().keysManager())
				.setTrustManager(keyTrustLoader.trustManager());
		createWebServiceMessageSender();

		distDFeConhecimentoDeserializer.setDefaultCertificate(certificateKeystore);
	}

	/**
	 * Request ctes to sefaz.
	 * 
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	@Override
	public List<RetDistDFeIntDecompressed> sendAndReceive() {
		List<RetDistDFeIntDecompressed> retsDecompressed = new CopyOnWriteArrayList<>();
		certificateKeystore.getStatesToSearch().parallelStream().forEach(stateToSearch -> {
			State state = stateToSearch.getState();

			DistDFeInt distDFeInt = distDfeCteFactory.getRequestObjectInstance();

			String status = null;
			String ultNSU = stateToSearch.getLastNSU();
			final List<UnRetDistDFeInt> retsDists = new ArrayList<>();

			do {
				log.info("Sending request to " + getDefaultUri() + " with NSU # " + ultNSU + " for state of "
						+ state.getNome());

				setDistDFeIntAttributes(distDFeInt, certificateKeystore, state.getIbgeId(), ultNSU);

				UnCteDistResponse response = (UnCteDistResponse) getWebServiceTemplate()

						.marshalSendAndReceive(distDfeCteFactory.getAllRequestObject(), message -> {
							SoapMessage soapMessage = (SoapMessage) message;
							soapMessage.setSoapAction(
									sefazProperties.getDocumentoFiscalEletronico().getConhecimento().getSoapAction());
						});
				status = response.getUnCteDistInteresseResult().getUnRetDistDFeInt().getcStat();
				String xMotivo = response.getUnCteDistInteresseResult().getUnRetDistDFeInt().getxMotivo();
				ultNSU = response.getUnCteDistInteresseResult().getUnRetDistDFeInt().getUltNSU();

				retsDists.add(response.getUnCteDistInteresseResult().getUnRetDistDFeInt());
				log.info("Got reponse from " + getDefaultUri() + ", for state of: " + state.getNome() + " with status: "
						+ status + " xMotivo: " + xMotivo);
				status = "137";
			} while (status.equals("138"));
			log.info("All ctes were obtained for the certificate " + certificateKeystore.getCnpj());
			distDFeConhecimentoDeserializer.deserialize(retsDists.get(0), state.getIbgeId());
		});

		return retsDecompressed;
	}

	private void setDistDFeIntAttributes(DistDFeInt distDFeInt, CertificateKeystore certificateKeystore,
			Integer cUFAutor, String ultNSU) {
		log.debug("Setting attributes to request object...");
		distDFeInt.setVersao("1.00");
		distDFeInt.setTpAmb(TipoAmbient.PRODUCAO.getAmbient());
		distDFeInt.setCUFAutor(cUFAutor.toString());
		distDFeInt.setCNPJ(certificateKeystore.getCnpj());
		distDfeCteFactory.setUltNsu(ultNSU);
	}

	private void createWebServiceMessageSender() throws KeyManagementException, NoSuchAlgorithmException {
		log.info(
				"Creating WebServiceMessageSender object and setting http client using keystore and truststore from certificate...");

		WebServiceTemplate webServiceTemplate = getWebServiceTemplate();
		HttpComponentsMessageSender httpComponentsMessageSender = new HttpComponentsMessageSender();

		httpComponentsMessageSender.setHttpClient(httpClientFactory.getCloseableHttpClient());

		webServiceTemplate.setMessageSender(httpComponentsMessageSender);

	}

}
