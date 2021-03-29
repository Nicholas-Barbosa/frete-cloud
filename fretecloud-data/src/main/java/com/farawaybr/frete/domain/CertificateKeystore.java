package com.farawaybr.frete.domain;

import java.io.File;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.farawaybr.frete.KeystoreLoaderOperations;

@Entity
public final class CertificateKeystore extends SuperEntity {

	private final String path;
	private final char[] password;
	private final String cnpj;

	@OneToMany
	private final Set<StateToSearchDistFeCte> statesToSearch;

	public CertificateKeystore(Long id, String path, char[] password, String cnpj,
			Set<StateToSearchDistFeCte> statesToSearch) {
		super(id);
		this.path = path;
		this.password = password;
		this.cnpj = cnpj;
		this.statesToSearch = new HashSet<>(statesToSearch);
	}

	public String getCnpj() {
		return cnpj;
	}

	public Set<StateToSearchDistFeCte> getStatesToSearch() {
		return new HashSet<>(statesToSearch);
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

		/**
		 * Load the keystore of the current instance of CertificateKeystore.
		 * 
		 * @throws IOException
		 * @throws CertificateException
		 * @throws NoSuchAlgorithmException
		 * @throws KeyStoreException
		 */
		@Override
		public KeyStore loadKeystore()
				throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
			return KeyStore.getInstance(new File(path), password);

		}

		/**
		 * Load KeyManagerFactory for the current instance of CertificateKeystore.
		 * 
		 * @throws NoSuchAlgorithmException
		 * @throws IOException
		 * @throws CertificateException
		 * @throws KeyStoreException
		 * @throws UnrecoverableKeyException
		 */
		@Override
		public KeyManagerFactory loadKeyManager() throws NoSuchAlgorithmException, UnrecoverableKeyException,
				KeyStoreException, CertificateException, IOException {
			KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			kmf.init(loadKeystore(), password);
			return kmf;

		}

		/**
		 * Get a keyManager for the current CertificateKeystore instance.
		 * 
		 * @throws IOException
		 * @throws CertificateException
		 * @throws KeyStoreException
		 * @throws NoSuchAlgorithmException
		 * @throws UnrecoverableKeyException
		 */
		@Override
		public KeyManager[] keysManager() throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException,
				CertificateException, IOException {
			return loadKeyManager().getKeyManagers();
		}

		/**
		 * return a trustManager[] for the current instance of CertificateKeystore
		 * 
		 * @throws KeyStoreException
		 * @throws Exception
		 */
		@Override
		public TrustManager[] trustManager() throws KeyStoreException, Exception {

			TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			tmf.init(loadKeystore());

			return tmf.getTrustManagers();
		}

	}
}
