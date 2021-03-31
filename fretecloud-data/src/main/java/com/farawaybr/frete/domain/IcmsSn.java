package com.farawaybr.frete.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class IcmsSn extends Icms {

	private Integer indicadorSN;

	public IcmsSn(Long id, Integer cst, BigDecimal baseCalculo, BigDecimal aliquota, BigDecimal valor,
			BigDecimal pReducaoBc, Integer indicadorSN) {
		super(id, cst, baseCalculo, aliquota, valor, pReducaoBc);
		this.indicadorSN = indicadorSN;
	}

	public Integer getIndicadorSN() {
		return indicadorSN;
	}

}
