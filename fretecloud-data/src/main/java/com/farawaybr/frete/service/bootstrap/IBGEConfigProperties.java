package com.farawaybr.frete.service.bootstrap;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "ibge")
@Component
public class IBGEConfigProperties {

	@NestedConfigurationProperty
	private EstadoProperties estadoProperties;

	public EstadoProperties getEstadoProperties() {
		return estadoProperties;
	}

	public void setEstado(EstadoProperties es) {
		this.estadoProperties = es;
	}

	@ConfigurationProperties(prefix = "estado")
	public static class EstadoProperties {

		private String url;

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}
	}
}
