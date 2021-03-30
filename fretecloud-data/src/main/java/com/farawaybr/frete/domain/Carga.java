package com.farawaybr.frete.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Table;

@Table
public class Carga extends SuperEntity {

	private final BigDecimal valor;
	private final String produtoPredominante;
	private final String caracteristicas;
	private final List<InfoCarga> infosCargs;

	public Carga(Long id, BigDecimal valor, String produtoPredominante, String caracteristicas,
			List<InfoCarga> infosCargs) {
		super(id);
		this.valor = valor;
		this.produtoPredominante = produtoPredominante;
		this.caracteristicas = caracteristicas;
		this.infosCargs = new ArrayList<>(infosCargs);
	}

	public BigDecimal getValor() {
		return valor;
	}

	public String getProdutoPredominante() {
		return produtoPredominante;
	}

	public String getCaracteristicas() {
		return caracteristicas;
	}

	public List<InfoCarga> getInfosCargs() {
		return infosCargs;
	}

}
