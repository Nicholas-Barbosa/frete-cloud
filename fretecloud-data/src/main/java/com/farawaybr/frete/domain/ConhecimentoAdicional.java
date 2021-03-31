package com.farawaybr.frete.domain;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class ConhecimentoAdicional extends Conhecimento {

	@ManyToOne(fetch = FetchType.EAGER)
	private final ConhecimentoNormal conhecimentoNormal;

	public ConhecimentoAdicional(Long id, String numero, String serie, LocalDate emissao, String chave,
			String naturezaOperacao, Modal modal, City origem, City destino, Empresa emitente, Empresa remetente,
			Empresa expedidor, Empresa destinatario, PrestacaoServico vPrest, Imposto imposto, TipoCte tipoServico,
			 Set<NotaFiscal> notasFiscais , Carga carga, TipoCte tipo, ConhecimentoNormal conhecimentoNormal) {
		
		super(id, numero, serie, emissao, chave, naturezaOperacao, modal, origem, destino, emitente, remetente,
				expedidor, destinatario, vPrest, imposto, tipoServico, notasFiscais, carga, tipo);
		
		this.conhecimentoNormal = conhecimentoNormal;
	}

	public ConhecimentoNormal getConhecimentoNormal() {
		return conhecimentoNormal;
	}

}
