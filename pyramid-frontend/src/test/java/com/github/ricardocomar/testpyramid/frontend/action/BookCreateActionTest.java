package com.github.ricardocomar.testpyramid.frontend.action;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.ricardocomar.testpyramid.frontend.PyramidFrontEndApplication;
import com.github.ricardocomar.testpyramid.frontend.client.BookService;
import com.github.ricardocomar.testpyramid.frontend.client.model.BookPojo;
import com.github.ricardocomar.testpyramid.frontend.model.Book;
import com.github.ricardocomar.testpyramid.frontend.usecase.BookCreateUseCase;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ActionConfiguration.class})
@DirtiesContext
@SpringBootTest(classes = PyramidFrontEndApplication.class)
@ActiveProfiles("service")
public class BookCreateActionTest {

	@Autowired
	BookCreateUseCase action;
	
	@Autowired
	BookService mockService;
	
	@Before
	public void before() {
		Mockito.reset(mockService);
	}
	
	@Test
	public void testSuccess() throws Exception {
		BookPojo body = BookPojo.builder().id(123L).name("Test").price(10.0).writter("John Snow").build();
		ResponseEntity<BookPojo> pojo = new ResponseEntity<BookPojo>(body, HttpStatus.CREATED);
		Mockito.when(mockService.create(Mockito.any())).thenReturn(pojo);
		
		Book book = Book.builder().name("Test").price(10.0).writter("John Snow").build();
		Book saved = action.save(book);
		
		Book expectedBook = Book.builder().id(123L).name("Test").price(10.0).writter("John Snow").build();
		Assert.assertThat(saved, Matchers.equalTo(expectedBook));
	}
	
	@Test
	public void testError() throws Exception {
		Mockito.when(mockService.create(Mockito.any())).thenThrow(new RuntimeException("Expected error"));
		
		Book book = Book.builder().name("Test").price(10.0).writter("John Snow").build();
		Book saved = action.save(book);
		
		Assert.assertThat(saved, Matchers.nullValue());
	}
}
