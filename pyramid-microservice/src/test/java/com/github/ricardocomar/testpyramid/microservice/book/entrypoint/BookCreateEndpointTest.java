package com.github.ricardocomar.testpyramid.microservice.book.entrypoint;

import org.junit.Before;
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
import com.github.ricardocomar.testpyramid.microservice.PyramidMicroserviceApplication;
import com.github.ricardocomar.testpyramid.microservice.book.model.Book;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {EntrypointConfiguration.class})
@DirtiesContext
@SpringBootTest(classes = PyramidMicroserviceApplication.class)
@ActiveProfiles("entrypoint")
public class BookCreateEndpointTest {

    @Autowired
    private WebApplicationContext wac;

	private MockMvc mockMvc;

	final Book book = Book.builder().name("John's thoughts").writter("John Snow").price(120.0).build();

	@Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

	@Test
	public void testCreate() throws Exception {
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/book")
				.content(new ObjectMapper().writeValueAsString(book))
				.contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(book.getName()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.writter").value(book.getWritter()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(book.getPrice()))
            ;
	}

	

}
