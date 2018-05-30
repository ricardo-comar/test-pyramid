package com.github.ricardocomar.testpyramid.microservice.book.dataprovider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.github.ricardocomar.testpyramid.microservice.book.mapper.BookEntityMapper;
import com.github.ricardocomar.testpyramid.microservice.book.model.Book;
import com.github.ricardocomar.testpyramid.microservice.book.repository.BookRepository;
import com.github.ricardocomar.testpyramid.microservice.book.usecase.CreateUserGateway;

@Component
@Transactional
public class CreateUserDataProvider implements CreateUserGateway {

	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public Book save(Book book) {
		return BookEntityMapper.from(bookRepository.save(BookEntityMapper.from(book)));
	}

}
