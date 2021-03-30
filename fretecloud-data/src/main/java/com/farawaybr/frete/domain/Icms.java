package com.farawaybr.frete.domain;

import java.math.BigDecimal;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Icms extends SuperEntity {

	private Integer cst;
	private BigDecimal baseCalculo;
	private BigDecimal aliquota;
	private BigDecimal valor;
	private BigDecimal pReducaoBc;

	public Icms(Long id, Integer cst, BigDecimal baseCalculo, BigDecimal aliquota, BigDecimal valor,
			BigDecimal pReducaoBc) {
		super(id);
		this.cst = cst;
		this.baseCalculo = baseCalculo;
		this.aliquota = aliquota;
		this.valor = valor;
		this.pReducaoBc = pReducaoBc;
	}

	public Integer getCst() {
		return cst;
	}

	public BigDecimal getBaseCalculo() {
		return baseCalculo;
	}

	public BigDecimal getAliquota() {
		return aliquota;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public BigDecimal getpReducaoBc() {
		return pReducaoBc;
	}
}
