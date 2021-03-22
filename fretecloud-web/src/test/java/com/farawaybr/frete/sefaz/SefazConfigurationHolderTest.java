package com.farawaybr.frete.sefaz;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.farawaybr.frete.sefaz.properties.SefazProperties;

@SpringBootTest
class SefazConfigurationHolderTest {

	@Autowired
	private SefazProperties sefazTruststoreConfigHolder;

	@Test
	void test() {
		assertEquals("C:\\Users\\nicho\\Downloads\\server-keystore.jks",
				sefazTruststoreConfigHolder.getTruststore().getPath());
	}

	@Test
	void docFiscalEletronicoNotNull() {
		assertNotNull(sefazTruststoreConfigHolder.getDocumentoFiscalEletronico());
	}

	@Test
	void assertEqualsdocConhecimentoUrl() {
		assertEquals(sefazTruststoreConfigHolder.getDocumentoFiscalEletronico().getConhecimento().getUrl(),
				"https://www1.cte.fazenda.gov.br/CTeDistribuicaoDFe/CTeDistribuicaoDFe.asmx");
	}
}
