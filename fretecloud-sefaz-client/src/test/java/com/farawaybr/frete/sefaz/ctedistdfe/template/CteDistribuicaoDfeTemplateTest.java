package com.farawaybr.frete.sefaz.ctedistdfe.template;

import org.junit.jupiter.api.Test;

import com.farawaybr.frete.formatmapper.xml.XmlMapperTemplate;
import com.farawaybr.frete.sefaz.ctedistdfe.CteDistribuicaoDfeProperties;

class CteDistribuicaoDfeTemplateTest {

	private CteDistribuicaoDfeTemplate cteDist = new CteDistribuicaoDfeTemplate(new CteDistribuicaoDfeProperties(),
			new XmlMapperTemplate());

	@Test
	void test() {
		cteDist.fetch();
	}

}
