package com.github.ricardocomar.testpyramid.frontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class PyramidFrontEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(PyramidFrontEndApplication.class, args);
	}
}
 