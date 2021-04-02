package com.farawaybr.frete.sefaz.client.distDFe.cte.deserilize;

import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.farawaybr.frete.sefaz.JaxbTemplateService;
import com.farawaybr.frete.sefaz.client.distDFe.cte.response.DistDFeCteDocDecompressed;
import com.farawaybr.frete.sefaz.client.distDFe.cte.unmarshal.UnRetDistDFeInt;
import com.farawaybr.frete.sefaz.gzip.GzipService;

import br.inf.portalfiscal.cte.procCTE.CteProc;
import br.inf.portalfiscal.cte.procevento.ProcEventoCTe;

@Scope("prototype")
@Component
public class DistDFeConhecimentoWSDeserializerImpl implements DistDFeConhecimentoWSDeserializer {

	private final GzipService gzipService;
	private final JaxbTemplateService jaxbTemplateService;

	public DistDFeConhecimentoWSDeserializerImpl(GzipService gzipService, JaxbTemplateService jaxbTemplateService) {
		super();
		this.gzipService = gzipService;
		this.jaxbTemplateService = jaxbTemplateService;
	}

	@Override
	public List<DistDFeCteDocDecompressed> deserialize(UnRetDistDFeInt ret) {
		List<DistDFeCteDocDecompressed> docs = ret.getLoteDistDFeInt().getDocsZip().parallelStream().map(doc -> {
			String rawContent = gzipService.decompress(doc.getValue());
			try {
				CteProc cteProc = null;
				ProcEventoCTe procEvento = null;
				if (doc.getSchema().equals("procCTe_v3.00.xsd")) {
					cteProc = jaxbTemplateService.unmarhall(rawContent, CteProc.class);
				} else {
					procEvento = jaxbTemplateService.unmarhall(rawContent, ProcEventoCTe.class);
				}
				return new DistDFeCteDocDecompressed(doc.getNsu(), cteProc, procEvento);
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}).collect(Collectors.toList());
		return docs;

	}

}
