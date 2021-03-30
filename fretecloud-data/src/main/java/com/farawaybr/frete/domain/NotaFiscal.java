package com.farawaybr.frete.domain;

import javax.persistence.Table;

@Table
public class NotaFiscal extends SuperEntity {

	private final String numero;
	private final String serie;
	private final String chave;

	public NotaFiscal() {
		this(null, null, null, null);
	}

	public NotaFiscal(Long id, String numero, String serie, String chave) {
		super(id);
		this.numero = numero;
		this.serie = serie;
		this.chave = chave;
	}

	public String getNumero() {
		return numero;
	}

	public String getSerie() {
		return serie;
	}

	public String getChave() {
		return chave;
	}

}
