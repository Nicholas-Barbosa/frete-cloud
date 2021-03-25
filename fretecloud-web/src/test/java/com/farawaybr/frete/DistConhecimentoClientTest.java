package com.farawaybr.frete;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.farawaybr.frete.domain.CertificateKeystore;
import com.farawaybr.frete.sefaz.client.distDFe.cte.DistDFeConhecimentoWSClient;

@SpringBootTest
class DistConhecimentoClientTest {

	@Autowired
	private DistDFeConhecimentoWSClient distCte;

	@Test
	void test() {
		try {
			distCte.setDefaultCertificateKeystore(new CertificateKeystore(
					"C:\\Users\\nicho\\Downloads\\CDG COMPONENTES AUTOMOTIVOS LTDA09512164000172.pfx",
					new char[] { 'M', '@', 's', 't', 'e', 'r', '!', '@', '#' }, "09512164000172", "000000000000000"));
			distCte.sendAndReceiveFull();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
