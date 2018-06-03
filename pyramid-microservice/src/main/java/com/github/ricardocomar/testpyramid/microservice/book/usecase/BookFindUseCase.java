package com.github.ricardocomar.testpyramid.microservice.book.usecase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.ricardocomar.testpyramid.microservice.book.model.Book;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class BookFindUseCase {

	@Autowired
	private BookFindGateway findGateway;

	public Book find(long id) {
		return findGateway.find(id);
	}

	public List<Book> find(int first, int maxResult) {
		return findGateway.find(first, maxResult);
	}

}
