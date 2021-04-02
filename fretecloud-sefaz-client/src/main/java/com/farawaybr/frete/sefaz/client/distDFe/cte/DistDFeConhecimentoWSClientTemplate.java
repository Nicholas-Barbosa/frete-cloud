package com.farawaybr.frete.sefaz.client.distDFe.cte;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.farawaybr.frete.repository.CertificateKeystoreRepository;
import com.farawaybr.frete.sefaz.client.distDFe.cte.unmarshal.decompressed.RetDistDFeIntDecompressed;

@Component
public class DistDFeConhecimentoWSClientTemplate {

	private final CertificateKeystoreRepository certificateRepository;

	private final DistDFeConhecimentoWSClient distDFeCteWSClient;

	public DistDFeConhecimentoWSClientTemplate(CertificateKeystoreRepository certificateRepository,
			DistDFeConhecimentoWSClient distDFeCteClient) {
		super();
		this.certificateRepository = certificateRepository;
		this.distDFeCteWSClient = distDFeCteClient;
	}

	public void reciveAndSave() {
		List<RetDistDFeIntDecompressed> responses = new ArrayList<>();
		certificateRepository.findAll().forEach(c -> {
			try {
				distDFeCteWSClient.setDefaultCertificateKeystore(c);

			} catch (UnrecoverableKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (KeyManagementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (KeyStoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CertificateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchProviderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

	}

}
