package com.farawaybr.frete.domain;

import javax.persistence.Table;

@Table
public class City extends SuperEntity {

	private final String name;
	private final State state;
	private final String ibgeCode;

	public City() {
		this(null, null, null,null);
	}

	public City(Long id, String name, State state, String ibgeCode) {
		super(id);
		this.name = name;
		this.state = state;
		this.ibgeCode = ibgeCode;
	}

	public String getName() {
		return name;
	}

	public State getState() {
		return state;
	}

	public String getIbgeCode() {
		return ibgeCode;
	}

}
