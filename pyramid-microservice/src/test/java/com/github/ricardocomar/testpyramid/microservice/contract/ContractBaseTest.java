package com.github.ricardocomar.testpyramid.microservice.contract;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.ricardocomar.testpyramid.microservice.PyramidMicroserviceApplication;
import com.github.ricardocomar.testpyramid.microservice.book.entrypoint.EntrypointConfiguration;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { EntrypointConfiguration.class })
@DirtiesContext
@SpringBootTest(classes = PyramidMicroserviceApplication.class)
@ActiveProfiles("entrypoint")
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:simpleBooks.sql")
public abstract class ContractBaseTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		RestAssuredMockMvc.mockMvc(mockMvc);
	}


}
