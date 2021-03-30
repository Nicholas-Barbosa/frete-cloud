package com.farawaybr.frete.domain;

import java.time.LocalDate;

import javax.persistence.Table;

@Table
public class DocumentoDeTransporte extends DocumentoEletronico {

	private final Empresa emissor;

	private final String chaveCte;

	public DocumentoDeTransporte(Long id, String numero, String serie, LocalDate emissao, Empresa emissor,
			String chaveCte) {
		super(id, numero, serie, emissao);
		this.emissor = emissor;
		this.chaveCte = chaveCte;
	}

	public Empresa getEmissor() {
		return emissor;
	}

	public String getChaveCte() {
		return chaveCte;
	}

}
