package com.farawaybr.frete.sefaz.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cteDadosMsg", propOrder = { "distDfeInt" })
public class CteDadosMsg {

	private DistDFeInt distDfeInt = new DistDFeInt();

	public DistDFeInt getDistDfeInt() {
		return distDfeInt;
	}

	
}
