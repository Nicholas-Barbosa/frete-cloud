package com.farawaybr.frete;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.farawaybr.frete.sefaz.client.distDFe.cte.DistDFeCteRequestObjectsFactory;

@SpringBootTest
class ApplicationContextTest {

	@Autowired
	private org.springframework.beans.factory.BeanFactory bean;

	@Test
	void test() {
		System.out.println("bean " + bean.getBean(DistDFeCteRequestObjectsFactory.class));
	}

}
