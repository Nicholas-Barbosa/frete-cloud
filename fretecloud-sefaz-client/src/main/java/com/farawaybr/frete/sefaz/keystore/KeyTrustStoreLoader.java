package com.farawaybr.frete.sefaz.keystore;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchProviderException;

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
	 */
	@Override
	public KeyStore load(String path, String senha) throws KeyStoreException, NoSuchProviderException {
		// TODO Auto-generated method stub
		return KeyStore.getInstance(path, senha);
	}

	/**
	 * Loads the default sefaz truststore defined in application properties file.
	 * 
	 * @throws NoSuchProviderException
	 * @throws KeyStoreException
	 */
	@Override
	public KeyStore load() throws KeyStoreException, NoSuchProviderException {
		return load(sefazTrustConfigHolder.getTruststore().getPath(),
				sefazTrustConfigHolder.getTruststore().getPassword());
	}
}
