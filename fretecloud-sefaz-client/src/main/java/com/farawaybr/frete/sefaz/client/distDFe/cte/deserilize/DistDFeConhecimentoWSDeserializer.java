package com.farawaybr.frete.sefaz.client.distDFe.cte.deserilize;

import com.farawaybr.frete.domain.CertificateKeystore;
import com.farawaybr.frete.sefaz.client.distDFe.DistDFeWSDeserializer;
import com.farawaybr.frete.sefaz.client.distDFe.cte.unmarshal.UnRetDistDFeInt;
import com.farawaybr.frete.sefaz.client.distDFe.cte.unmarshal.decompressed.RetDistDFeIntDecompressed;

public interface DistDFeConhecimentoWSDeserializer
		extends DistDFeWSDeserializer<RetDistDFeIntDecompressed, UnRetDistDFeInt> {

	/**
	 * Will set the certificate to generate the RetDistDFeIntDecompressed object.
	 * 
	 * @param certificateKeystore
	 */
	void setDefaultCertificate(CertificateKeystore certificateKeystore);

	
}
