package com.farawaybr.frete.domain;

import javax.persistence.Entity;

@Entity
public class State extends SuperEntity implements Comparable<State> {

	private Integer ibgeId;
	private String sigla;
	private String nome;

	public State() {
		super(null);
	}

	public State(Long id, String sigla, String nome, Integer ibgeId) {
		super(id);
		this.sigla = sigla;
		this.nome = nome;
		this.ibgeId = ibgeId;
	}

	public String getSigla() {
		return sigla;
	}

	public String getNome() {
		return nome;
	}

	public Integer getIbgeId() {
		return ibgeId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ibgeId == null) ? 0 : ibgeId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		if (ibgeId == null) {
			if (other.ibgeId != null)
				return false;
		} else if (!ibgeId.equals(other.ibgeId))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (sigla == null) {
			if (other.sigla != null)
				return false;
		} else if (!sigla.equals(other.sigla))
			return false;
		return true;
	}

	@Override
	public int compareTo(State o) {
		return ibgeId.compareTo(o.ibgeId);
	}

}
