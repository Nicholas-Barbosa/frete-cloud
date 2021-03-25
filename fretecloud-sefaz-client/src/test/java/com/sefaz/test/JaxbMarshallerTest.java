package com.sefaz.test;

import java.io.File;

import javax.xml.bind.JAXBContext;

import org.junit.jupiter.api.Test;

import br.inf.portalfiscal.cte.DistDFeInt;

class JaxbMarshallerTest {

	@Test
	void testDistDfe() throws Exception {
		JAXBContext context = JAXBContext.newInstance(DistDFeInt.class);
		DistDFeInt dist = new DistDFeInt();
		dist.setCNPJ("ddddddd");

		context.createMarshaller().marshal(dist,
				new File("C:\\Users\\nicho\\OneDrive\\Documentos\\teste soapmessage\\maCteDfeInteresse.xml"));
	}

}
