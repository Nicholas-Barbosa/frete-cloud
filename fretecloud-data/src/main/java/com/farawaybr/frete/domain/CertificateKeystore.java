package com.farawaybr.frete.domain;

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

}
