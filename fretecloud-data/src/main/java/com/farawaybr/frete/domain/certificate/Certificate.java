package com.farawaybr.frete.domain.certificate;

import java.util.Arrays;

public final class Certificate {

	private final String path;
	private final char[] password;
	private final String cnpj;

	public Certificate(String path, char[] password, String cnpj) {
		super();
		this.path = path;
		this.password = password;
		this.cnpj = cnpj;
	}

	String getPath() {
		return path;
	}

	char[] getPassword() {
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		return Arrays.asList(stackTrace).stream().anyMatch(x -> x.getClassName().equals("com.farawaybr.frete.domain.certificate.CertificateSslManager")) ? password : null;
	}

	public String getCnpj() {
		return cnpj;
	}

	
}
