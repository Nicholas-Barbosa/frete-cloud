package com.farawaybr.frete.sefaz.ctedistdfe.template;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "distDFeInt", namespace = "http://www.portalfiscal.inf.br/cte")
public class CteDistDfeRequestPayload {

	@JacksonXmlProperty(isAttribute = true)
	private String versao = "1.00";

	@JacksonXmlProperty(namespace = "http://www.portalfiscal.inf.br/cte")
	private String tpAmb;

	@JacksonXmlProperty(localName = "cUFAutor", namespace = "http://www.portalfiscal.inf.br/cte")
	private String ufAutor;

	@JacksonXmlProperty(localName = "CNPJ", namespace = "http://www.portalfiscal.inf.br/cte")
	private String cnpj;

	@JacksonXmlProperty(localName = "distNSU", namespace = "http://www.portalfiscal.inf.br/cte")
	private DistNsuTemplate distNsu;

	public CteDistDfeRequestPayload(String tpAmb, String ufAutor, String cnpj, DistNsuTemplate distNsu) {
		super();
		this.tpAmb = tpAmb;
		this.ufAutor = ufAutor;
		this.cnpj = cnpj;
		this.distNsu = distNsu;
	}

	public CteDistDfeRequestPayload() {
		// TODO Auto-generated constructor stub
	}

	public String getVersao() {
		return versao;
	}

	public String getTpAmb() {
		return tpAmb;
	}

	public String getUfAutor() {
		return ufAutor;
	}

	public String getCnpj() {
		return cnpj;
	}

	public DistNsuTemplate getDistNsu() {
		return distNsu;
	}

	public static class DistNsuTemplate {
		@JacksonXmlProperty(localName = "ultNSU", namespace = "http://www.portalfiscal.inf.br/cte")
		private String ultNsu;

		public DistNsuTemplate(String ultNsu) {
			super();
			this.ultNsu = ultNsu;
		}
	}
}
