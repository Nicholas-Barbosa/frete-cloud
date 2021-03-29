package com.farawaybr.frete.sefaz.client.distDFe.cte.unmarshal.gzip;

import br.inf.portalfiscal.cte.procCTE.CteProc;

public class DocZipDecompressed {

	private String nsu;

	private CteProc cteProc;

	public DocZipDecompressed(String nsu, CteProc cteProc) {
		super();
		this.nsu = nsu;
		this.cteProc = cteProc;
	}

	public String getNsu() {
		return nsu;
	}

	public CteProc getCteProc() {
		return cteProc;
	}

}
