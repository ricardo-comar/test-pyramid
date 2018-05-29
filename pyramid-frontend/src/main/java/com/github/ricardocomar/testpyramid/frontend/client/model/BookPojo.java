package com.github.ricardocomar.testpyramid.frontend.client.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookPojo {

	private Long id;
	
	private String name;
	
	private String writter;
	
	private Double price;
}
