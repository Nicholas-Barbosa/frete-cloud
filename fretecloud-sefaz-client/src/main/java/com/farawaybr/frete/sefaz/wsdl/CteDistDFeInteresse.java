package com.farawaybr.frete.sefaz.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cteDistDFeInteresse", propOrder = { "cteDadosMsg" })
@XmlRootElement(name = "cteDistDFeInteresse")
public class CteDistDFeInteresse {

	@XmlElement(name = "cteDadosMsg", required = true)
	private CteDadosMsg cteDadosMsg = new CteDadosMsg();

	public CteDadosMsg getCteDadosMsg() {
		return cteDadosMsg;
	}

}
