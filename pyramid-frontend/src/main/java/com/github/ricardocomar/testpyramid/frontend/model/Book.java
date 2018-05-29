package com.github.ricardocomar.testpyramid.frontend.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Book {

	private Long id;
	
	private String name;
	
	private String writter;
	
	private Double price;
}
