package com.github.ricardocomar.testpyramid.microservice.book.repository.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class BookEntity {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	private String writter;
	
	private Double price;
}
