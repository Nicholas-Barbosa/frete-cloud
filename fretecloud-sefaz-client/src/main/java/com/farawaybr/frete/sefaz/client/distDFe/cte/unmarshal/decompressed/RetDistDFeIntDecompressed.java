package com.farawaybr.frete.sefaz.client.distDFe.cte.unmarshal.decompressed;

import java.util.ArrayList;
import java.util.List;

import com.farawaybr.frete.domain.CertificateKeystore;

public class RetDistDFeIntDecompressed {

	private CertificateKeystore certificateKeystore;
	private List<EstadoDistDFeResponse> estadosResponses;

	public RetDistDFeIntDecompressed(List<EstadoDistDFeResponse> estadosResponses,
			CertificateKeystore certificateKeystore) {
		super();
		this.certificateKeystore = certificateKeystore;
		this.estadosResponses = new ArrayList<>(estadosResponses);
	}

	public CertificateKeystore getCertificateKeystore() {
		return certificateKeystore;
	}

	public List<EstadoDistDFeResponse> getEstadosResponses() {
		return estadosResponses;
	}

}
