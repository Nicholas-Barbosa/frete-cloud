package com.farawaybr.frete.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class IcmsOutraUf extends Icms {

	public IcmsOutraUf(Long id, Integer cst, BigDecimal baseCalculo, BigDecimal aliquota, BigDecimal valor,
			BigDecimal pReducaoBc) {
		super(id, cst, baseCalculo, aliquota, valor, pReducaoBc);
	}

}
