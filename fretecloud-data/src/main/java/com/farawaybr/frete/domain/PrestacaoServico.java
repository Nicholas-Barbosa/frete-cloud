package com.farawaybr.frete.domain;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class PrestacaoServico extends SuperEntity {

	private final BigDecimal valorPrest;
	private final BigDecimal valorReceber;
	@OneToMany(mappedBy = "valorPrestacaoServico", cascade = CascadeType.ALL)
	private final Set<ComponentePrestServico> componentes;

	public PrestacaoServico() {
		this(null, null, null, null);
	}

	public PrestacaoServico(Long id, BigDecimal valorPrest, BigDecimal valorReceber,
			Set<ComponentePrestServico> componentes) {
		super(id);
		this.valorPrest = valorPrest;
		this.valorReceber = valorReceber;
		this.componentes = componentes;
	}

	public BigDecimal getValorPrest() {
		return valorPrest;
	}

	public BigDecimal getValorReceber() {
		return valorReceber;
	}

	public Set<ComponentePrestServico> getComponentes() {
		return componentes;
	}

	@Entity
	public class ComponentePrestServico extends SuperEntity {
		private final String nome;
		private final BigDecimal valor;
		@ManyToOne
		private final PrestacaoServico valorPrestacaoServico;

		public ComponentePrestServico() {
			this(null, null, null, null);
		}

		public ComponentePrestServico(Long id, String nome, BigDecimal valor, PrestacaoServico valorPrestacaoServico) {
			super(id);
			this.nome = nome;
			this.valor = valor;
			this.valorPrestacaoServico = valorPrestacaoServico;
		}

		public String getNome() {
			return nome;
		}

		public BigDecimal getValor() {
			return valor;
		}

		public PrestacaoServico getValorPrestacaoServico() {
			return valorPrestacaoServico;
		}

	}
}
