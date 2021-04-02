package com.farawaybr.frete.sefaz.client.distDFe.cte;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.consumingwebservice.wsdl.CteDistDFeInteresse;
import com.example.consumingwebservice.wsdl.CteDistDFeInteresse.CteDadosMsg;

import br.inf.portalfiscal.cte.DistDFeInt;
import br.inf.portalfiscal.cte.DistDFeInt.DistNSU;

@Component
@Scope("prototype")
public class DistDFeCteRequestObjectsFactory {

	private final CteDistDFeInteresse cteDistDFeInteresse;
	private final CteDadosMsg cteDadosMsg;
	private DistDFeInt distDFeInt;
	private DistNSU distNSU;

	public DistDFeCteRequestObjectsFactory() {
		this.cteDistDFeInteresse = new CteDistDFeInteresse();
		this.cteDadosMsg = new CteDadosMsg();
		this.distDFeInt = new DistDFeInt();
		this.distNSU = new DistNSU();
		this.cteDadosMsg.getContent().add(distDFeInt);

		distDFeInt.setDistNSU(distNSU);
		wrapCteDadosMsgIntoCteDistDFeInteresse();
	}

	/**
	 * Ira retornar uma referencia mutavel de DistDFeInt.
	 * 
	 * @return DistDFeInt
	 * 
	 * @author nicholas-barbosa
	 */
	public DistDFeInt getRequestObjectInstance() {
		return distDFeInt;
	}

	/**
	 * Este metodo ira atribuir o parametro ao atributo ultNSU de distNSU.
	 * 
	 * @param nsu
	 * @return
	 */
	public DistDFeCteRequestObjectsFactory setUltNsu(String nsu) {
		distNSU.setUltNSU(nsu);

		return this;
	}

	public CteDistDFeInteresse getAllRequestObject() {
		return this.cteDistDFeInteresse;
	}

	private void wrapCteDadosMsgIntoCteDistDFeInteresse() {
		cteDistDFeInteresse.setCteDadosMsg(cteDadosMsg);
	}

}
