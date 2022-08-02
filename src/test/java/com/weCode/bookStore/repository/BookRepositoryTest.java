package com.weCode.bookStore.repository;

import java.util.List;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.weCode.bookStore.model.Book;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTest {
	
	
	
	public BookRepositoryTest() {
		super();
	}

	@Autowired
	private BookRepository bookRepository;

	@Test
	@Sql(scripts = {"classpath:insertInitialBookRecordForTest.sql"} )
	void shouldAbleToFetchAllBooksInDB() {
		Iterable<Book> all= bookRepository.findAll();
		Long totalBookCount  = StreamSupport.stream(all.spliterator(), false).count();
		Assertions.assertEquals(totalBookCount, 20);
	}
	
	@Test
	@Sql(scripts = {"classpath:insertInitialBookRecordForTest.sql"} )
	void shouldReturnOnBookWhenTitleIsTestTest() {
		
		List<Book> all = bookRepository.findBooksByTitle("test title");
		Assertions.assertEquals(all.size(),1);
	}
	
	@Test
	@Sql(scripts = {"classpath:insertInitialBookRecordForTest.sql"} )
	void shouldReturnOnBookWhenTitleIsTestTestIgnoreCase() {
		
		List<Book> all = bookRepository.findBooksByTitleIgnoreCase("test title");
		Assertions.assertEquals(all.size(),1);
	}
}
