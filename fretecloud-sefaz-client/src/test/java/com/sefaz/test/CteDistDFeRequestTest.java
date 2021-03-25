package com.sefaz.test;

import org.junit.jupiter.api.Test;

import com.farawaybr.frete.data.xml.GenericSerialiazerDeserializer;
import com.farawaybr.frete.sefaz.ctedistdfe.template.CteDistDfeRequestPayload;
import com.farawaybr.frete.sefaz.ctedistdfe.template.CteDistDfeRequestPayload.DistNsuTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;

class CteDistDFeRequestTest {

	@Test
	void test() throws JsonProcessingException {
		CteDistDfeRequestPayload cte = new CteDistDfeRequestPayload("1", "41", "09512164000172",
				new DistNsuTemplate("000000000000000"));

		System.out.println(new GenericSerialiazerDeserializer<CteDistDfeRequestPayload>().serialize(cte));
	}

}
