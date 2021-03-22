package com.farawaybr.frete.sefaz;

import java.security.KeyStore;

import org.springframework.stereotype.Component;

import com.farawaybr.frete.sefaz.properties.SefazProperties;

@Component
public class KeyTrustStoreLoader implements KeyTrustStoreLoaderOperations {

	private final SefazProperties sefazTrustConfigHolder;

	public KeyTrustStoreLoader(SefazProperties sefazTrustConfigHolder) {
		super();
		this.sefazTrustConfigHolder = sefazTrustConfigHolder;
	}

	@Override
	public void load(KeyStore keyStore) {
		// TODO Auto-generated method stub

	}

	@Override
	public KeyStore load(String path, String senha) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Loads the default sefaz trustore defined in application properties file.
	 */
	@Override
	public KeyStore load() {

		return null;
	}
}
