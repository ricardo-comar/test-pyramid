package com.github.ricardocomar.testpyramid.frontend.entrypoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.ricardocomar.testpyramid.frontend.model.Book;
import com.github.ricardocomar.testpyramid.frontend.usecase.BookUpdateUseCase;

@RestController
@RequestMapping(value = "/front/book")
public class BookUpdateEntrypoint {

	@Autowired
	private BookUpdateUseCase bookAction;

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable long id,
			@RequestBody Book post) {
		bookAction.update(post);
		return ResponseEntity.noContent().build();
	}
}
