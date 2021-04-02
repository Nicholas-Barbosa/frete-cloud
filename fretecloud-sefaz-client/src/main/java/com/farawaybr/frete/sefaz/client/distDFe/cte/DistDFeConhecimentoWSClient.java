
package com.farawaybr.frete.sefaz.client.distDFe.cte;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

import com.farawaybr.frete.domain.CertificateKeystore;
import com.farawaybr.frete.domain.State;
import com.farawaybr.frete.domain.StateToSearchDistFeCte;
import com.farawaybr.frete.sefaz.client.distDFe.cte.DistDFeCteRequestObjectsFactory.DistDFeIntAttributesFactory;
import com.farawaybr.frete.sefaz.client.distDFe.cte.deserilize.DistDFeConhecimentoWSDeserializer;
import com.farawaybr.frete.sefaz.client.distDFe.cte.response.DistDFeCteDocDecompressed;
import com.farawaybr.frete.sefaz.client.distDFe.cte.response.DistDFeCteResponse;
import com.farawaybr.frete.sefaz.client.distDFe.cte.response.DistDFeCteResponseByEstado;
import com.farawaybr.frete.sefaz.client.distDFe.cte.unmarshal.TipoAmbient;
import com.farawaybr.frete.sefaz.client.distDFe.cte.unmarshal.UnCteDistResponse;
import com.farawaybr.frete.sefaz.httpclient.CloseableHttpClientSslFactory;
import com.farawaybr.frete.sefaz.keystore.KeyTrustStoreLoader;
import com.farawaybr.frete.sefaz.properties.SefazProperties;

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

	}

	/**
	 * Request ctes to sefaz.
	 * 
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * @return DistDFeCteResponse
	 */
	@Override
	public DistDFeCteResponse sendAndReceive() {
		List<DistDFeCteResponseByEstado> estadosDistDFeResponses = new CopyOnWriteArrayList<>();

		List<DistDFeCteDocDecompressed> docsDecompresseds = new CopyOnWriteArrayList<>();

		Set<StateToSearchDistFeCte> statesToSearch = certificateKeystore.getStatesToSearch();

		distDfeCteFactory.registerKeys(statesToSearch.parallelStream().map(s -> s.getState().getIbgeId())
				.collect(CopyOnWriteArraySet::new, Set::add, Set::addAll));

		statesToSearch.parallelStream().forEach(stateToSearch -> {
			State state = stateToSearch.getState();

			String status = null;
			String ultNSU = stateToSearch.getLastNSU();

			do {
				log.info("Sending request to " + getDefaultUri() + " with NSU # " + ultNSU + " for state of "
						+ state.getNome());

				distDfeCteFactory.setDistDFeIntAttributes(DistDFeIntAttributesFactory.instance()
						.ambient(TipoAmbient.PRODUCAO).ufAutor(state.getIbgeId().toString())
						.cnpj(certificateKeystore.getCnpj()).distNSU(ultNSU).versao("1.00"), state.getIbgeId());

				UnCteDistResponse response = (UnCteDistResponse) getWebServiceTemplate()

						.marshalSendAndReceive(distDfeCteFactory.getRequestObject(state.getIbgeId()), message -> {
							SoapMessage soapMessage = (SoapMessage) message;
							soapMessage.setSoapAction(
									sefazProperties.getDocumentoFiscalEletronico().getConhecimento().getSoapAction());
							soapMessage.writeTo(new FileOutputStream(
									"C:\\Users\\nicho\\OneDrive\\Documentos\\teste soapmessage\\dist.xml"));
						});
				status = response.getUnCteDistInteresseResult().getUnRetDistDFeInt().getcStat();
				String xMotivo = response.getUnCteDistInteresseResult().getUnRetDistDFeInt().getxMotivo();
				ultNSU = response.getUnCteDistInteresseResult().getUnRetDistDFeInt().getUltNSU();

				if (status.equals("138")) {
					docsDecompresseds.addAll(distDFeConhecimentoDeserializer
							.deserialize(response.getUnCteDistInteresseResult().getUnRetDistDFeInt()));
				}

				log.info("Got reponse from " + getDefaultUri() + ", for state of: " + state.getNome() + " with status: "
						+ status + " xMotivo: " + xMotivo);
				status = "137";
			} while (status.equals("138"));
			estadosDistDFeResponses
					.add(new DistDFeCteResponseByEstado(stateToSearch.getState().getIbgeId(), docsDecompresseds));
			log.info("All ctes were obtained for the certificate " + certificateKeystore.getCnpj());
		});

		return new DistDFeCteResponse(certificateKeystore, estadosDistDFeResponses);
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
