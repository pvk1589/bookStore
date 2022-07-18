package com.weCode.bookStore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.weCode.bookStore.dto.BookDto;
import com.weCode.bookStore.model.Book;
import com.weCode.bookStore.repository.BookRepository;

@Service
public class BookService {

	private final BookRepository bookRepository;

	private final ModelMapper modelMapper;

	public BookService(BookRepository bookRepository, ModelMapper modelMapper) {
		this.bookRepository = bookRepository;
		this.modelMapper = modelMapper;
	}

	public List<BookDto> getBooks() {
		Iterable<Book> all = bookRepository.findAll();

		return StreamSupport.stream(all.spliterator(), false).map(convertBookModelToDto()).collect(Collectors.toList());

	}

	private Function<? super Book, ? extends BookDto> convertBookModelToDto() {
		return book -> modelMapper.map(book, BookDto.class);
	}
}
