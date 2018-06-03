package com.github.ricardocomar.testpyramid.frontend.usecase;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.ricardocomar.testpyramid.frontend.client.BookService;
import com.github.ricardocomar.testpyramid.frontend.client.model.BookPojo;
import com.github.ricardocomar.testpyramid.frontend.mapper.BookPojoMapper;
import com.github.ricardocomar.testpyramid.frontend.model.Book;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
@AllArgsConstructor
public class BookCreateUseCase {

	@Autowired
	private BookService service;
	
	@HystrixCommand(groupKey = "saveAction", fallbackMethod = "errorSave")
	//TODO: sem a anotação funciona corretamente
	public Book save(Book book) {
		BookPojo pojo = BookPojoMapper.from(book);
		BookPojo response = service.create(pojo).getBody();
		return BookPojoMapper.from(response);
	}
	
	public Book errorSave(Book book) {
		return null;
	}
	
	
}
