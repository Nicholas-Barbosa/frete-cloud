package com.farawaybr.frete.sefaz;

import javax.xml.bind.JAXBException;

public interface JaxbTemplateService {

	<T> T unmarhall(String text, Class<T> classT)throws JAXBException;
}
