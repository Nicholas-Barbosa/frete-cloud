package com.farawaybr.frete.sefaz.client.distDFe.cte.unmarshal.decompressed;

import java.util.List;

public class EstadoDistDFeResponse {

	private Integer ibgeId;

	private List<DocZipDecompressed> docsDecompressed;

	public EstadoDistDFeResponse(Integer ibgeId, List<DocZipDecompressed> docsDecompressed) {
		super();
		this.ibgeId = ibgeId;
		this.docsDecompressed = docsDecompressed;
	}

	public Integer getIbgeId() {
		return ibgeId;
	}

	public List<DocZipDecompressed> getDocsDecompressed() {
		return docsDecompressed;
	}

}
