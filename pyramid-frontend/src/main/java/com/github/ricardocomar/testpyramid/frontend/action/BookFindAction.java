package com.github.ricardocomar.testpyramid.frontend.action;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.ricardocomar.testpyramid.frontend.client.BookService;
import com.github.ricardocomar.testpyramid.frontend.mapper.BookPojoMapper;
import com.github.ricardocomar.testpyramid.frontend.model.Book;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
@AllArgsConstructor
public class BookFindAction {

	@Autowired
	private BookService service;
	
	@HystrixCommand(groupKey = "findAction", fallbackMethod = "errorFind")
	public Book find(Long id) {
		return BookPojoMapper.from(service.find(id));
	}
	
	@HystrixCommand(groupKey = "findAction", fallbackMethod = "errorFind")
	public List<Book> find(Integer first, Integer maxResult) {
		return service.find(first, maxResult).stream().map(b -> BookPojoMapper.from(b))
				.collect(Collectors.toList());
	}
	
	public Book errorFind(Book book) {
		return null;
	}
	
	
}
