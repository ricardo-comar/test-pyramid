package com.github.ricardocomar.testpyramid.microservice.book.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder @NoArgsConstructor @AllArgsConstructor
public class Book {

	private Long id;
	
	private String name;
	
	private String writter;
	
	private Double price;
}
