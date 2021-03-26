package com.farawaybr.frete.sefaz.base64;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public interface Base64Service {

	Encoder enconder = Base64.getEncoder();
	Decoder decoder = Base64.getDecoder();

	default String encode(String text) {
		return enconder.encodeToString(text.getBytes());
	}

	default String decode(String text) {
		return new String(decoder.decode(text));
	}
}
