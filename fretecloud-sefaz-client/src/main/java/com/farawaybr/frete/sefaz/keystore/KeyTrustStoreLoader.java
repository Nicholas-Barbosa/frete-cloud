package com.farawaybr.frete.sefaz.keystore;

import java.io.File;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import org.springframework.stereotype.Component;

import com.farawaybr.frete.sefaz.properties.SefazProperties;

@Component
public class KeyTrustStoreLoader implements KeyTrustStoreLoaderOperations {

	private final SefazProperties sefazTrustConfigHolder;

	public KeyTrustStoreLoader(SefazProperties sefazTrustConfigHolder) {
		super();
		this.sefazTrustConfigHolder = sefazTrustConfigHolder;
	}

	/**
	 * Loads the specific path with password
	 * 
	 * @param path     -> Keystore path to load.
	 * @param password -> Keystore password that will be loaded.
	 * 
	 * @throws NoSuchProviderException
	 * @throws KeyStoreException
	 * @throws IOException
	 * @throws CertificateException
	 * @throws NoSuchAlgorithmException
	 */
	@Override
	public KeyStore load(String path, char[] password) throws KeyStoreException, NoSuchProviderException,
			NoSuchAlgorithmException, CertificateException, IOException {
		// TODO Auto-generated method stub
		return KeyStore.getInstance(new File(path), password);
	}

	/**
	 * Loads the default sefaz truststore defined in application properties file.
	 * 
	 * @throws NoSuchProviderException
	 * @throws KeyStoreException
	 */
	@Override
	public KeyStore load() throws KeyStoreException, NoSuchProviderException, NoSuchAlgorithmException,
			CertificateException, IOException {
		return load(sefazTrustConfigHolder.getTruststore().getPath(),
				sefazTrustConfigHolder.getTruststore().getPassword().toCharArray());
	}

	/**
	 * Loads the default truststore calling load() and return an array of
	 * trustManager.
	 * 
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws KeyStoreException
	 * @throws IOException
	 * @throws CertificateException
	 */
	@Override
	public TrustManager[] trustManager() throws NoSuchAlgorithmException, KeyStoreException, NoSuchProviderException,
			CertificateException, IOException {

		TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		tmf.init(load());

		return tmf.getTrustManagers();
	}

	/**
	 * Load trustManager for specific keystore.
	 */
	@Override
	public TrustManager[] trustManager(String path, char[] password) throws NoSuchAlgorithmException, KeyStoreException,
			NoSuchProviderException, CertificateException, IOException {

		TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		tmf.init(load(path, password));

		return tmf.getTrustManagers();
	}
}
