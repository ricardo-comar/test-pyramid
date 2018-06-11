package com.github.ricardocomar.testpyramid.microservice.book.usecase;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.ricardocomar.testpyramid.microservice.PyramidMicroserviceApplication;
import com.github.ricardocomar.testpyramid.microservice.book.model.Book;

@RunWith(SpringRunner.class)
@DirtiesContext
@SpringBootTest(classes = PyramidMicroserviceApplication.class)
@ActiveProfiles("usecase")
public class BookCreateUseCaseTest {

	@Autowired
	private BookCreateGateway createGateway;

	@Autowired
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