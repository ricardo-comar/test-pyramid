package com.github.ricardocomar.testpyramid.microservice.book.usecase;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.ricardocomar.testpyramid.microservice.book.mapper.BookEntityMapper;
import com.github.ricardocomar.testpyramid.microservice.book.model.Book;
import com.github.ricardocomar.testpyramid.microservice.book.repository.BookRepository;

@Repository
@Transactional
@AllArgsConstructor
public class BookFindUseCase {

	@Autowired
	private BookRepository bookRepository;

	public Book find(long id) {
		return BookEntityMapper.from(bookRepository.findById(id).get());
	}

	public List<Book> find(int first, int maxResult) {
		return bookRepository.findAll(PageRequest.of(first, maxResult))
				.stream().map(b -> BookEntityMapper.from(b))
				.collect(Collectors.toList());
	}

}
