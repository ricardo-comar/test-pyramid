package com.github.ricardocomar.testpyramid.microservice.book;

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
import com.github.ricardocomar.testpyramid.microservice.book.repository.entity.BookEntity;

@Repository
@Transactional
@AllArgsConstructor
public class BookAction {

	@Autowired
	private BookRepository bookRepository;

	public void create(Book book) {
		bookRepository.save(BookEntityMapper.from(book));
	}

	public Book find(long id) {
		return BookEntityMapper.from(bookRepository.findById(id).get());
	}

	public List<Book> find(int first, int maxResult) {
		return bookRepository.findAll(PageRequest.of(first, maxResult))
				.stream().map(b -> BookEntityMapper.from(b))
				.collect(Collectors.toList());
	}

	public Book update(Book book) {
		BookEntity savedBook = bookRepository.save(BookEntityMapper.from(book));
		return BookEntityMapper.from(savedBook);
	}

	public void delete(long id) {
		bookRepository.deleteById(id);
	}

	public List<Book> findByTitle(String name) {
		return bookRepository.findByName(name).stream()
				.map(b -> BookEntityMapper.from(b))
				.collect(Collectors.toList());
	}
}
