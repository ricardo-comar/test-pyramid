package com.github.ricardocomar.testpyramid.frontend.action;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.ricardocomar.testpyramid.frontend.client.BookService;
import com.github.ricardocomar.testpyramid.frontend.mapper.BookPojoMapper;
import com.github.ricardocomar.testpyramid.frontend.model.Book;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
@AllArgsConstructor
public class BookCreateAction {

	@Autowired
	private BookService service;
	
	@HystrixCommand(groupKey = "saveAction", fallbackMethod = "errorSave")
	public Book save(Book book) {
		return BookPojoMapper.from(service.create(BookPojoMapper.from(book)).getBody());
	}
	
	public Book errorSave(Book book) {
		return null;
	}
	
	
}
