package com.github.ricardocomar.testpyramid.microservice.book.usecase;

import com.github.ricardocomar.testpyramid.microservice.book.model.Book;


public interface BookCreateGateway {

	Book save(Book book);

}
