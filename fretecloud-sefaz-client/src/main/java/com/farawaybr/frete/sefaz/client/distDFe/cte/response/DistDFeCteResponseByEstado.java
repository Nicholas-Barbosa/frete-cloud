package com.farawaybr.frete.sefaz.client.distDFe.cte.response;

import java.util.ArrayList;
import java.util.List;

public class DistDFeCteResponseByEstado {

	private Integer ibgeId;

	private List<DistDFeCteDocDecompressed> docsDecompressed;

	public DistDFeCteResponseByEstado(Integer ibgeId, List<DistDFeCteDocDecompressed> docsDecompressed) {
		super();
		this.ibgeId = ibgeId;
		this.docsDecompressed = docsDecompressed;
	}

	public Integer getIbgeId() {
		return ibgeId;
	}

	public List<DistDFeCteDocDecompressed> getDocsDecompressed() {
		return new ArrayList<>(docsDecompressed);
	}

}
