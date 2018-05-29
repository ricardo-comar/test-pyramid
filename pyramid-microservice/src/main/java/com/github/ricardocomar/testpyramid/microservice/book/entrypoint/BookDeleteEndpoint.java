package com.github.ricardocomar.testpyramid.microservice.book.entrypoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.ricardocomar.testpyramid.microservice.book.usecase.BookDeleteUseCase;

@RestController
@RequestMapping(value = "/api/book")
public class BookDeleteEndpoint {

	@Autowired
	private BookDeleteUseCase bookAction;

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable long id) {
		bookAction.delete(id);
		return ResponseEntity.noContent().build();
	}
}
