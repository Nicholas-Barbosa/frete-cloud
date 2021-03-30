package com.farawaybr.frete.sefaz.client.distDFe.cte;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DistDFeConhecimentoWSClientTemplateTest {

	@Autowired
	private DistDFeConhecimentoWSClientTemplate wsClientTemplate;

	@Test
	void test() {
		wsClientTemplate.reciveAndSave();
	}

}
