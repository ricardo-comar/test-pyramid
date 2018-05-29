package com.github.ricardocomar.testpyramid.frontend.action;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.github.ricardocomar.testpyramid.frontend.client.BookService;

@Configuration
@ComponentScan(basePackages = "com.github.ricardocomar.testpyramid.frontend.action")
public class ActionConfiguration {
	
	@Bean
	public BookService bookService() {
		return Mockito.mock(BookService.class);
	}

}
