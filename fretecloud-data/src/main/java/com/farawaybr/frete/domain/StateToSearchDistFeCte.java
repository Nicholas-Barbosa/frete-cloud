package com.farawaybr.frete.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class StateToSearchDistFeCte extends SuperEntity {

	private String lastNSU;
	@ManyToOne
	private State state;
	public StateToSearchDistFeCte(Long id, String lastNSU, State state) {
		super(id);
		this.lastNSU = lastNSU;
		this.state = state;
	}

	public String getLastNSU() {
		return lastNSU;
	}

	public State getState() {
		return state;
	}

	@Override
	public String toString() {
		return "StateToSearchDistFeCte [nsuToSearch=" + lastNSU + ", state=" + state + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lastNSU == null) ? 0 : lastNSU.hashCode());
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
		if (lastNSU == null) {
			if (other.lastNSU != null)
				return false;
		} else if (!lastNSU.equals(other.lastNSU))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}

}
