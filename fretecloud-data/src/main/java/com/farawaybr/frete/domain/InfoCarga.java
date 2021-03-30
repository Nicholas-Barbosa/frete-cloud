package com.farawaybr.frete.domain;

import javax.persistence.Table;

@Table
public class InfoCarga extends SuperEntity {

	private final UnidadeDeMedida unidade;
	private final String medida;
	private final Float quantidade;

	public InfoCarga(Long id, UnidadeDeMedida unidade, String medida, Float quantidade) {
		super(id);
		this.unidade = unidade;
		this.medida = medida;
		this.quantidade = quantidade;
	}

	public UnidadeDeMedida getUnidade() {
		return unidade;
	}

	public String getMedida() {
		return medida;
	}

	public Float getQuantidade() {
		return quantidade;
	}

	
}
