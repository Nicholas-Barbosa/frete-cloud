package com.farawaybr.frete.sefaz.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "conhecimento")
public class ConhecimentoProperties {

	private String url;

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
}
