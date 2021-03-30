package com.farawaybr.frete.domain;

import java.time.LocalDate;

import javax.persistence.Table;

@Table
public class Conhecimento extends DocumentoEletronico {

	private final String chave;
	private final String naturezaOperacao;
	private final String mod;
	private final Modal modal;
	private final City origem;
	private final City destino;
	private final Empresa emitente;
	private final Empresa remetente;
	private final Empresa expedidor;
	private final Empresa destinatario;
	private final ValoresPrestacaoServico vPrest;
	private final Imposto imposto;
	private final TipoService tipoServico;
	private final NotaFiscal notaFiscal;
	private final Carga carga;
	private final DocumentoDeTransporte docDeTransporte;

	public Conhecimento(Long id, String numero, String serie, LocalDate emissao, String chave, String naturezaOperacao,
			String mod, Modal modal, City origem, City destino, Empresa emitente, Empresa remetente, Empresa expedidor,
			Empresa destinatario, ValoresPrestacaoServico vPrest, Imposto imposto, TipoService tipoServico,
			NotaFiscal notaFiscal, Carga carga, DocumentoDeTransporte docDeTransporte) {
		super(id, numero, serie, emissao);
		this.chave = chave;
		this.naturezaOperacao = naturezaOperacao;
		this.mod = mod;
		this.modal = modal;
		this.origem = origem;
		this.destino = destino;
		this.emitente = emitente;
		this.remetente = remetente;
		this.expedidor = expedidor;
		this.destinatario = destinatario;
		this.vPrest = vPrest;
		this.imposto = imposto;
		this.tipoServico = tipoServico;
		this.notaFiscal = notaFiscal;
		this.carga = carga;
		this.docDeTransporte = docDeTransporte;
	}

	public String getChave() {
		return chave;
	}

	public String getNaturezaOperacao() {
		return naturezaOperacao;
	}

	public String getMod() {
		return mod;
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

	public ValoresPrestacaoServico getvPrest() {
		return vPrest;
	}

	public Imposto getImposto() {
		return imposto;
	}

	public TipoService getTipoServico() {
		return tipoServico;
	}

	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}

	public Carga getCarga() {
		return carga;
	}

	public DocumentoDeTransporte getDocDeTransporte() {
		return docDeTransporte;
	}

}
