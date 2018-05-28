package com.github.ricardocomar.testpyramid.microservice.book;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.github.ricardocomar.testpyramid.microservice.book.model.Book;

@RestController
@RequestMapping(value = "/api/book")
public class BookEndpoint {

	@Autowired
	private BookAction bookAction;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody Book book)
			throws URISyntaxException {

		bookAction.create(book);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(book.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public Book find(@PathVariable long id) {
		return bookAction.find(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Book> find(@RequestParam("start") Integer first,
			@RequestParam("maxResult") Integer maxResult) {
		return bookAction.find(first, maxResult);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable long id,
			@RequestBody Book post) {
		bookAction.update(post);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable long id) {
		bookAction.delete(id);
		return ResponseEntity.noContent().build();
	}
}
