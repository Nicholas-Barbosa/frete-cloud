package com.farawaybr.frete.domain;

import java.math.BigDecimal;

public class Icms45 extends Icms {

	public Icms45(Long id, Integer cst, BigDecimal baseCalculo, BigDecimal aliquota, BigDecimal valor,
			BigDecimal pReducaoBc) {
		super(id, cst, baseCalculo, aliquota, valor, pReducaoBc);
	}

}
