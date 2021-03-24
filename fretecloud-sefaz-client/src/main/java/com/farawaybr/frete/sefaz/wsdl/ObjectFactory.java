package com.farawaybr.frete.sefaz.wsdl;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

	public CteDistDFeInteresse createCteDistDFeInteresse() {
		return new CteDistDFeInteresse();
	}

	public CteDadosMsg createCteDadosMsg() {
		return new CteDadosMsg();
	}

	public DistDFeInt createDistDfeInt() {
		return new DistDFeInt();
	}
}
