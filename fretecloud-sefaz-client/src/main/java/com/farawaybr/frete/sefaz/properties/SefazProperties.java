package com.farawaybr.frete.sefaz.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sefaz")
public class SefazProperties {

	@NestedConfigurationProperty
	private Truststore truststore;
	@NestedConfigurationProperty
	private DocumentosFiscalEletronicoProperties documentoFiscalEletronico;

	public void setTruststore(Truststore truststore) {
		this.truststore = truststore;
	}

	public Truststore getTruststore() {
		return truststore;
	}

	public DocumentosFiscalEletronicoProperties getDocumentoFiscalEletronico() {
		return documentoFiscalEletronico;
	}

	public void setDFe(DocumentosFiscalEletronicoProperties documentoFiscalEletronico) {
		this.documentoFiscalEletronico = documentoFiscalEletronico;
	}

	public static class Truststore {
		private String path;
		private String password;

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	}

	@ConfigurationProperties(prefix = "DFe")
	public static class DocumentosFiscalEletronicoProperties {

		@NestedConfigurationProperty
		private ConhecimentoProperties conhecimento;

		public ConhecimentoProperties getConhecimento() {
			return conhecimento;
		}

		public void setConhecimento(ConhecimentoProperties conhecimento) {
			this.conhecimento = conhecimento;
		}
	}
}
