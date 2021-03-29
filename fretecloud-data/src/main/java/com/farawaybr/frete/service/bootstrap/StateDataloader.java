package com.farawaybr.frete.service.bootstrap;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.farawaybr.frete.domain.State;
import com.farawaybr.frete.service.crud.certificate.StateCrudService;

@Component
@Profile("load-states")
public class StateDataloader implements CommandLineRunner {

	private final Logger log = org.slf4j.LoggerFactory.getLogger(StateDataloader.class);

	private final StateCrudService stateCrudService;

	private final RestTemplate restTemplate;

	private final IBGEConfigProperties ibgeConfigProperties;

	public StateDataloader(StateCrudService stateCrudService, RestTemplate restTemplate,
			IBGEConfigProperties ibgeConfigProperties) {
		super();
		this.stateCrudService = stateCrudService;
		this.restTemplate = restTemplate;
		this.ibgeConfigProperties = ibgeConfigProperties;
	}

	@Override
	public void run(String... args) throws Exception {
		List<StateIbgeResponse> stateResponses = getStates();
		stateCrudService.saveAll(stateResponses.parallelStream().map(this::convertToState)
				.collect(ConcurrentSkipListSet::new, Set::add, Set::addAll));
	}

	private List<StateIbgeResponse> getStates() throws InterruptedException {
		log.info("Sending http get to: " + ibgeConfigProperties.getEstadoProperties().getUrl());
		return Arrays.asList(restTemplate.getForObject(ibgeConfigProperties.getEstadoProperties().getUrl(),
				StateIbgeResponse[].class));
	}

	private State convertToState(StateIbgeResponse stateIbgeResponse) {
		return new State(null, stateIbgeResponse.getSigla(), stateIbgeResponse.getNome(), stateIbgeResponse.getId());
	}
}
