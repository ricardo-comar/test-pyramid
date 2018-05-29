package com.github.ricardocomar.testpyramid.microservice.book.usecase;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.ricardocomar.testpyramid.microservice.book.mapper.BookEntityMapper;
import com.github.ricardocomar.testpyramid.microservice.book.model.Book;
import com.github.ricardocomar.testpyramid.microservice.book.repository.BookRepository;

@Repository
@Transactional
@AllArgsConstructor
public class BookCreateUseCase {

	@Autowired
	private BookRepository bookRepository;

	public Book create(Book book) {
		return BookEntityMapper.from(bookRepository.save(BookEntityMapper.from(book)));
	}

}