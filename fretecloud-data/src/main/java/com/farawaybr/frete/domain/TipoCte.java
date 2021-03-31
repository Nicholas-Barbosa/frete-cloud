package com.farawaybr.frete.domain;

public enum TipoCte {

	NORMAL(0), SUBCONTRATACAO(1), REDESPACHO(2), REDESPACHO_INTERMEDIARIO(3), MULTIMODAL(4);

	private final Integer tipo;

	private TipoCte(Integer tipo) {
		this.tipo = tipo;
	}

	public Integer getTipo() {
		return tipo;
	}
}
