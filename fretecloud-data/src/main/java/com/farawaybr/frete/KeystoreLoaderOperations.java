package com.farawaybr.frete;

import java.security.KeyStore;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;

public interface KeystoreLoaderOperations {

	KeyStore loadKeystore()throws Exception;

	KeyManagerFactory loadKeyManager()throws Exception;
	
	KeyManager[] keysManager()throws Exception;
}
