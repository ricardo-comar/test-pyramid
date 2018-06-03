package com.github.ricardocomar.testpyramid.microservice.book.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class BookDeleteUseCase {

	@Autowired
	private BookDeleteGateway deleteGateway;

	public void delete(long id) {
		deleteGateway.delete(id);
	}

}
