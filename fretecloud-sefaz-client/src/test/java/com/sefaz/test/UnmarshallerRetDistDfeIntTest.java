package com.sefaz.test;

import java.io.File;

import javax.xml.bind.JAXBContext;

import org.junit.jupiter.api.Test;

import com.farawaybr.frete.sefaz.dfe.cte.unmarshaller.UnCteDistResponse;

import br.inf.portalfiscal.cte.DistDFeInt;
import br.inf.portalfiscal.cte.DistDFeInt.DistNSU;
import br.inf.portalfiscal.cte.ret.RetDistDFeInt;

class UnmarshallerRetDistDfeIntTest {

	@Test
	void unmarshaller() throws Exception {
		JAXBContext context = JAXBContext.newInstance(RetDistDFeInt.class);
		RetDistDFeInt response = (RetDistDFeInt) context.createUnmarshaller()
				.unmarshal(new File("C:\\Users\\nicho\\OneDrive\\Documentos\\teste soapmessage\\retTest.xml"));
		System.out.println(response.getXMotivo());
	}

	@Test
	void testMarshaller() throws Exception {
		JAXBContext context = JAXBContext.newInstance(DistDFeInt.class);

		DistDFeInt distDfeInt = new DistDFeInt();

		distDfeInt.setVersao("1.00");
		distDfeInt.setTpAmb("1");
		distDfeInt.setCUFAutor("41");
		distDfeInt.setCNPJ("09512164000172");

		DistNSU distNsu = new DistNSU();
		distNsu.setUltNSU("000000000000000");

		distDfeInt.setDistNSU(distNsu);

		context.createMarshaller().marshal(distDfeInt,
				new File("C:\\Users\\nicho\\OneDrive\\Documentos\\teste soapmessage\\testDistMarshall"));
	}

	@Test
	void testUmarshallerCompleteSoapBody() throws Exception {
		JAXBContext context = JAXBContext.newInstance(UnCteDistResponse.class);
		UnCteDistResponse response = (UnCteDistResponse) context.createUnmarshaller()
				.unmarshal(new File("C:\\Users\\nicho\\OneDrive\\Documentos\\teste soapmessage\\retTestComplete.xml"));
		System.out.println(response.getUnCteDistInteresseResult().getUnRetDistDFeInt().getxMotivo());
	}
}
