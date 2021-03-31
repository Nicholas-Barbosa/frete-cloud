package com.farawaybr.frete.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class ConhecimentoNormal extends Conhecimento {

	@OneToMany
	private final Set<ConhecimentoAdicional> conhecimentosAdicionais;

	public ConhecimentoNormal() {
		this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
				null, new HashSet<>());
	}

	public ConhecimentoNormal(Long id, String numero, String serie, LocalDate emissao, String chave,
			String naturezaOperacao, Modal modal, City origem, City destino, Empresa emitente, Empresa remetente,
			Empresa expedidor, Empresa destinatario, PrestacaoServico vPrest, Imposto imposto, TipoCte tipoServico,
			Set<NotaFiscal> notasFiscais, Carga carga, TipoCte tipo,
			Set<ConhecimentoAdicional> conhecimentosAdicionais) {

		super(id, numero, serie, emissao, chave, naturezaOperacao, modal, origem, destino, emitente, remetente,
				expedidor, destinatario, vPrest, imposto, tipoServico, notasFiscais, carga, tipo);

		this.conhecimentosAdicionais = new HashSet<>(conhecimentosAdicionais);
	}

	public Set<ConhecimentoAdicional> getConhecimentosAdicionais() {
		return new HashSet<>(conhecimentosAdicionais);
	}

}
