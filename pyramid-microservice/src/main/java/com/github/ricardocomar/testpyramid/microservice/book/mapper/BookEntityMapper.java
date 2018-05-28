package com.github.ricardocomar.testpyramid.microservice.book.mapper;

import com.github.ricardocomar.testpyramid.microservice.book.model.Book;
import com.github.ricardocomar.testpyramid.microservice.book.repository.entity.BookEntity;

public class BookEntityMapper {
	
	public static BookEntity from(Book book) {
		return
				BookEntity.builder()
					.id(book.getId())
					.name(book.getName())
					.writter(book.getWritter())
					.price(book.getPrice())
					.build();
	}

	public static Book from(BookEntity entity) {
		return
				Book.builder()
					.id(entity.getId())
					.name(entity.getName())
					.writter(entity.getWritter())
					.price(entity.getPrice())
					.build();
	}

}
