package com.farawaybr.frete.domain;

import java.math.BigDecimal;

public class IcmsOutraUf extends Icms {

	public IcmsOutraUf(Long id, Integer cst, BigDecimal baseCalculo, BigDecimal aliquota, BigDecimal valor,
			BigDecimal pReducaoBc) {
		super(id, cst, baseCalculo, aliquota, valor, pReducaoBc);
	}

}
