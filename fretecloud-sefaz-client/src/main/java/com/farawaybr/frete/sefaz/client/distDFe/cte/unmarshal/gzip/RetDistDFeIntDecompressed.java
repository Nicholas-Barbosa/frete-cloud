package com.farawaybr.frete.sefaz.client.distDFe.cte.unmarshal.gzip;

import com.farawaybr.frete.domain.CertificateKeystore;

public class RetDistDFeIntDecompressed {

	private LoteDistDFeIntDecompressed loteDistDFeIntDecompressedl;

	private CertificateKeystore certificateKeystore;

	private Integer ufAutor;

	public RetDistDFeIntDecompressed(LoteDistDFeIntDecompressed loteDistDFeIntDecompressedl,
			CertificateKeystore certificateKeystore, Integer ufAutor) {
		super();
		this.loteDistDFeIntDecompressedl = loteDistDFeIntDecompressedl;
		this.certificateKeystore = certificateKeystore;
		this.ufAutor = ufAutor;
	}

	public LoteDistDFeIntDecompressed getLoteDistDFeIntDecompressedl() {
		return loteDistDFeIntDecompressedl;
	}

	public CertificateKeystore getCertificateKeystore() {
		return certificateKeystore;
	}

	public Integer getUfAutor() {
		return ufAutor;
	}
}
