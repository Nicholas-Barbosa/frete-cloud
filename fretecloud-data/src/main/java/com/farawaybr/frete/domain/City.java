package com.farawaybr.frete.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class City extends SuperEntity {

	private final String name;
	private final String ibgeCode;
	@ManyToOne()
	private final State estado;

	public City() {
		this(null, null, null, null);
	}

	public City(Long id, String name, String ibgeCode, State estado) {
		super(id);
		this.name = name;
		this.ibgeCode = ibgeCode;
		this.estado = estado;
	}

	public String getName() {
		return name;
	}

	public String getIbgeCode() {
		return ibgeCode;
	}

	public State getEstado() {
		return estado;
	}
}
