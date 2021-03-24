package com.farawaybr.frete.sefaz.ctedistdfe.template;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "retDistDFeInt", namespace = "http://www.portalfiscal.inf.br/cte")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CteDistDfeResponsePayload {

	@JacksonXmlProperty(localName = "ultNSU", namespace = "http://www.portalfiscal.inf.br/cte")
	private String ultimoNsu;

	@JacksonXmlProperty(localName = "maxNSU", namespace = "http://www.portalfiscal.inf.br/cte")
	private String nsuMaximo;

	@JacksonXmlProperty(localName = "cStat", namespace = "http://www.portalfiscal.inf.br/cte")
	private Integer status;

	@JacksonXmlProperty(localName = "xMotivo", namespace = "http://www.portalfiscal.inf.br/cte")
	private String motivo;

	public String getUltimoNsu() {
		return ultimoNsu;
	}

	public String getNsuMaximo() {
		return nsuMaximo;
	}

	public Integer getStatus() {
		return status;
	}

	public String getMotivo() {
		return motivo;
	}

}
