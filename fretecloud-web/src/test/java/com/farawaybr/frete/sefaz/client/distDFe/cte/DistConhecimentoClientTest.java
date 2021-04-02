package com.farawaybr.frete.sefaz.client.distDFe.cte;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.farawaybr.frete.domain.CertificateKeystore;
import com.farawaybr.frete.domain.State;
import com.farawaybr.frete.domain.StateToSearchDistFeCte;

@SpringBootTest
class DistConhecimentoClientTest {

	@Autowired
	private DistDFeConhecimentoWSClient distCte;

	@Test
	void test() {
		try {
			Set<StateToSearchDistFeCte> statesToSearch = new HashSet<>();
			statesToSearch
					.add(new StateToSearchDistFeCte(null, "000000000000000", new State(null, "PR", "Parana", 41)));
			distCte.setDefaultCertificateKeystore(new CertificateKeystore(null,
					"C:\\Users\\nicho\\Downloads\\CDG COMPONENTES AUTOMOTIVOS LTDA09512164000172.pfx",
					new char[] { 'M', '@', 's', 't', 'e', 'r', '!', '@', '#' }, "09512164000172", statesToSearch));
			assertNotNull(distCte.sendAndReceive());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testTakeDocs() {
		Set<StateToSearchDistFeCte> statesToSearch = new HashSet<>();
		statesToSearch.add(new StateToSearchDistFeCte(null, "000000000000000", new State(null, "PR", "Parana", 41)));
		try {
			distCte.setDefaultCertificateKeystore(new CertificateKeystore(null,
					"C:\\Users\\nicho\\Downloads\\CDG COMPONENTES AUTOMOTIVOS LTDA09512164000172.pfx",
					new char[] { 'M', '@', 's', 't', 'e', 'r', '!', '@', '#' }, "09512164000172", statesToSearch));
			distCte.sendAndReceive().getEstadosResponses().parallelStream()
					.flatMap(estado -> estado.getDocsDecompressed().stream()).forEach(response -> {
						if (response.getCteProc() != null) {
							System.out.println(
									"response cteProc " + response.getCteProc().getCTe().getInfCte().getIde().getNCT());
						} else
							System.out.println("response procEevento "
									+ response.getProcEvento().getEventoCTe().getInfEvento().getTpAmb());
					});

		} catch (UnrecoverableKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
