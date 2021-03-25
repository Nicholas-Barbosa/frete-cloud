package com.farawaybr.frete.sefaz.dfe.cte.unmarshaller;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "retDistDFeInt")
public class UnRetDistDFeInt {

	@XmlElement(namespace = "http://www.portalfiscal.inf.br/cte")
	private String xMotivo;
	@XmlElement(namespace = "http://www.portalfiscal.inf.br/cte")
	private String cStat;

	public String getxMotivo() {
		return xMotivo;
	}

	public String getcStat() {
		return cStat;
	}
}
