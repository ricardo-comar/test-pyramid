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
public class BookUpdateAction {

	@Autowired
	private BookService service;
	
	@HystrixCommand(groupKey = "updateAction", fallbackMethod = "errorUpdate")
	public boolean save(Book book) {
		service.update(book.getId(), BookPojoMapper.from(book));
		return true;
	}
	
	public boolean errorSave(Book book) {
		return false;
	}
	
	
}
