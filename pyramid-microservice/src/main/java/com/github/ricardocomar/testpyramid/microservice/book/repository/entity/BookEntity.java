package com.github.ricardocomar.testpyramid.microservice.book.repository.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.lang.NonNull;

@Entity
@Data
@Builder @NoArgsConstructor @AllArgsConstructor
public class BookEntity {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NonNull
	private String name;
	
	@NonNull
	private String writter;
	
	@NonNull
	private Double price;
}
