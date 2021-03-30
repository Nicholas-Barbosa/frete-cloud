package com.farawaybr.frete.sefaz.client.distDFe.cte.unmarshal.decompressed;

import com.farawaybr.frete.domain.CertificateKeystore;

public class RetDistDFeIntDecompressed {

	private LoteDistDFeIntDecompressed loteDistDFeIntDecompressedl;

	private CertificateKeystore certificateKeystore;

	private Integer ibgeStateId;

	public RetDistDFeIntDecompressed(LoteDistDFeIntDecompressed loteDistDFeIntDecompressedl,
			CertificateKeystore certificateKeystore, Integer ibgeStateId) {
		super();
		this.loteDistDFeIntDecompressedl = loteDistDFeIntDecompressedl;
		this.certificateKeystore = certificateKeystore;
		this.ibgeStateId = ibgeStateId;
	}

	public LoteDistDFeIntDecompressed getLoteDistDFeIntDecompressedl() {
		return loteDistDFeIntDecompressedl;
	}

	public CertificateKeystore getCertificateKeystore() {
		return certificateKeystore;
	}

	public Integer getIbgeStateId() {
		return ibgeStateId;
	}
}
