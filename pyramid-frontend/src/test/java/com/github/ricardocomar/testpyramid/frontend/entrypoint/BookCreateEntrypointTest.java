package com.github.ricardocomar.testpyramid.frontend.entrypoint;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { EntrypointConfiguration.class })
@DirtiesContext
@SpringBootTest(classes = PyramidFrontEndApplication.class)
@ActiveProfiles("entrypoint")
public class BookCreateEntrypointTest {

	@Autowired
	private BookCreateEntrypoint entrypoint;

	@Autowired
	private WebApplicationContext wac;

	@Rule
	public WireMockRule wireMock = new WireMockRule(8090);

	private MockMvc mockMvc;

	final Book book = Book.builder().name("John's thoughts")
			.writter("John Snow").price(120.0).build();
	final Book created = Book.builder().id(123L).name("John's thoughts")
			.writter("John Snow").price(120.0).build();
	
	final ObjectMapper objectMapper = new ObjectMapper();

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testCreate() throws Exception {

		wireMock.stubFor(WireMock.post("/api/book")
				.withHeader("Content-Type", WireMock.equalTo(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.willReturn(WireMock.aResponse()
					.withHeader("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE)
					.withBody(objectMapper.writeValueAsString(created))));

		this.mockMvc
				.perform(
						MockMvcRequestBuilders.post("/front/book")
								.content(objectMapper.writeValueAsString(book))
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value(book.getName()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.writter").value(book.getWritter()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.price").value(book.getPrice()));
	}
}
