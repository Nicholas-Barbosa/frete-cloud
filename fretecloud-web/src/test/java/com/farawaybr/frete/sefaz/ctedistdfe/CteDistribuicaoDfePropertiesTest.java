package com.farawaybr.frete.sefaz.ctedistdfe;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CteDistribuicaoDfePropertiesTest {

	@Autowired
	private CteDistribuicaoDfeProperties cteDist;

	@Test
	public void getProperty() {
		assertEquals("https://www1.cte.fazenda.gov.br/CTeDistribuicaoDFe/CTeDistribuicaoDFe.asmx", cteDist.getUrl());
	}
}
