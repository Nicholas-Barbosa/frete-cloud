package com.farawaybr.frete;

import java.io.StringReader;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.farawaybr.frete.sefaz.ctedistdfe.template.CteDistDfeRequestPayload;
import com.farawaybr.frete.sefaz.ctedistdfe.template.CteDistDfeResponsePayload;
import com.farawaybr.frete.sefaz.ctedistdfe.template.CteDistDfeRequestPayload.DistNsuTemplate;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

class SoapMessageTest {

	private final String xml = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\r\n"
			+ "   <soap:Body>\r\n"
			+ "      <cteDistDFeInteresseResponse xmlns=\"http://www.portalfiscal.inf.br/cte/wsdl/CTeDistribuicaoDFe\">\r\n"
			+ "         <cteDistDFeInteresseResult>\r\n"
			+ "            <retDistDFeInt versao=\"1.00\" xmlns=\"http://www.portalfiscal.inf.br/cte\">\r\n"
			+ "               <tpAmb>1</tpAmb>\r\n" + "               <verAplic>1.0.1_2103231706</verAplic>\r\n"
			+ "               <cStat>138</cStat>\r\n" + "               <xMotivo>documento localizado.</xMotivo>\r\n"
			+ "               <dhResp>2021-03-23T17:06:42</dhResp>\r\n"
			+ "               <ultNSU>000000000115377</ultNSU>\r\n"
			+ "               <maxNSU>000000000122877</maxNSU>\r\n" + "      \r\n" + "            </retDistDFeInt>\r\n"
			+ "         </cteDistDFeInteresseResult>\r\n" + "      </cteDistDFeInteresseResponse>\r\n"
			+ "   </soap:Body>\r\n" + "</soap:Envelope>";

	@Test
	void testDeserializeXml() throws Exception {
		XmlMapper xmlMapper = new XmlMapper();
		xmlMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		XMLInputFactory f = XMLInputFactory.newFactory();
		XMLStreamReader sr = f.createXMLStreamReader(new StringReader(xml));

		while (sr.hasNext()) {
			int type = sr.next();

			if (type == XMLStreamReader.START_ELEMENT && "retDistDFeInt".equals(sr.getLocalName())) {
				CteDistDfeResponsePayload r = xmlMapper.readValue(sr, CteDistDfeResponsePayload.class);

				System.out.println(r.getStatus());
			}
		}
	}

}
