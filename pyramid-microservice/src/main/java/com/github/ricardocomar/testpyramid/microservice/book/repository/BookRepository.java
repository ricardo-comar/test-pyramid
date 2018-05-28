package com.github.ricardocomar.testpyramid.microservice.book.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.github.ricardocomar.testpyramid.microservice.book.repository.entity.BookEntity;

public interface BookRepository extends PagingAndSortingRepository<BookEntity, Long> {

	List<BookEntity> findByName(String name);
	
}
