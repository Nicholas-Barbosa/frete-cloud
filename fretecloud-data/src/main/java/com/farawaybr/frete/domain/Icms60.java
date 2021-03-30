package com.farawaybr.frete.domain;

import java.math.BigDecimal;

public class Icms60 extends Icms {

	private BigDecimal credito;

	public Icms60(Long id, Integer cst, BigDecimal baseCalculo, BigDecimal aliquota, BigDecimal valor,
			BigDecimal credito,BigDecimal pReducaoBc) {
		super(id, cst, baseCalculo, aliquota, valor,pReducaoBc);
		this.credito = credito;
	}

	public BigDecimal getCredito() {
		return credito;
	}
	
	
}
