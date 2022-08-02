package com.weCode.bookStore.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.weCode.bookStore.dto.BookDto;
import com.weCode.bookStore.model.Book;
import com.weCode.bookStore.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

	@InjectMocks
	private BookService bookService;

	@Mock
	private BookRepository bookRepository;

	@Mock
	private ModelMapper mapper;

	@Test
	void shouldReturnListOfBookDtoWhenGetBooksCalled() {

		List<Book> books = new ArrayList<>();
		Book book = getBook();
		books.add(book);
		BookDto bookDto = getBookDto();
		when(bookRepository.findAll()).thenReturn(books);
		when(mapper.map(book, BookDto.class)).thenReturn(bookDto);
		List<BookDto> bookDtos = bookService.getBooks();
		assertThat(1).isEqualTo(bookDtos.size());
		assertThat(bookDtos.get(0)).isNotNull().hasFieldOrPropertyWithValue("title", "test title")
				.hasFieldOrPropertyWithValue("description", "test description")
				.hasFieldOrPropertyWithValue("releaseYear", 2022);
	}
	
	@Test
	void shouldReturnBooksByBookTitleIgnoreCase() {
		List<Book> books = new ArrayList<>();
		Book book = getBook();
		books.add(book);
		BookDto bookDto = getBookDto();
		when(bookRepository.findBooksByTitleIgnoreCase(anyString())).thenReturn(books);
		when(mapper.map(book, BookDto.class)).thenReturn(bookDto);
		List<BookDto> bookDtoList  = bookService.getBooksByTitle("test title");
		assertThat(bookDtoList.size()).isEqualTo(1);
	}
	
	private Book getBook() {
		return Book.builder().title("test title").description("test description").releaseYear(2022)
				.id(UUID.randomUUID()).build();
	}

	private BookDto getBookDto() {
		return BookDto.builder().title("test title").description("test description").releaseYear(2022)
				.id(UUID.randomUUID()).build();
	}
}
