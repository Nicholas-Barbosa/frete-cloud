package com.farawaybr.frete.sefaz.client.distDFe.cte.unmarshal;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cteDistDFeInteresseResult")
public class UnCteDistInteresseResult {
	@XmlElement(name = "retDistDFeInt", namespace = "http://www.portalfiscal.inf.br/cte")
	private UnRetDistDFeInt unRetDistDFeInt;

	public UnRetDistDFeInt getUnRetDistDFeInt() {
		return unRetDistDFeInt;
	}
}
