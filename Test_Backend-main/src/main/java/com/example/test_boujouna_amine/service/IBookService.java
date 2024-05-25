package com.example.test_boujouna_amine.service;

import com.example.test_boujouna_amine.dto.BookDto;
import com.example.test_boujouna_amine.entity.Book;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IBookService {
    BookDto createBook(BookDto bookDto);
    public Page<BookDto> getAllBooks(int page, int size) ;

    public BookDto updateBook(Long bookId, BookDto updatedBookDto);
    public void deleteBook(Long bookId);
    public Book getBookById(Long id);
}
