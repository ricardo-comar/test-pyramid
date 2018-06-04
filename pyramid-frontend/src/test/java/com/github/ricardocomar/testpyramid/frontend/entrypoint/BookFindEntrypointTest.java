package com.github.ricardocomar.testpyramid.frontend.entrypoint;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ricardocomar.testpyramid.frontend.PyramidFrontEndApplication;
import com.github.ricardocomar.testpyramid.frontend.model.Book;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { EntrypointConfiguration.class })
@DirtiesContext
@SpringBootTest(classes = PyramidFrontEndApplication.class)
@AutoConfigureStubRunner(ids = {"com.github.ricardocomar.testpyramid.microservice:pyramid-microservice:${spring-cloud-contract.version}:stubs:8090"}, stubsMode = StubRunnerProperties.StubsMode.LOCAL)
@ActiveProfiles("entrypoint")
public class BookFindEntrypointTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	final Book book = Book.builder().id(1000l).name("test")
			.writter("test writter").price(100.00).build();
	final ObjectMapper objectMapper = new ObjectMapper();

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testFindById() throws Exception {

		this.mockMvc
				.perform(
						MockMvcRequestBuilders.get("/front/book/1000")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(book.getId()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value(book.getName()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.writter").value(book.getWritter()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.price").value(book.getPrice()));
	}
}
