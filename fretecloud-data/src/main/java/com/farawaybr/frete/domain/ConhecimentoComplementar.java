package com.farawaybr.frete.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class ConhecimentoComplementar extends SuperEntity {

	@OneToMany
	@JoinTable(inverseJoinColumns = @JoinColumn(name = ""))
	private Set<Conhecimento> conhecimentoComplementarComp;

	@OneToOne
	private Conhecimento conhecimentoTipoNormal;

	public ConhecimentoComplementar(Long id, Set<Conhecimento> conhecimentoComplementar,
			Conhecimento conhecimentoTipoNormal) {
		super(id);
		this.conhecimentoComplementarComp = conhecimentoComplementar;
		this.conhecimentoTipoNormal = conhecimentoTipoNormal;
	}

	public Set<Conhecimento> getConhecimentoComplementar() {
		return conhecimentoComplementarComp;
	}

	public Conhecimento getConhecimentoTipoNormal() {
		return conhecimentoTipoNormal;
	}

}
