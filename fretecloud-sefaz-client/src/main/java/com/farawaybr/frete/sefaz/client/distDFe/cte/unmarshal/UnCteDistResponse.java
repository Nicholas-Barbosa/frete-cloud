package com.farawaybr.frete.sefaz.client.distDFe.cte.unmarshal;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cteDistDFeInteresseResponse", namespace = "http://www.portalfiscal.inf.br/cte/wsdl/CTeDistribuicaoDFe")
public class UnCteDistResponse {

	@XmlElement(name = "cteDistDFeInteresseResult",namespace = "http://www.portalfiscal.inf.br/cte/wsdl/CTeDistribuicaoDFe")
	private UnCteDistInteresseResult unCteDistInteresseResult;

	public UnCteDistInteresseResult getUnCteDistInteresseResult() {
		return unCteDistInteresseResult;
	}
}
