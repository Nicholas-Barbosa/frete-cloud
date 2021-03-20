package com.farawaybr.frete.formatmapper.xml;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Component
public class XmlMapperTemplate {

	public <T> String serialize(T o) {
		try {
			return new XmlMapper().writeValueAsString(o);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
