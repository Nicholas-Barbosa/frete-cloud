package com.farawaybr.frete.sefaz.client.distDFe.cte.unmarshal.decompressed;

import br.inf.portalfiscal.cte.procCTE.CteProc;
import br.inf.portalfiscal.cte.procevento.ProcEventoCTe;

public class DocZipDecompressed {

	private String nsu;

	private CteProc cteProc;

	private ProcEventoCTe procEvento;

	public DocZipDecompressed(String nsu, CteProc cteProc, ProcEventoCTe procEvento) {
		super();
		this.nsu = nsu;
		this.cteProc = cteProc;
		this.procEvento = procEvento;
	}

	public String getNsu() {
		return nsu;
	}

	public CteProc getCteProc() {
		return cteProc;
	}

	public ProcEventoCTe getProcEvento() {
		return procEvento;
	}
}
