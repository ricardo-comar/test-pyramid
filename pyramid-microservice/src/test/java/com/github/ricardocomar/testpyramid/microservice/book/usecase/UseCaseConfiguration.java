package com.github.ricardocomar.testpyramid.microservice.book.usecase;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("usecase")
public class UseCaseConfiguration {
	
	@Bean @Primary
	public BookCreateGateway bookService() {
		return Mockito.mock(BookCreateGateway.class);
	}

}