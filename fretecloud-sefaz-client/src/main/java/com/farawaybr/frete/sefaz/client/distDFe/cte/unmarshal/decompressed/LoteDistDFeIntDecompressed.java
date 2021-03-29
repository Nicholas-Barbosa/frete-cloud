package com.farawaybr.frete.sefaz.client.distDFe.cte.unmarshal.decompressed;

import java.util.ArrayList;
import java.util.List;

public class LoteDistDFeIntDecompressed {

	private List<DocZipDecompressed> docsDecompressed;

	public LoteDistDFeIntDecompressed(List<DocZipDecompressed> docs) {
		super();
		this.docsDecompressed = new ArrayList<>(docs);
	}

	public List<DocZipDecompressed> getDocsDecompressed() {
		return new ArrayList<>(docsDecompressed);
	}

}
