package com.farawaybr.frete.sefaz.client.distDFe.cte;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.consumingwebservice.wsdl.CteDistDFeInteresse;
import com.example.consumingwebservice.wsdl.CteDistDFeInteresse.CteDadosMsg;
import com.farawaybr.frete.sefaz.client.distDFe.cte.unmarshal.TipoAmbient;

import br.inf.portalfiscal.cte.DistDFeInt;
import br.inf.portalfiscal.cte.DistDFeInt.ConsNSU;
import br.inf.portalfiscal.cte.DistDFeInt.DistNSU;

@Component
@Scope("prototype")
public class DistDFeCteRequestObjectsFactory {

	private final Map<Integer, CteDistDFeInteresse> requestObjects = new ConcurrentHashMap<>();

	/**
	 * Will register the keys that will be used to retrieve objects from map.
	 * 
	 * @param keys to register in map.
	 * @return
	 */
	public DistDFeCteRequestObjectsFactory registerKeys(Set<Integer> keys) {
		if (!requestObjects.isEmpty())
			requestObjects.clear();
		requestObjects
				.putAll(keys.parallelStream().collect(Collectors.toMap(k -> k, this::generateCteDistDFeInteresse)));
		return this;
	}

	/**
	 * Will return CteDistDFeInteresse object mapped in this specific key.
	 * 
	 * @param key
	 * @return
	 */
	public CteDistDFeInteresse getRequestObject(Integer key) {
		requestObjectsMapIsEmpty();

		return requestObjects.get(key);
	}

	/**
	 * Set values for DistDFeInt mutable object which will be retrieved from the
	 * map.
	 * 
	 * @param attributes
	 * @param key        to retrieve the object.
	 * @return
	 */
	public DistDFeCteRequestObjectsFactory setDistDFeIntAttributes(DistDFeIntAttributesFactory attributes,
			Integer key) {
		requestObjectsMapIsEmpty();

		DistDFeInt distDFeInt = getDistDFeInt(key);
		distDFeInt.setVersao(attributes.versao);
		distDFeInt.setTpAmb(attributes.tpAmb.getAmbient());
		distDFeInt.setCUFAutor(attributes.cufAutor);
		distDFeInt.setCNPJ(attributes.cnpj);
		distDFeInt.setCPF(attributes.getCpf());
		distDFeInt.setDistNSU(attributes.distNSU);
		distDFeInt.setConsNSU(attributes.getConsNSU());
		return this;
	}

	/**
	 * Ira retornar o objeto DistDFeInt englobado num CteDadosMsg ja existente.
	 * 
	 * @return
	 */
	public DistDFeInt getDistDFeInt(Integer key) {
		requestObjectsMapIsEmpty();

		return (DistDFeInt) requestObjects.get(key).getCteDadosMsg().getContent().get(0);
	}

	private CteDistDFeInteresse generateCteDistDFeInteresse(Integer i) {

		CteDistDFeInteresse cteDistDFeInteresse = new CteDistDFeInteresse();
		CteDadosMsg cteDadosMsg = new CteDadosMsg();
		cteDadosMsg.getContent().add(new DistDFeInt());
		cteDistDFeInteresse.setCteDadosMsg(cteDadosMsg);
		return cteDistDFeInteresse;
	}

	private void requestObjectsMapIsEmpty() {
		if (requestObjects.isEmpty())
			throw new IllegalArgumentException("You must set the map before get the values!");
	}

	public static class DistDFeIntAttributesFactory {
		private TipoAmbient tpAmb;
		private String cufAutor;
		private String cnpj;
		private String cpf;
		private DistNSU distNSU;
		private ConsNSU consNSU;
		private String versao;

		private DistDFeIntAttributesFactory() {
			// TODO Auto-generated constructor stub
		}

		public static DistDFeIntAttributesFactory instance() {
			return new DistDFeIntAttributesFactory();
		}

		public DistDFeIntAttributesFactory ambient(TipoAmbient tipoAmbiente) {
			this.tpAmb = tipoAmbiente;
			return this;
		}

		public DistDFeIntAttributesFactory ufAutor(String ufAutor) {
			this.cufAutor = ufAutor;
			return this;
		}

		public DistDFeIntAttributesFactory cnpj(String cnpj) {
			this.cnpj = cnpj;
			return this;
		}

		public DistDFeIntAttributesFactory cpf(String cpf) {
			this.cpf = cpf;
			return this;
		}

		public DistDFeIntAttributesFactory distNSU(String distNSU) {
			this.distNSU = new DistNSU();
			this.distNSU.setUltNSU(distNSU);
			return this;
		}

		public DistDFeIntAttributesFactory consNSU(String consNSU) {
			this.consNSU = new ConsNSU();
			this.consNSU.setNSU(consNSU);
			return this;
		}

		public DistDFeIntAttributesFactory versao(String versao) {
			this.versao = versao;
			return this;
		}

		public TipoAmbient getTpAmb() {
			return tpAmb;
		}

		public String getCufAutor() {
			return cufAutor;
		}

		public String getCnpj() {
			return cnpj;
		}

		public String getCpf() {
			return cpf;
		}

		public DistNSU getDistNSU() {
			return distNSU;
		}

		public ConsNSU getConsNSU() {
			return consNSU;
		}

		public String getVersao() {
			return versao;
		}

	}
}
