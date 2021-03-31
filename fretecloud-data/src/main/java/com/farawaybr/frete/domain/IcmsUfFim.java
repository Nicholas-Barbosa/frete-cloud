package com.farawaybr.frete.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class IcmsUfFim extends SuperEntity {

	private BigDecimal baseCalculoUfFim;
	private BigDecimal percentualFCPUfFim;
	private Float aliquotaInternaUfFim;
	private Float aliquotaInterestadual;
	private BigDecimal valorFCPUfFim;

	private BigDecimal valorUFIni;

	public IcmsUfFim(Long id, BigDecimal baseCalculoUfFim, BigDecimal percentualFCPUfFim, Float aliquotaInternaUfFim,
			Float aliquotaInterestadual, BigDecimal valorFCPUfFim, BigDecimal valorUFIni) {
		super(id);
		this.baseCalculoUfFim = baseCalculoUfFim;
		this.percentualFCPUfFim = percentualFCPUfFim;
		this.aliquotaInternaUfFim = aliquotaInternaUfFim;
		this.aliquotaInterestadual = aliquotaInterestadual;
		this.valorFCPUfFim = valorFCPUfFim;
		this.valorUFIni = valorUFIni;
	}

	/**
	 * Valor da BC do ICMS na UF de término da prestação do serviço de transporte
	 * 
	 * @return
	 */
	public BigDecimal getBaseCalculoUfFim() {
		return baseCalculoUfFim;
	}

	/**
	 * Percentual do ICMS relativo ao Fundo de Combate à pobreza (FCP) na UF de
	 * término da prestação do serviço de transporte
	 * 
	 * @return
	 */
	public BigDecimal getPercentualFCPUfFim() {
		return percentualFCPUfFim;
	}

	/**
	 * Alíquota interna da UF de término da prestação do serviço de transporte
	 * 
	 * @return
	 */
	public Float getAliquotaInternaUfFim() {
		return aliquotaInternaUfFim;
	}

	/**
	 * Alíquota interestadual das UF envolvidas
	 * 
	 * @return
	 */
	public Float getAliquotaInterestadual() {
		return aliquotaInterestadual;
	}

	/**
	 * Valor do ICMS de partilha para a UF de término da prestação do serviço de
	 * transporte
	 * 
	 * 
	 * @return
	 */
	public BigDecimal getValorFCPUfFim() {
		return valorFCPUfFim;
	}

	/**
	 * Valor do ICMS de partilha para a UF de início da prestação do serviço de
	 * transporte
	 * 
	 * @return
	 */
	public BigDecimal getValorUFIni() {
		return valorUFIni;
	}

}
