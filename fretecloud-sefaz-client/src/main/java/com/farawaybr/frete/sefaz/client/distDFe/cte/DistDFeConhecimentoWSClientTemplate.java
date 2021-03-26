package com.farawaybr.frete.sefaz.client.distDFe.cte;

import org.springframework.stereotype.Component;

import com.farawaybr.frete.repository.CertificateKeystoreRepository;

@Component
public class DistDFeConhecimentoWSClientTemplate {

	private final CertificateKeystoreRepository certificateRepository;

	private final DistDFeConhecimentoWSClient distDFeCteClient;

	public DistDFeConhecimentoWSClientTemplate(CertificateKeystoreRepository certificateRepository,
			DistDFeConhecimentoWSClient distDFeCteClient) {
		super();
		this.certificateRepository = certificateRepository;
		this.distDFeCteClient = distDFeCteClient;
	}

}
