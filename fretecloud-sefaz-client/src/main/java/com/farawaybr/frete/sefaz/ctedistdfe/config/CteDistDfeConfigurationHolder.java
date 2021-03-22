package com.farawaybr.frete.sefaz.ctedistdfe.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "dist.dfe.cte")
public class CteDistDfeConfigurationHolder {

	private String soapUrl;

	public String getSoapUrl() {
		return soapUrl;
	}

	public void setSoapUrl(String soapUrl) {
		this.soapUrl = soapUrl;
	}
}
