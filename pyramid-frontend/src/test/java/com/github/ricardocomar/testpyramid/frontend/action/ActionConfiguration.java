package com.github.ricardocomar.testpyramid.frontend.action;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.github.ricardocomar.testpyramid.frontend.client.BookService;

@Configuration
@Profile("service")
public class ActionConfiguration {
	
	@Bean @Primary
	public BookService bookService() {
		return Mockito.mock(BookService.class);
	}

}
