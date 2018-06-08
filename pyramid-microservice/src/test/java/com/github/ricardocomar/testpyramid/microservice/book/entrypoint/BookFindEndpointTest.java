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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.github.ricardocomar.testpyramid.microservice.PyramidMicroserviceApplication;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { EntrypointConfiguration.class })
@DirtiesContext
@SpringBootTest(classes = PyramidMicroserviceApplication.class)
@ActiveProfiles("entrypoint")
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:simpleBooks.sql")
@Transactional 
public class BookFindEndpointTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testFindExisting() throws Exception {

		this.mockMvc
			.perform(MockMvcRequestBuilders.get("/api/book/1001")
			.accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1001))
			.andExpect(MockMvcResultMatchers.jsonPath("$.writter").value("test writter"))
			;
	}
	
	@Test
	public void testNotFound() throws Exception {
		
		this.mockMvc
			.perform(MockMvcRequestBuilders.get("/api/book/10")
			.accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(MockMvcResultMatchers.status().isNotFound())
			;
	}
	
	@Test
	public void testFindSome() throws Exception {
		
		this.mockMvc
			.perform(MockMvcRequestBuilders.get("/api/book?start=0&maxResult=10")
			.accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
			;
	}
	
	@Test
	public void testFindNone() throws Exception {
		
		this.mockMvc
			.perform(MockMvcRequestBuilders.get("/api/book?start=1&maxResult=10")
			.accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(0))
			;
	}

}
