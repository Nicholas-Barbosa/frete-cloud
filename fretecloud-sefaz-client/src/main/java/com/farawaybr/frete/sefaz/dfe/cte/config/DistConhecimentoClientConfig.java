package com.farawaybr.frete.sefaz.dfe.cte.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.farawaybr.frete.sefaz.dfe.cte.DistConhecimentoClient;
import com.farawaybr.frete.sefaz.keystore.KeyTrustStoreLoader;
import com.farawaybr.frete.sefaz.properties.SefazProperties;

@Configuration
public class DistConhecimentoClientConfig {

	private final SefazProperties sefazProperties;
	private final KeyTrustStoreLoader keytrustloader;

	public DistConhecimentoClientConfig(SefazProperties sefazProperties, KeyTrustStoreLoader keytrustloader) {
		super();
		this.sefazProperties = sefazProperties;
		this.keytrustloader = keytrustloader;
	}

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// this package must match the package in the <generatePackage> specified in
		// pom.xml
		marshaller.setContextPath("com.example.consumingwebservice.wsdl");
		return marshaller;
	}

	@Bean
	public DistConhecimentoClient countryClient(Jaxb2Marshaller marshaller) {
		DistConhecimentoClient client = new DistConhecimentoClient(sefazProperties, keytrustloader);
		client.setDefaultUri("https://www1.cte.fazenda.gov.br/CTeDistribuicaoDFe/CTeDistribuicaoDFe.asmx");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
}
