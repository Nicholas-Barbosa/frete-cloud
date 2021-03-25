package com.farawaybr.frete.sefaz.client.distDFe.cte;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import com.farawaybr.frete.domain.CertificateKeystore;
import com.farawaybr.frete.sefaz.client.distDFe.cte.unmarshaller.UnCteDistResponse;

public interface DistDFeConhecimentoWSOperations {

	public void setDefaultCertificateKeystore(CertificateKeystore certificateKeystore) throws UnrecoverableKeyException,
			NoSuchAlgorithmException, KeyStoreException, CertificateException, NoSuchProviderException, IOException;

	public UnCteDistResponse sendAndReceive() throws KeyManagementException, NoSuchAlgorithmException;

	public void sendAndReceiveFull() throws KeyManagementException, NoSuchAlgorithmException;
}
