package com.farawaybr.frete.sefaz.ctedistdfe.template;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.HashSet;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import com.farawaybr.frete.data.xml.GenericSerialiazerDeserializer;
import com.farawaybr.frete.domain.CertificateKeystore;
import com.farawaybr.frete.sefaz.ctedistdfe.template.CteDistDfeRequestPayload.DistNsuTemplate;
import com.farawaybr.frete.sefaz.httpclient.CloseableHttpClientSslFactory;
import com.farawaybr.frete.sefaz.keystore.KeyTrustStoreLoader;
import com.farawaybr.frete.sefaz.properties.SefazProperties;

@Component
public class CteDistribuicaoDfeTemplate {

	private final Logger log = org.slf4j.LoggerFactory.getLogger(CteDistribuicaoDfeTemplate.class);

	private final SefazProperties sefazProperties;

	private final KeyTrustStoreLoader keyTrustLoader;

	private Set<CertificateKeystore> certificates;

	public CteDistribuicaoDfeTemplate(SefazProperties sefazProperties, KeyTrustStoreLoader keyTrustLoader) {
		super();
		this.sefazProperties = sefazProperties;

		this.keyTrustLoader = keyTrustLoader;
	}

	public CteDistDfeResponsePayload fetch(CertificateKeystore certificate) {
		System.out.println("fetch");
		HttpPost post = new HttpPost(sefazProperties.getDocumentoFiscalEletronico().getConhecimento().getUrl());
		CloseableHttpClientSslFactory closeaFactory = CloseableHttpClientSslFactory.create();

		try {
			closeaFactory.setKeyManager(certificate.new KeystoreLoader().keysManager())
					.setTrustManager(keyTrustLoader.trustManager());

			// Begin try with resources
			try (CloseableHttpClient httpclient = closeaFactory.getCloseableHttpClient()) {
				CteDistDfeRequestPayload requestPayload = new CteDistDfeRequestPayload("1", "41", certificate.getCnpj(),
						new DistNsuTemplate(certificate.getNsuToFetch()));

				String soapMessage = CteDistDfeSoapEnvelope.envelope(
						new GenericSerialiazerDeserializer<CteDistDfeRequestPayload>().serialize(requestPayload));

				post.setEntity(new ByteArrayEntity(soapMessage.getBytes("UTF-8")));
				post.setHeader("Content-Type", "application/soap+xml");

				CloseableHttpResponse httpresponse = httpclient.execute(post);

				HttpEntity entity = httpresponse.getEntity();
				String response = replaceTags(EntityUtils.toString(entity));

				return new GenericSerialiazerDeserializer<CteDistDfeResponsePayload>().deserialize(response,
						CteDistDfeResponsePayload.class);

			}

		} catch (KeyManagementException e) {
			e.printStackTrace();

		} catch (UnrecoverableKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	private String replaceTags(String xml) {
		return xml.replace(
				"<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">"
						+ "",
				"").replace("</soap:Envelope>", "").replace("<soap:Body>", "").replace("</soap:Body>", "")
				.replace(
						"<cteDistDFeInteresseResponse xmlns=\"http://www.portalfiscal.inf.br/cte/wsdl/CTeDistribuicaoDFe\">",
						"")
				.replace("</cteDistDFeInteresseResponse>", "").replace("<cteDistDFeInteresseResult>", "")
				.replace("</cteDistDFeInteresseResult>", "");
	}

	public void fetch() {
		certificates.parallelStream().forEach(this::fetch);
	}

	public CteDistribuicaoDfeTemplate certificates(Set<CertificateKeystore> certificates) {
		certificates = new HashSet<>(certificates);
		return this;
	}
}
