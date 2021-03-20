package com.farawaybr.frete.sefaz.ctedistdfe.template;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.farawaybr.frete.domain.certificate.Certificate;
import com.farawaybr.frete.domain.certificate.CertificateSslManager;
import com.farawaybr.frete.formatmapper.xml.XmlMapperTemplate;
import com.farawaybr.frete.sefaz.ctedistdfe.CteDistDfeOperations;
import com.farawaybr.frete.sefaz.ctedistdfe.CteDistribuicaoDfeProperties;
import com.farawaybr.frete.sefaz.ctedistdfe.template.xml.CteDistDfeRequestTemplateXml;
import com.farawaybr.frete.sefaz.ctedistdfe.template.xml.CteDistDfeRequestTemplateXml.DistNsuTemplate;

@Component
public class CteDistribuicaoDfeTemplate implements CteDistDfeOperations {

	private final Logger log = LoggerFactory.getLogger(CteDistribuicaoDfeTemplate.class);

	private final CteDistribuicaoDfeProperties cteDistribuicaoDfeProperties;

	private final CertificateSslManager certificateSslManager;

	private final XmlMapperTemplate xmlMapperTemplate;

	public CteDistribuicaoDfeTemplate(CteDistribuicaoDfeProperties cteDistribuicaoDfeProperties,
			XmlMapperTemplate xmlMapperTemplate) {
		super();
		this.cteDistribuicaoDfeProperties = cteDistribuicaoDfeProperties;
		this.certificateSslManager = new CertificateSslManager();
		this.xmlMapperTemplate = xmlMapperTemplate;
	}

	public void fetch() {
		log.info("requesting to " + cteDistribuicaoDfeProperties.getUrl());

		Certificate certificate = new Certificate(
				"C:\\Users\\nicho\\Downloads\\CDG COMPONENTES AUTOMOTIVOS LTDA09512164000172.pfx",
				new char[] { 'M', '@', 's', 't', 'e', 'r', '!', '@', '#' }, "09512164000172");
		certificateSslManager.certificate(certificate);

		try (CloseableHttpClient httpclient = certificateSslManager.httpClient()) {
			CteDistDfeRequestTemplateXml reqeustTemplate = new CteDistDfeRequestTemplateXml("1", "41",
					certificate.getCnpj(), new DistNsuTemplate("00000000000000"));
			HttpPost post = new HttpPost(cteDistribuicaoDfeProperties.getUrl());

			String xml = xmlMapperTemplate.<CteDistDfeRequestTemplateXml>serialize(reqeustTemplate);

			post.setEntity(new ByteArrayEntity(xml.getBytes("UTF-8")));
			post.setHeader("Content-Type", "application/soap+xml");
			
			CloseableHttpResponse httpResponse = httpclient.execute(post);
			
			HttpEntity entity = httpResponse.getEntity();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
