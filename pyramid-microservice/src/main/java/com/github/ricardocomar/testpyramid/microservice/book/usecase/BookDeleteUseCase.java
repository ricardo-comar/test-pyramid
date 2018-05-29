package com.github.ricardocomar.testpyramid.microservice.book.usecase;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.ricardocomar.testpyramid.microservice.book.repository.BookRepository;

@Repository
@Transactional
@AllArgsConstructor
public class BookDeleteUseCase {

	@Autowired
	private BookRepository bookRepository;

	public void delete(long id) {
		bookRepository.deleteById(id);
	}

}
