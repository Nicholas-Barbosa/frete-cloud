package com.farawaybr.frete.domain;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@MappedSuperclass
public class Conhecimento extends DocumentoEletronico {

	private final String chave;
	private final String naturezaOperacao;
	@Enumerated(EnumType.STRING)
	private final Modal modal;
	@ManyToOne
	private final City origem;
	@ManyToOne
	private final City destino;
	@ManyToOne
	private final Empresa emitente;
	@ManyToOne
	private final Empresa remetente;
	@ManyToOne
	private final Empresa expedidor;
	@ManyToOne
	private final Empresa destinatario;
	@OneToOne(cascade = CascadeType.ALL)
	private final PrestacaoServico prestacaoServico;
	@OneToOne(cascade = CascadeType.ALL)
	private final Imposto imposto;
	@Enumerated(EnumType.STRING)
	private final TipoCte tipoServico;
	@ManyToMany
	@JoinTable(joinColumns = @JoinColumn(name = "conhecimento_id"), inverseJoinColumns = @JoinColumn(name = "nota_id"))
	private final Set<NotaFiscal> notasFiscais;
	@OneToOne(cascade = CascadeType.ALL)
	private final Carga carga;
	@Enumerated(EnumType.STRING)
	private final TipoCte tipo;
	public Conhecimento(Long id, String numero, String serie, LocalDate emissao, String chave, String naturezaOperacao,
			Modal modal, City origem, City destino, Empresa emitente, Empresa remetente, Empresa expedidor,
			Empresa destinatario, PrestacaoServico vPrest, Imposto imposto, TipoCte tipoServico,
			Set<NotaFiscal> notaFiscal, Carga carga, TipoCte tipo) {
		super(id, numero, serie, emissao);
		this.chave = chave;
		this.naturezaOperacao = naturezaOperacao;
		this.modal = modal;
		this.origem = origem;
		this.destino = destino;
		this.emitente = emitente;
		this.remetente = remetente;
		this.expedidor = expedidor;
		this.destinatario = destinatario;
		this.prestacaoServico = vPrest;
		this.imposto = imposto;
		this.tipoServico = tipoServico;
		this.notasFiscais = notaFiscal;
		this.carga = carga;
		this.tipo = tipo;
	}

	public String getChave() {
		return chave;
	}

	public String getNaturezaOperacao() {
		return naturezaOperacao;
	}

	public Modal getModal() {
		return modal;
	}

	public City getOrigem() {
		return origem;
	}

	public City getDestino() {
		return destino;
	}

	public Empresa getEmitente() {
		return emitente;
	}

	public Empresa getRemetente() {
		return remetente;
	}

	public Empresa getExpedidor() {
		return expedidor;
	}

	public Empresa getDestinatario() {
		return destinatario;
	}

	public PrestacaoServico getvPrest() {
		return prestacaoServico;
	}

	public Imposto getImposto() {
		return imposto;
	}

	public TipoCte getTipoServico() {
		return tipoServico;
	}

	public Set<NotaFiscal> getNotasFiscais() {
		return notasFiscais;
	}

	public Carga getCarga() {
		return carga;
	}

	public TipoCte getTipo() {
		return tipo;
	}
}
