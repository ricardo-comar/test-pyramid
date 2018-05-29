package com.github.ricardocomar.testpyramid.frontend.usecase;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.ricardocomar.testpyramid.frontend.client.BookService;
import com.github.ricardocomar.testpyramid.frontend.mapper.BookPojoMapper;
import com.github.ricardocomar.testpyramid.frontend.model.Book;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
@AllArgsConstructor
public class BookUpdateUseCase {

	@Autowired
	private BookService service;
	
	@HystrixCommand(groupKey = "updateAction", fallbackMethod = "errorUpdate")
	public boolean update(Book book) {
		service.update(book.getId(), BookPojoMapper.from(book));
		return true;
	}
	
	public boolean errorUpdate(Book book) {
		return false;
	}
	
	
}
