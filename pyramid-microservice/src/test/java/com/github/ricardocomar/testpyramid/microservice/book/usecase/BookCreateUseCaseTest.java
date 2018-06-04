package com.github.ricardocomar.testpyramid.microservice.book.usecase;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.github.ricardocomar.testpyramid.microservice.book.model.Book;

@RunWith(MockitoJUnitRunner.class)
public class BookCreateUseCaseTest { 

	@Mock
	private BookCreateGateway createGateway;

	@InjectMocks
	private BookCreateUseCase useCase;

	final Book book = Book.builder().name("John's thoughts").writter("John Snow").price(120.0).build();

	final Book created = Book.builder().id(123L).name("John's thoughts").writter("John Snow").price(120.0).build();

	@Test
	public void testSuccess() throws Exception {
	
		Mockito.when(createGateway.save(Mockito.eq(book))).thenReturn(created);
		
		Book newBook = useCase.create(book);
		
		Assert.assertThat(newBook, Matchers.equalTo(created));
	}
	
	@Test
	public void testNull() throws Exception {
		
		Mockito.when(createGateway.save(Mockito.eq(book))).thenReturn(null);
		
		Book newBook = useCase.create(book);
		
		Assert.assertThat(newBook, Matchers.nullValue());
		
	}
}
