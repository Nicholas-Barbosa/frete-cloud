package com.farawaybr.frete.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Empresa extends SuperEntity {

	private final String cnpj;
	private final String IE;
	private final String name;
	private final String fantasyName;
	@OneToOne(cascade = CascadeType.ALL)
	private final Address address;

	public Empresa() {
		this(null, null, null, null, null, null);
	}

	public Empresa(Long id, String cnpj, String iE, String name, String fantasyName, Address address) {
		super(id);
		this.cnpj = cnpj;
		this.IE = iE;
		this.name = name;
		this.fantasyName = fantasyName;
		this.address = address;
	}

	public String getCnpj() {
		return cnpj;
	}

	public String getIE() {
		return IE;
	}

	public String getName() {
		return name;
	}

	public String getFantasyName() {
		return fantasyName;
	}

	public Address getAddress() {
		return address;
	}
}
