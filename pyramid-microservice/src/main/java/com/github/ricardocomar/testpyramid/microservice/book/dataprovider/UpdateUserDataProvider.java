package com.github.ricardocomar.testpyramid.microservice.book.dataprovider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.github.ricardocomar.testpyramid.microservice.book.mapper.BookEntityMapper;
import com.github.ricardocomar.testpyramid.microservice.book.model.Book;
import com.github.ricardocomar.testpyramid.microservice.book.repository.BookRepository;
import com.github.ricardocomar.testpyramid.microservice.book.repository.entity.BookEntity;
import com.github.ricardocomar.testpyramid.microservice.book.usecase.UpdateUserGateway;


@Component
@Transactional
public class UpdateUserDataProvider implements UpdateUserGateway {

	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public Book update(Book book) {
		BookEntity savedBook = bookRepository.save(BookEntityMapper.from(book));
		return BookEntityMapper.from(savedBook);
	}

}
