package com.weCode.bookStore.integrationTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import com.weCode.bookStore.BookStoreApplication;
import com.weCode.bookStore.dto.BookDto;
import com.weCode.bookStore.util.JwtUtil;

@SpringBootTest(classes = BookStoreApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private PasswordEncoder passwordEncoder;

	void setUpHeader() {
		String token = jwtUtil
				.generateToken(new User("vijay@gmail.com", passwordEncoder.encode("password"), new ArrayList<>()));
	
	testRestTemplate.getRestTemplate().setInterceptors(
			Collections.singletonList(((request, body, execution) -> {
				request.getHeaders().add("Authorization", "Bearer "+ token);
				return execution.execute(request, body);
			})));
	}

	@Test
	@Sql(scripts = { "classpath:insertInitialBookRecordForTest.sql" })
	void shouldReturnBooksWhenBookApiCalled() {
		setUpHeader();
		BookDto[] listOfBooks = testRestTemplate.getForObject("http://localhost:" + port + "/api/v1/books",
				BookDto[].class);

		assertThat(listOfBooks).isNotNull();
		assertThat(listOfBooks.length).isEqualTo(20);
	}

	/**
	 * @Test
	 * @Sql(scripts = {"classpath:insertInitialBookRecordForTest.sql"} ) void
	 *              shouldReturnBooksWhenBookApiCalled1() { BookDto[] listOfBooks =
	 *              testRestTemplate.getForObject(
	 *              "http://localhost:"+port+"/api/v1/books" , BookDto[].class);
	 * 
	 *              assertThat(listOfBooks).isNotNull();
	 *              assertThat(listOfBooks.length).isEqualTo(1); }
	 */

	@Test
	@Sql(scripts = { "classpath:insertInitialBookRecordForTest.sql" })
	void shouldReturnOneBookWhenCalledWithTestTitle() {
		setUpHeader();
		BookDto[] listOfBooks = testRestTemplate.getForObject("http://localhost:" + port + "/api/v1/books/test title",
				BookDto[].class);
		assertThat(listOfBooks).isNotNull();
		assertThat(listOfBooks.length).isEqualTo(1);
	}

}
