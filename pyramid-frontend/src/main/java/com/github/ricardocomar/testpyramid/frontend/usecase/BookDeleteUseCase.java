package com.github.ricardocomar.testpyramid.frontend.usecase;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.ricardocomar.testpyramid.frontend.client.BookService;
import com.github.ricardocomar.testpyramid.frontend.model.Book;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
@AllArgsConstructor
public class BookDeleteUseCase {

	@Autowired
	private BookService service;
	
	@HystrixCommand(groupKey = "deleteAction", fallbackMethod = "errorDelete")
	public boolean delete(Long id) {
		service.delete(id);
		return true;
	}
	
	public boolean errorDelete(Book book) {
		return false;
	}
	
	
}
