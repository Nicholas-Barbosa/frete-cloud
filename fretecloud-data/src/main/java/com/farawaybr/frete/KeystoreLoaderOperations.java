package com.farawaybr.frete;

import java.security.KeyStore;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;

public interface KeystoreLoaderOperations {

	KeyStore load();

	KeyManagerFactory loadKeyManager();
	
	KeyManager[] keysManager();
}
