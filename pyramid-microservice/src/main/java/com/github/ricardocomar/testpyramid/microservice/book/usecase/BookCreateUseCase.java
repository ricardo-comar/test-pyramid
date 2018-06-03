package com.github.ricardocomar.testpyramid.microservice.book.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.ricardocomar.testpyramid.microservice.book.model.Book;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class BookCreateUseCase {

	@Autowired
	private BookCreateGateway createGateway;

	public Book create(Book book) {
		return createGateway.save(book);
	}

}
