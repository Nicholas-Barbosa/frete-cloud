package com.farawaybr.frete.sefaz.client.distDFe.cte.unmarshaller;

import java.util.Arrays;

public enum TipoAmbient {

	PRODUCAO("1"), HOMOLOGACAO("2");

	private final String ambient;

	private TipoAmbient(String tipo) {
		ambient = tipo;
	}

	public String getAmbient() {
		return ambient;
	}

	public static TipoAmbient valueOfFromNumeric(String number) {
		return Arrays.asList(TipoAmbient.values()).parallelStream().filter(t -> t.ambient.equals(number)).findAny()
				.get();

	}

}
