package com.farawaybr.frete.domain;

import javax.persistence.Table;

@Table
public class Address extends SuperEntity {

	private final String street;
	private final Integer number;
	private final String district;
	private final String cep;
	private final String fone;
	private final City city;

	public Address() {
		this(null, null, null, null, null, null, null);
	}

	public Address(Long id, String street, Integer number, String district, String cep, String fone, City city) {
		super(id);
		this.street = street;
		this.number = number;
		this.district = district;
		this.cep = cep;
		this.fone = fone;
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public Integer getNumber() {
		return number;
	}

	public String getDistrict() {
		return district;
	}

	public String getCep() {
		return cep;
	}

	public String getFone() {
		return fone;
	}

	public City getCity() {
		return city;
	}

}
