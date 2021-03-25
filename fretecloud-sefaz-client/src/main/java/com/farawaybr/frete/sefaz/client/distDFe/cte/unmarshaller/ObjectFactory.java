package com.farawaybr.frete.sefaz.client.distDFe.cte.unmarshaller;

import javax.xml.bind.annotation.XmlRegistry;

import br.inf.portalfiscal.cte.DistDFeInt;

@XmlRegistry
public class ObjectFactory {

	public UnCteDistInteresseResult createCteDistDFeInteresseResponse() {
		return new UnCteDistInteresseResult();

	}

	public UnCteDistResponse createCteDistDFeInteresseResult() {
		return new UnCteDistResponse();
	}

	public DistDFeInt createDistDFeInt() {
		return new DistDFeInt();
	}
}
