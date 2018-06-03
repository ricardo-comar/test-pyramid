package com.github.ricardocomar.testpyramid.microservice.book.dataprovider;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.ricardocomar.testpyramid.microservice.book.mapper.BookEntityMapper;
import com.github.ricardocomar.testpyramid.microservice.book.model.Book;
import com.github.ricardocomar.testpyramid.microservice.book.repository.BookRepository;
import com.github.ricardocomar.testpyramid.microservice.book.repository.entity.BookEntity;
import com.github.ricardocomar.testpyramid.microservice.book.usecase.BookFindGateway;

@Repository
@Transactional
public class BookFindDataProvider implements BookFindGateway {

	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public List<Book> find(int first, int maxResult) {
		return bookRepository.findAll(PageRequest.of(first, maxResult))
				.stream().map(entity -> BookEntityMapper.from(entity))
				.collect(Collectors.toList());
	}

	@Override
	public Book find(long id) {
		Optional<BookEntity> optional = bookRepository.findById(id);
		return BookEntityMapper.from(optional.get());
	}

}
