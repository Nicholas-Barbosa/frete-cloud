package com.farawaybr.frete;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.farawaybr.frete.domain.CertificateKeystore;
import com.farawaybr.frete.domain.State;
import com.farawaybr.frete.domain.StateToSearchDistFeCte;
import com.farawaybr.frete.sefaz.client.distDFe.cte.DistDFeConhecimentoWSClient;

@SpringBootTest
class DistConhecimentoClientTest {

	@Autowired
	private DistDFeConhecimentoWSClient distCte;

	@Test
	void test() {
		try {
			Set<StateToSearchDistFeCte> statesToSearch = new HashSet<>();
			statesToSearch
					.add(new StateToSearchDistFeCte(null, "000000000000000", new State(null, "DF", "DF", 41)));
			distCte.setDefaultCertificateKeystore(new CertificateKeystore(null,
					"C:\\Users\\nicho\\Downloads\\CDG COMPONENTES AUTOMOTIVOS LTDA09512164000172.pfx",
					new char[] { 'M', '@', 's', 't', 'e', 'r', '!', '@', '#' }, "09512164000172", statesToSearch));
			distCte.sendAndReceive();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
