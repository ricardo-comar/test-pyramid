package com.github.ricardocomar.testpyramid.microservice.book.usecase;

import com.github.ricardocomar.testpyramid.microservice.book.model.Book;


public interface CreateUserGateway {

	Book save(Book book);

}
