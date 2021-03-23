package com.farawaybr.frete.sefaz.keystore;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchProviderException;

public interface KeyTrustStoreLoaderOperations {


	KeyStore load(String path, String senha)throws KeyStoreException, NoSuchProviderException;
	
	KeyStore load()throws KeyStoreException, NoSuchProviderException;
}
