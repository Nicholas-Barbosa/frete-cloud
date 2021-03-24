package com.farawaybr.frete.data.xml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class GenericSerialiazerDeserializer<T> {

	private final XmlMapper xmlMapper;

	public GenericSerialiazerDeserializer() {
		super();
		this.xmlMapper = new XmlMapper();
	}

	public String serialize(T t) throws JsonProcessingException {
		return xmlMapper.writeValueAsString(t);
	}

	public T deserialize(String xml, Class<T> classT) throws JsonMappingException, JsonProcessingException {
		return xmlMapper.readValue(xml, classT);
	}
}
