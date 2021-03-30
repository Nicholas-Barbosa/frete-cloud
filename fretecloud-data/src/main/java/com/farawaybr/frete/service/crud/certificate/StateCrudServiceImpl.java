package com.farawaybr.frete.service.crud.certificate;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.farawaybr.frete.domain.State;
import com.farawaybr.frete.repository.StateRepository;
import com.farawaybr.frete.service.crud.StateCrudService;

@Service
public class StateCrudServiceImpl implements StateCrudService {

	private final StateRepository repo;

	private final Map<Integer, State> mapStates;

	public StateCrudServiceImpl(StateRepository repo) {
		super();
		this.repo = repo;

		mapStates = repo.findAll().parallelStream()
				.collect(Collectors.toConcurrentMap(state -> state.getIbgeId(), state -> state));
	}

	@Override
	public boolean checkIfExists(Integer key) {
		return mapStates.isEmpty() ? true : mapStates.containsKey(key);
	}

	@Override
	public List<State> saveAll(Iterable<State> iterable) {
		// TODO Auto-generated method stub
		Set<State> states = new HashSet<>();
		iterable.forEach(state -> states.add(state));

		return states.parallelStream().filter(state -> !checkIfExists(state.getIbgeId())).map(state -> repo.save(state))
				.collect(Collectors.toList());
	}

	@Override
	public State save(State t) {
		// TODO Auto-generated method stub
		return checkIfExists(t.getIbgeId()) ? mapStates.get(t.getIbgeId()) : repo.save(t);
	}

}
