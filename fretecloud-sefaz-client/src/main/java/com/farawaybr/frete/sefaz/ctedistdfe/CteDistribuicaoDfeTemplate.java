package com.farawaybr.frete.sefaz.ctedistdfe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CteDistribuicaoDfeTemplate implements CteDistDfeOperations {

	private final Logger log = LoggerFactory.getLogger(CteDistribuicaoDfeTemplate.class);

	private final CteDistribuicaoDfeProperties cteDistribuicaoDfeProperties;

	public CteDistribuicaoDfeTemplate(CteDistribuicaoDfeProperties cteDistribuicaoDfeProperties) {
		super();
		this.cteDistribuicaoDfeProperties = cteDistribuicaoDfeProperties;
	}

	public void receive() {
		log.info("requesting to " + cteDistribuicaoDfeProperties.getUrl());
	}
}
