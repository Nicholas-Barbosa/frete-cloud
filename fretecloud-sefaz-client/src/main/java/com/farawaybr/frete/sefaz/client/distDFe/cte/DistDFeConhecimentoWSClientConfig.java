package com.farawaybr.frete.sefaz.client.distDFe.cte;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.farawaybr.frete.sefaz.base64.Base64Service;
import com.farawaybr.frete.sefaz.gzip.GzipService;
import com.farawaybr.frete.sefaz.keystore.KeyTrustStoreLoader;
import com.farawaybr.frete.sefaz.properties.SefazProperties;

@Configuration
public class DistDFeConhecimentoWSClientConfig {

	private final SefazProperties sefazProperties;
	private final KeyTrustStoreLoader keytrustloader;
	private final DistDFeCteRequestObjectsFactory distDFeCteRequestObjectsFactory;
	private final GzipService gzipService;
	private final Base64Service base64Service;

	public DistDFeConhecimentoWSClientConfig(SefazProperties sefazProperties, KeyTrustStoreLoader keytrustloader,
			DistDFeCteRequestObjectsFactory distDFeCteRequestObjectsFactory, GzipService gzipService,
			Base64Service base64Service) {
		super();
		this.sefazProperties = sefazProperties;
		this.keytrustloader = keytrustloader;
		this.distDFeCteRequestObjectsFactory = distDFeCteRequestObjectsFactory;
		this.gzipService = gzipService;
		this.base64Service = base64Service;
	}

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// this package must match the package in the <generatePackage> specified in
		// pom.xml
		marshaller.setContextPaths("com.example.consumingwebservice.wsdl", "br.inf.portalfiscal.cte");
		return marshaller;
	}

	public Jaxb2Marshaller unmarshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// this package must match the package in the <generatePackage> specified in
		// pom.xml
		marshaller.setContextPaths("com.farawaybr.frete.sefaz.client.distDFe.cte.unmarshal");
		return marshaller;
	}

	@Bean
	public DistDFeConhecimentoWSClient countryClient(Jaxb2Marshaller marshaller) {
		DistDFeConhecimentoWSClient client = new DistDFeConhecimentoWSClient(sefazProperties, keytrustloader,
				distDFeCteRequestObjectsFactory, gzipService, base64Service);
		client.setDefaultUri("https://www1.cte.fazenda.gov.br/CTeDistribuicaoDFe/CTeDistribuicaoDFe.asmx");
		client.setMarshaller(marshaller());
		client.setUnmarshaller(unmarshaller());
		return client;
	}
}
