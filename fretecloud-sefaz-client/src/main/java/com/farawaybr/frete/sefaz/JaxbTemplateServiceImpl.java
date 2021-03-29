package com.farawaybr.frete.sefaz;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Service;

@Service
public class JaxbTemplateServiceImpl implements JaxbTemplateService {

	@SuppressWarnings("unchecked")
	@Override
	public <T> T unmarhall(String text, Class<T> classT) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(classT);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		return (T) jaxbUnmarshaller.unmarshal(new StringReader(text));
	}

}
