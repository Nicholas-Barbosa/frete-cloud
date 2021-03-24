package com.farawaybr.frete;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

@SpringBootTest
class WebServiceTemplateTest {

	@Autowired
	private WebServiceTemplate webServiceTemplate;

	@Test
	void test() {
		webServiceTemplate.sendAndReceive("https://www1.cte.fazenda.gov.br/CTeDistribuicaoDFe/CTeDistribuicaoDFe.asmx",
				null, null);
	}

}
