package com.weCode.bookStore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class BookStoreApplicationtests {

	@Test
	void contextLoads() {
		 assertThat(1).isNotNull();
		
	}

}
