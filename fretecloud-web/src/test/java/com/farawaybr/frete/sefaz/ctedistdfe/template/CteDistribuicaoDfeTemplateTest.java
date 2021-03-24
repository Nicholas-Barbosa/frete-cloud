package com.farawaybr.frete.sefaz.ctedistdfe.template;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.farawaybr.frete.domain.CertificateKeystore;

@SpringBootTest
class CteDistribuicaoDfeTemplateTest {

	@Autowired
	private CteDistribuicaoDfeTemplate bean;

	@Test
	void test() {
		System.out.println("Status " + bean.fetch(new CertificateKeystore(
				"C:\\Users\\nicho\\Downloads\\CDG COMPONENTES AUTOMOTIVOS LTDA09512164000172.pfx",
				new char[] { 'M', '@', 's', 't', 'e', 'r', '!', '@', '#' }, "09512164000172", "000000000000000"))
				.getStatus());
	}

}
