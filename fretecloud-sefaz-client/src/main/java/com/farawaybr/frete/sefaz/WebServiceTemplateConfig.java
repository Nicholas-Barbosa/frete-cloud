package com.farawaybr.frete.sefaz;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.client.core.WebServiceTemplate;

@Configuration
public class WebServiceTemplateConfig {
//
//	@Bean
//	public Jaxb2Marshaller marshaller() {
//		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
//		// this package must match the package in the <generatePackage> specified in
//		// pom.xml
//		marshaller.setContextPath("com.example.consumingwebservice.wsdl");
//		return marshaller;
//	}

	@Bean
	public WebServiceTemplate webServiceTemplate() {
		return new WebServiceTemplate();
	}
}
