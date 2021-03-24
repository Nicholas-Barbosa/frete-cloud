package com.farawaybr.frete.sefaz.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "distDFeInt", propOrder = { "versao", "tpAmb", "ufAutor", "cnpj", "distNsu" })
@XmlRootElement(name = "distDFeInt")
public class DistDFeInt {

	@XmlElement(required = true)
	private String versao = "1.00";

	@XmlElement(required = true)
	private String tpAmb = "1";

	@XmlElement(name = "cUFAutor", required = true)
	private String ufAutor = "41";

	@XmlElement(name = "CNPJ", required = true)
	private String cnpj = "09512164000172";

	@XmlElement(name = "distNSU", required = true)
	private DistNsuTemplate distNsu = new DistNsuTemplate("000000000000000");
 

	public String getVersao() {
		return versao;
	}


	public void setVersao(String versao) {
		this.versao = versao;
	}


	public String getTpAmb() {
		return tpAmb;
	}


	public void setTpAmb(String tpAmb) {
		this.tpAmb = tpAmb;
	}


	public String getUfAutor() {
		return ufAutor;
	}


	public void setUfAutor(String ufAutor) {
		this.ufAutor = ufAutor;
	}


	public String getCnpj() {
		return cnpj;
	}


	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}


	public DistNsuTemplate getDistNsu() {
		return distNsu;
	}


	public void setDistNsu(DistNsuTemplate distNsu) {
		this.distNsu = distNsu;
	}


	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlRootElement(name = "distNSU")
	public static class DistNsuTemplate {
		private String ultNsu;

		public DistNsuTemplate(String ultNsu) {
			super();
			this.ultNsu = ultNsu;
		}

		public String getUltNsu() {
			return ultNsu;
		}
	}
}
