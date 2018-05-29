package com.github.ricardocomar.testpyramid.frontend.mapper;

import com.github.ricardocomar.testpyramid.frontend.client.model.BookPojo;
import com.github.ricardocomar.testpyramid.frontend.model.Book;

public class BookPojoMapper {
	
	public static BookPojo from(Book book) {
		return
				BookPojo.builder()
					.id(book.getId())
					.name(book.getName())
					.writter(book.getWritter())
					.price(book.getPrice())
					.build();
	}

	public static Book from(BookPojo pojo) {
		return
				Book.builder()
					.id(pojo.getId())
					.name(pojo.getName())
					.writter(pojo.getWritter())
					.price(pojo.getPrice())
					.build();
	}

}
