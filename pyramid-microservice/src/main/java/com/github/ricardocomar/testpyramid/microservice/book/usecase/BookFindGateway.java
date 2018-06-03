package com.github.ricardocomar.testpyramid.microservice.book.usecase;

import java.util.List;

import com.github.ricardocomar.testpyramid.microservice.book.model.Book;

public interface BookFindGateway {

	List<Book> find(int first, int maxResult);

	Book find(long id);
}
