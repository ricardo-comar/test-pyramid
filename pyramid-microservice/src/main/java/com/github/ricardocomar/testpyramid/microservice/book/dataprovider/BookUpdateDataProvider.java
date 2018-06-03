package com.github.ricardocomar.testpyramid.microservice.book.dataprovider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.github.ricardocomar.testpyramid.microservice.book.mapper.BookEntityMapper;
import com.github.ricardocomar.testpyramid.microservice.book.model.Book;
import com.github.ricardocomar.testpyramid.microservice.book.repository.BookRepository;
import com.github.ricardocomar.testpyramid.microservice.book.repository.entity.BookEntity;
import com.github.ricardocomar.testpyramid.microservice.book.usecase.BookUpdateGateway;


@Component
@Transactional
public class BookUpdateDataProvider implements BookUpdateGateway {

	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public Book update(Book book) {
		BookEntity entity = BookEntityMapper.from(book);
		BookEntity saved = bookRepository.save(entity);
		return BookEntityMapper.from(saved);
	}

}
