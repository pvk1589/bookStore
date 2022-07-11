package com.weCode.bookStore.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weCode.bookStore.dto.BookDto;

@RestController
@RequestMapping("api/v1/books")
public class BookController {

	@GetMapping
	public ResponseEntity<List<BookDto>> getBooks() {
		BookDto bookDto = new BookDto();
		bookDto.setTitle("My First book title");

		List<BookDto> books = List.of(bookDto);

		return ResponseEntity.ok(books);

	}

}
