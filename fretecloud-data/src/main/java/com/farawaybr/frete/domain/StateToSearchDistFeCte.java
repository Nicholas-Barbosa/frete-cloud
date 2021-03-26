package com.farawaybr.frete.domain;

import javax.persistence.Entity;

@Entity
public class StateToSearchDistFeCte extends SuperEntity {

	private String nsuToSearch;
	private State state;

	public StateToSearchDistFeCte(Long id, String nsuToSearch, State state) {
		super(id);
		this.nsuToSearch = nsuToSearch;
		this.state = state;
	}

	public String getNsuToSearch() {
		return nsuToSearch;
	}

	public State getState() {
		return state;
	}

	@Override
	public String toString() {
		return "StateToSearchDistFeCte [nsuToSearch=" + nsuToSearch + ", state=" + state + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nsuToSearch == null) ? 0 : nsuToSearch.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		StateToSearchDistFeCte other = (StateToSearchDistFeCte) obj;
		if (nsuToSearch == null) {
			if (other.nsuToSearch != null)
				return false;
		} else if (!nsuToSearch.equals(other.nsuToSearch))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}

}
