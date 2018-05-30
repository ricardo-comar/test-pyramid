package com.github.ricardocomar.testpyramid.microservice.book.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.ricardocomar.testpyramid.microservice.book.model.Book;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class BookUpdateUseCase {

	@Autowired
	private UpdateUserGateway updateUserGateway;

	public Book update(Book book) {
		return updateUserGateway.update(book);
	}

}
