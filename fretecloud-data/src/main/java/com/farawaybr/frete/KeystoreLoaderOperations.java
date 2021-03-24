package com.farawaybr.frete;

import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;

public interface KeystoreLoaderOperations {

	KeyStore loadKeystore() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException;

	KeyManagerFactory loadKeyManager() throws NoSuchAlgorithmException, UnrecoverableKeyException, KeyStoreException,
			CertificateException, IOException;

	KeyManager[] keysManager() throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException,
			CertificateException, IOException;

	TrustManager[] trustManager() throws Exception;

}
