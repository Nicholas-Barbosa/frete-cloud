package com.farawaybr.frete.sefaz.client.distDFe.cte.unmarshal;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "retDistDFeInt")
public class UnRetDistDFeInt {

	@XmlElement(namespace = "http://www.portalfiscal.inf.br/cte")
	private String tpAmb;
	@XmlElement(namespace = "http://www.portalfiscal.inf.br/cte")
	private String cStat;
	@XmlElement(namespace = "http://www.portalfiscal.inf.br/cte")
	private String xMotivo;
	@XmlElement(namespace = "http://www.portalfiscal.inf.br/cte")
	private String ultNSU;
	@XmlElement(namespace = "http://www.portalfiscal.inf.br/cte")
	private String maxNSU;

	public TipoAmbient getTpAmb() {
		return TipoAmbient.valueOfFromNumeric(tpAmb);
	}

	public String getcStat() {
		return cStat;
	}

	public String getxMotivo() {
		return xMotivo;
	}

	public String getUltNSU() {
		return ultNSU;
	}

	public String getMaxNSU() {
		return maxNSU;
	}

}
