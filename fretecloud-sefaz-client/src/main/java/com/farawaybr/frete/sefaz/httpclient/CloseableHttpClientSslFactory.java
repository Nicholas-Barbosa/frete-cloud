package com.farawaybr.frete.sefaz.httpclient;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.ws.transport.http.HttpComponentsMessageSender.RemoveSoapHeadersInterceptor;

public class CloseableHttpClientSslFactory {

	private KeyManager[] keyManager;
	private TrustManager[] trustManager;

	public static CloseableHttpClientSslFactory create() {
		return new CloseableHttpClientSslFactory();
	}

	public CloseableHttpClientSslFactory setKeyManager(KeyManager[] keyManager) {
		this.keyManager = keyManager.clone();
		return this;
	}

	public CloseableHttpClientSslFactory setTrustManager(TrustManager[] trustManager) {
		this.trustManager = trustManager.clone();
		return this;
	}

	public CloseableHttpClient getCloseableHttpClient() throws KeyManagementException, NoSuchAlgorithmException {
		return HttpClientBuilder.create().setSSLSocketFactory(sslConnectionSocketFactory())
				.addInterceptorFirst(new RemoveSoapHeadersInterceptor()).build();
	}

	private SSLConnectionSocketFactory sslConnectionSocketFactory()
			throws KeyManagementException, NoSuchAlgorithmException {
		return new SSLConnectionSocketFactory(sslContext());
	}

	private SSLContext sslContext() throws NoSuchAlgorithmException, KeyManagementException {
		if (keyManager != null && trustManager != null) {
			SSLContext sslcontext = SSLContext.getInstance("TLS");

			sslcontext.init(keyManager, trustManager, new SecureRandom());
			SSLContext.setDefault(sslcontext);

			return sslcontext;
		}
		throw new MissingKeystoreObject("You must set keyManager and trustManager!");
	}
}
