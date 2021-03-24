package com.farawaybr.frete;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.farawaybr.frete.domain.CertificateKeystore;
import com.farawaybr.frete.sefaz.dfe.cte.DistConhecimentoClient;

@SpringBootTest
class DistConhecimentoClientTest {

	@Autowired
	private DistConhecimentoClient distCte;

	@Test
	void test() {
		try {
			distCte.send(new CertificateKeystore(
					"C:\\Users\\nicho\\Downloads\\CDG COMPONENTES AUTOMOTIVOS LTDA09512164000172.pfx",
					new char[] { 'M', '@', 's', 't', 'e', 'r', '!', '@', '#' }, "09512164000172", "000000000000000"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
