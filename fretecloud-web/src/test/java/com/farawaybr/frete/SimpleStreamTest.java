package com.farawaybr.frete;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

class SimpleStreamTest {

	@Test
	void test() {
		Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
		List.of(1,2,3,4,5).parallelStream(). map(x -> {

			return x;
		}).forEach(x -> { System.out.println("Thread " + Thread.currentThread().getName());});
		;
	}

}
