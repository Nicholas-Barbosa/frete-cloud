package com.farawaybr.frete.domain;

import java.io.File;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;

import com.farawaybr.frete.KeystoreLoaderOperations;

public final class CertificateKeystore {

	private final String path;
	private final char[] password;
	private final String cnpj;

	public CertificateKeystore(String path, char[] password, String cnpj) {
		super();
		this.path = path;
		this.password = password;
		this.cnpj = cnpj;
	}

	public String getCnpj() {
		return cnpj;
	}

	public final class KeystoreLoader implements KeystoreLoaderOperations {

		@Override
		public KeyStore load() {
			try {
				return KeyStore.getInstance(new File(path), password);
			} catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}

		public KeyManagerFactory loadKeyManager() {
			try {
				KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
				kmf.init(load(), password);
				return kmf;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;

		}

		@Override
		public KeyManager[] keysManager() {
			return loadKeyManager().getKeyManagers();
		}

	}
}
