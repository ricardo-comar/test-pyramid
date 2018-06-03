package com.github.ricardocomar.testpyramid.microservice.book.dataprovider;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.github.ricardocomar.testpyramid.microservice.book.mapper.BookEntityMapper;
import com.github.ricardocomar.testpyramid.microservice.book.model.Book;
import com.github.ricardocomar.testpyramid.microservice.book.repository.BookRepository;
import com.github.ricardocomar.testpyramid.microservice.book.repository.entity.BookEntity;
import com.github.ricardocomar.testpyramid.microservice.book.usecase.BookCreateGateway;

@Component
@Transactional
public class BookCreateDataProvider implements BookCreateGateway {
	
	private static final Logger LOGGER = Logger.getLogger(BookCreateDataProvider.class.getName());

	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public Book save(Book book) {
		BookEntity entity = BookEntityMapper.from(book);
		BookEntity saved;
		try {
			saved = bookRepository.save(entity);
			return BookEntityMapper.from(saved);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Error saving book", e);
		}
		
		return null;
	}

}
