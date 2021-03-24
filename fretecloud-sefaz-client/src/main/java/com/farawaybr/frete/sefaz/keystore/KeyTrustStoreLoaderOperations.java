package com.farawaybr.frete.sefaz.keystore;

import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;

import javax.net.ssl.TrustManager;

public interface KeyTrustStoreLoaderOperations {

	KeyStore load(String path, char[] password) throws KeyStoreException, NoSuchProviderException,
			NoSuchAlgorithmException, CertificateException, IOException;

	KeyStore load() throws NoSuchAlgorithmException, KeyStoreException, NoSuchProviderException, CertificateException,
			IOException;

	TrustManager[] trustManager() throws NoSuchAlgorithmException, KeyStoreException, NoSuchProviderException,
			CertificateException, IOException;

	TrustManager[] trustManager(String path, char[] password) throws NoSuchAlgorithmException, KeyStoreException,
			NoSuchProviderException, CertificateException, IOException;
}
