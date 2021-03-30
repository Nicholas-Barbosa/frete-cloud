package com.farawaybr.frete.sefaz.client.distDFe.cte.deserilize;

import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.farawaybr.frete.domain.CertificateKeystore;
import com.farawaybr.frete.sefaz.JaxbTemplateService;
import com.farawaybr.frete.sefaz.client.distDFe.cte.unmarshal.UnRetDistDFeInt;
import com.farawaybr.frete.sefaz.client.distDFe.cte.unmarshal.decompressed.DocZipDecompressed;
import com.farawaybr.frete.sefaz.client.distDFe.cte.unmarshal.decompressed.LoteDistDFeIntDecompressed;
import com.farawaybr.frete.sefaz.client.distDFe.cte.unmarshal.decompressed.RetDistDFeIntDecompressed;
import com.farawaybr.frete.sefaz.gzip.GzipService;

import br.inf.portalfiscal.cte.procCTE.CteProc;
import br.inf.portalfiscal.cte.procevento.ProcEventoCTe;

@Scope("prototype")
@Component
public class DistDFeConhecimentoWSDeserializerImpl implements DistDFeConhecimentoWSDeserializer {

	private final GzipService gzipService;
	private final JaxbTemplateService jaxbTemplateService;

	private CertificateKeystore certificateKeystore;

	public DistDFeConhecimentoWSDeserializerImpl(GzipService gzipService, JaxbTemplateService jaxbTemplateService) {
		super();
		this.gzipService = gzipService;
		this.jaxbTemplateService = jaxbTemplateService;
	}

	@Override
	public RetDistDFeIntDecompressed deserialize(UnRetDistDFeInt ret, Integer ibgeStateId) {
		List<DocZipDecompressed> docs = ret.getLoteDistDFeInt().getDocsZip().parallelStream().map(doc -> {
			String rawContent = gzipService.decompress(doc.getValue());
			System.out.println("Raw: " + rawContent);
			try {
				CteProc cteProc = null;
				ProcEventoCTe procEvento = null;
				if (doc.getSchema().equals("procCTe_v3.00.xsd")) {
					cteProc = jaxbTemplateService.unmarhall(rawContent, CteProc.class);
				} else {
					procEvento = jaxbTemplateService.unmarhall(rawContent, ProcEventoCTe.class);
				}
				return new DocZipDecompressed(doc.getNsu(), cteProc, procEvento);
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}).collect(Collectors.toList());
		LoteDistDFeIntDecompressed loteDistDFeIntDecompressed = new LoteDistDFeIntDecompressed(docs);
		return new RetDistDFeIntDecompressed(loteDistDFeIntDecompressed, certificateKeystore, ibgeStateId);

	}

	@Override
	public List<RetDistDFeIntDecompressed> deserialize(List<UnRetDistDFeInt> ret, Integer ibgeStateId) {
		// TODO Auto-generated method stub
		return ret.parallelStream().map(retElement -> this.deserialize(retElement, ibgeStateId))
				.collect(Collectors.toList());
	}

	@Override
	public void setDefaultCertificate(CertificateKeystore certificateKeystore) {
		this.certificateKeystore = certificateKeystore;

	}

}
