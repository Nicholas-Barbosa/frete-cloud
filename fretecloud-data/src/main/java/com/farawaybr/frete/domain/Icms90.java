package com.farawaybr.frete.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class Icms90 extends Icms {

	private BigDecimal credito;

	public Icms90(Long id, Integer cst, BigDecimal baseCalculo, BigDecimal aliquota, BigDecimal valor,
			BigDecimal credito, BigDecimal pReducaoBc) {
		super(id, cst, baseCalculo, aliquota, valor, pReducaoBc);
		this.credito = credito;
	}

	public BigDecimal getCredito() {
		return credito;
	}
}
