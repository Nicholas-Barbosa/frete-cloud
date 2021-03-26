package com.farawaybr.frete.sefaz.client.distDFe.cte.unmarshal;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "retDistDFeInt")
public class UnRetDistDFeInt {

	@XmlElement(namespace = "http://www.portalfiscal.inf.br/cte")
	private String tpAmb;
	@XmlElement(namespace = "http://www.portalfiscal.inf.br/cte")
	private String cStat;
	@XmlElement(namespace = "http://www.portalfiscal.inf.br/cte")
	private String xMotivo;
	@XmlElement(namespace = "http://www.portalfiscal.inf.br/cte")
	private String ultNSU;
	@XmlElement(namespace = "http://www.portalfiscal.inf.br/cte")
	private String maxNSU;

	@XmlElement(name = "loteDistDFeInt", namespace = "http://www.portalfiscal.inf.br/cte")
	private LoteDistDFeInt loteDistDFeInt;

	public TipoAmbient getTpAmb() {
		return TipoAmbient.valueOfFromNumeric(tpAmb);
	}

	public String getcStat() {
		return cStat;
	}

	public String getxMotivo() {
		return xMotivo;
	}

	public String getUltNSU() {
		return ultNSU;
	}

	public String getMaxNSU() {
		return maxNSU;
	}

	public LoteDistDFeInt getLoteDistDFeInt() {
		return loteDistDFeInt;
	}

	public static class LoteDistDFeInt {

		@XmlElement(name = "docZip", namespace = "http://www.portalfiscal.inf.br/cte")
		private List<DocZip> docsZip;

		public List<DocZip> getDocsZip() {
			return docsZip;
		}

		public static class DocZip {

			@XmlValue
			protected byte[] value;
			@XmlAttribute(name = "NSU", required = true)
			@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
			protected String nsu;
			@XmlAttribute(name = "schema", required = true)
			protected String schema;

			public byte[] getValue() {
				return value;
			}

			public String getNsu() {
				return nsu;
			}

			public String getSchema() {
				return schema;
			}

		}
	}

}
