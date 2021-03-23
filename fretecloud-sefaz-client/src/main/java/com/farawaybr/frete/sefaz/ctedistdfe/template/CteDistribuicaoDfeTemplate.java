package com.farawaybr.frete.sefaz.ctedistdfe.template;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.farawaybr.frete.domain.CertificateKeystore;
import com.farawaybr.frete.sefaz.properties.SefazProperties;

@Component
public class CteDistribuicaoDfeTemplate {

	private final SefazProperties sefazProperties;

	private Set<CertificateKeystore> certificates;

	public CteDistribuicaoDfeTemplate(SefazProperties sefazProperties, Set<CertificateKeystore> certificates) {
		super();
		this.sefazProperties = sefazProperties;
		this.certificates = certificates;
	}

	public void fetch(CertificateKeystore certificate) {

	}

	public void fetch() {
		certificates.parallelStream().forEach(this::fetch);
	}

	public CteDistribuicaoDfeTemplate certificates(Set<CertificateKeystore> certificates) {
		certificates = new HashSet<>(certificates);
		return this;
	}
}
