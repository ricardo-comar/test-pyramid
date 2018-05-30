package com.github.ricardocomar.testpyramid.microservice.book.dataprovider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.github.ricardocomar.testpyramid.microservice.book.repository.BookRepository;
import com.github.ricardocomar.testpyramid.microservice.book.usecase.DeleteUserGateway;

@Component
@Transactional
public class DeleteUserDataProvider implements DeleteUserGateway {

	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public void delete(long id) {
		bookRepository.deleteById(id);
	}

}
