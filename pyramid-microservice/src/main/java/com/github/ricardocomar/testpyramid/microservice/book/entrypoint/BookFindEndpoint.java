package com.github.ricardocomar.testpyramid.microservice.book.entrypoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.ricardocomar.testpyramid.microservice.book.model.Book;
import com.github.ricardocomar.testpyramid.microservice.book.usecase.BookFindUseCase;

@RestController
@RequestMapping(value = "/api/book")
public class BookFindEndpoint {

	@Autowired
	private BookFindUseCase bookAction;

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public Book find(@PathVariable long id) {
		return bookAction.find(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Book> find(@RequestParam("start") Integer first,
			@RequestParam("maxResult") Integer maxResult) {
		return bookAction.find(first, maxResult);
	}

}
