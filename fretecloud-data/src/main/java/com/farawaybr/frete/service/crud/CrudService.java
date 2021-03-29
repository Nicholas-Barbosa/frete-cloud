package com.farawaybr.frete.service.crud;

import java.util.List;

public interface CrudService<T, ID,KEY> {

	boolean checkIfExists(KEY key);

	List<T> saveAll(Iterable<T> iterable);

	T save(T t);
}
