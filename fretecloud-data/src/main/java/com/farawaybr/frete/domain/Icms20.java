package com.farawaybr.frete.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class Icms20 extends Icms {

	public Icms20(Long id, Integer cst, BigDecimal baseCalculo, BigDecimal aliquota, BigDecimal valor,
			BigDecimal pRedBc) {
		super(id, cst, baseCalculo, aliquota, valor, pRedBc);
	}

}
