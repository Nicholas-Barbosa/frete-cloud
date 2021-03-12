package com.farawaybr.frete.sefaz.ctedistdfe;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "cte.dist.dfe")
@Component
public class CteDistribuicaoDfeProperties {

	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
