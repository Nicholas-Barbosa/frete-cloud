package com.farawaybr.frete.domain;

import javax.persistence.Entity;

@Entity
public class State extends SuperEntity{

	private String sigla;
	private String nome;
	
	public State(Long id, String sigla, String nome) {
		super(id);
		this.sigla = sigla;
		this.nome = nome;
	}
	
	public String getSigla() {
		return sigla;
	}
	public String getNome() {
		return nome;
	}
	
	
}
