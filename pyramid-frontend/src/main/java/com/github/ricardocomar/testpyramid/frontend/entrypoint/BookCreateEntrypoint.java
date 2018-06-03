package com.github.ricardocomar.testpyramid.frontend.entrypoint;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.github.ricardocomar.testpyramid.frontend.model.Book;
import com.github.ricardocomar.testpyramid.frontend.usecase.BookCreateUseCase;

@RestController
@RequestMapping(value = "/front/book")
public class BookCreateEntrypoint {

	@Autowired
	private BookCreateUseCase bookAction;

	@RequestMapping(method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, 
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Book> create(@RequestBody Book book)
			throws URISyntaxException {

		Book created = bookAction.save(book);
		if (created == null) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(book.getId()).toUri();
		ResponseEntity<Book> response = ResponseEntity.created(uri).contentType(MediaType.APPLICATION_JSON_UTF8).body(created);
		return response;
	}

}
