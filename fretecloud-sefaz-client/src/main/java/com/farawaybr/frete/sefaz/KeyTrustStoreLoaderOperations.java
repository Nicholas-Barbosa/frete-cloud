package com.farawaybr.frete.sefaz;

import java.security.KeyStore;

public interface KeyTrustStoreLoaderOperations {

	void load(KeyStore keyStore);

	KeyStore load(String path, String senha);
	
	KeyStore load();
}
