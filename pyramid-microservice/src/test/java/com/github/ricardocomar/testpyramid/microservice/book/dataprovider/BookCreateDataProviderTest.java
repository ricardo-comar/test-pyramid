package com.github.ricardocomar.testpyramid.microservice.book.dataprovider;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.ricardocomar.testpyramid.microservice.PyramidMicroserviceApplication;
import com.github.ricardocomar.testpyramid.microservice.book.model.Book;
import com.github.ricardocomar.testpyramid.microservice.book.usecase.BookCreateGateway;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DataProviderConfiguration.class})
@DirtiesContext
@SpringBootTest(classes = PyramidMicroserviceApplication.class)
@ActiveProfiles("dataprovider")
public class BookCreateDataProviderTest {

	@Autowired
	private BookCreateGateway createGateway;

	final Book book = Book.builder().name("John's thoughts").writter("John Snow").price(120.0).build();

	final Book expected = Book.builder().id(1L).name("John's thoughts").writter("John Snow").price(120.0).build();

	@Test
	public void testSuccess() throws Exception {
	
		Book created = createGateway.save(book);
		
		Assert.assertThat(created, Matchers.equalTo(expected));
	}
	
}
