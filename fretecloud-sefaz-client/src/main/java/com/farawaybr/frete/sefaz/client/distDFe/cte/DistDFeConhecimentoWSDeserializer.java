package com.farawaybr.frete.sefaz.client.distDFe.cte;

import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBException;

import org.springframework.stereotype.Component;

import com.farawaybr.frete.sefaz.JaxbTemplateService;
import com.farawaybr.frete.sefaz.base64.Base64Service;
import com.farawaybr.frete.sefaz.client.distDFe.cte.unmarshal.decompressed.DocZipDecompressed;
import com.farawaybr.frete.sefaz.gzip.GzipService;

import br.inf.portalfiscal.cte.RetDistDFeInt;
import br.inf.portalfiscal.cte.procCTE.CteProc;
import br.inf.portalfiscal.cte.procevento.ProcEventoCTe;

@Component
public class DistDFeConhecimentoWSDeserializer {

	private final GzipService gzipService;
	private final Base64Service base64Service;
	private final JaxbTemplateService jaxbTemplateService;

	public DistDFeConhecimentoWSDeserializer(GzipService gzipService, Base64Service base64Service,
			JaxbTemplateService jaxbTemplateService) {
		super();
		this.gzipService = gzipService;
		this.base64Service = base64Service;
		this.jaxbTemplateService = jaxbTemplateService;
	}

	public void deserialize(RetDistDFeInt ret) {
		List<DocZipDecompressed> docs = ret.getLoteDistDFeInt().getDocZip().parallelStream().map(doc -> {
			String rawContent = gzipService.decompress(base64Service.decode(doc.getValue()));

			try {
				CteProc cteProc = null;
				ProcEventoCTe procEvento = null;
				if (doc.getSchema().equals("procCTe_v3.00.xsd")) {
					cteProc = jaxbTemplateService.unmarhall(rawContent, CteProc.class);
				} else {
					procEvento = jaxbTemplateService.unmarhall(rawContent, ProcEventoCTe.class);
				}
				return new DocZipDecompressed(doc.getNSU(), cteProc, procEvento);
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}).collect(Collectors.toList());

	}
}
