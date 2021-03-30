package com.farawaybr.frete.domain;

import java.time.LocalDate;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class DocumentoEletronico extends SuperEntity {

	private final String numero, serie;
	private final LocalDate emissao;

	public DocumentoEletronico(Long id, String numero, String serie, LocalDate emissao) {
		super(id);
		this.numero = numero;
		this.serie = serie;
		this.emissao = emissao;
	}

	public String getNumero() {
		return numero;
	}

	public String getSerie() {
		return serie;
	}

	public LocalDate getEmissao() {
		return emissao;
	}

}
