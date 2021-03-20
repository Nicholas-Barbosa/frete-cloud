package com.farawaybr.frete.domain.certificate;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.SecureRandom;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "certificate")
public final class CertificateSslManager {

	private String keyStorePath = "C:\\Users\\nicho\\Downloads\\server-keystore.jks";
	private final char[] keyStore = "server-keystore-p455w0rd".toCharArray();
	private Certificate certificate;

	public CertificateSslManager() {
		// TODO Auto-generated constructor stub
	}

	public CertificateSslManager(Certificate certificate) {
		this.certificate = certificate;
	}

	public void setKeyStorePath(String keyStorePath) {
		this.keyStorePath = keyStorePath;
	}

	public void certificate(Certificate certificate) {
		this.certificate = certificate;
	}

	public CloseableHttpClient httpClient() {
		return HttpClientBuilder.create().setSSLSocketFactory(sslConnectionSocketFactory()).build();
	}

	private SSLConnectionSocketFactory sslConnectionSocketFactory() {
		return new SSLConnectionSocketFactory(sslContext());
	}

	private SSLContext sslContext() {
		try (var keyStoreInputStream = new BufferedInputStream(new FileInputStream(keyStorePath));
				var certInputStream = new BufferedInputStream(new FileInputStream(certificate.getPath()))) {

			KeyStore clientStore = KeyStore.getInstance("PKCS12");
			clientStore.load(certInputStream, certificate.getPassword());

			KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			kmf.init(clientStore, "M@ster!@#".toCharArray());

			KeyManager[] kms = kmf.getKeyManagers();

			KeyStore trustStore = KeyStore.getInstance("JKS");
			trustStore.load(keyStoreInputStream, keyStore);

			TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			tmf.init(trustStore);
			TrustManager[] tms = tmf.getTrustManagers();

			SSLContext sslcontext = SSLContext.getInstance("TLS");

			sslcontext.init(kms, tms, new SecureRandom());
			SSLContext.setDefault(sslcontext);

			return sslcontext;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return null;
	}
}
