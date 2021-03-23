package com.farawaybr.frete.domain;

import java.io.File;
import java.security.KeyStore;
import java.util.Arrays;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		result = prime * result + Arrays.hashCode(password);
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CertificateKeystore other = (CertificateKeystore) obj;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		if (!Arrays.equals(password, other.password))
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		return true;

	}

	@Override
	public String toString() {
		return "CertificateKeystore [ cnpj=" + cnpj + "]";
	}

	public final class KeystoreLoader implements KeystoreLoaderOperations {

		@Override
		public KeyStore loadKeystore() throws Exception {
			return KeyStore.getInstance(new File(path), password);

		}

		public KeyManagerFactory loadKeyManager() throws Exception {
			KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			kmf.init(loadKeystore(), password);
			return kmf;

		}

		@Override
		public KeyManager[] keysManager() throws Exception {
			return loadKeyManager().getKeyManagers();
		}

	}
}
