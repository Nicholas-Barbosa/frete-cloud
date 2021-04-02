package com.farawaybr.frete.sefaz.client.distDFe.cte.response;

import java.util.ArrayList;
import java.util.List;

import com.farawaybr.frete.domain.CertificateKeystore;

public class DistDFeCteResponse {

	private CertificateKeystore certificateKeystore;
	private List<DistDFeCteResponseByEstado> estadosResponses;

	public DistDFeCteResponse(CertificateKeystore certificateKeystore,
			List<DistDFeCteResponseByEstado> estadosResponses) {
		super();
		this.certificateKeystore = certificateKeystore;
		this.estadosResponses = estadosResponses;
	}

	public CertificateKeystore getCertificateKeystore() {
		return certificateKeystore;
	}

	public List<DistDFeCteResponseByEstado> getEstadosResponses() {
		return new ArrayList<>(estadosResponses);
	}

}
