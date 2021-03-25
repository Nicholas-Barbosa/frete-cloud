package com.farawaybr.frete.sefaz.dfe.cte.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.soap.SoapVersion;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;

@Configuration
public class SaajSoapMessageConfig {
	@Bean
	public SaajSoapMessageFactory saajSoapMessageFactory() {
		SaajSoapMessageFactory mf = new SaajSoapMessageFactory();
		mf.setSoapVersion(SoapVersion.SOAP_12);
		return mf;
	}
}
