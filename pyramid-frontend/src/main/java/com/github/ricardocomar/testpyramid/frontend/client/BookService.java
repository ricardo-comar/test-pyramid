package com.github.ricardocomar.testpyramid.frontend.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.ricardocomar.testpyramid.frontend.client.model.BookPojo;

import feign.Headers;

@FeignClient(url = "http://localhost:8090/api/book", value = "bookClient")
public interface BookService {

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<BookPojo> create(@RequestBody BookPojo book);

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@Headers("Content-Type: " + MediaType.APPLICATION_JSON_UTF8_VALUE)
	public BookPojo find(@PathVariable("id") long id);

	@RequestMapping(method = RequestMethod.GET)
	public List<BookPojo> find(@RequestParam("start") Integer first,
			@RequestParam("maxResult") Integer maxResult);

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable("id") long id,
			@RequestBody BookPojo post);

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") long id);
}
