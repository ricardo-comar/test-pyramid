package com.github.ricardocomar.testpyramid.microservice.book.mapper;


import org.hamcrest.beans.SamePropertyValuesAs;
import org.junit.Assert;
import org.junit.Test;

import com.github.ricardocomar.testpyramid.microservice.book.model.Book;
import com.github.ricardocomar.testpyramid.microservice.book.repository.entity.BookEntity;

public class BookEntityMapperTest {

	final Book book = Book.builder().id(123L).name("John's thoughts").writter("John Snow").price(120.0).build();
	final BookEntity entity = BookEntity.builder().id(123L).name("John's thoughts").writter("John Snow").price(120.0).build();

	@Test
	public void testModelToEntity() {
		
		Assert.assertThat(entity, SamePropertyValuesAs.samePropertyValuesAs(BookEntityMapper.from(book)));
	}

	@Test
	public void testEntityToModel() {

		Assert.assertThat(book, SamePropertyValuesAs.samePropertyValuesAs(BookEntityMapper.from(entity)));
	}
}
