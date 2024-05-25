package com.example.test_boujouna_amine.service;

import com.example.test_boujouna_amine.dto.BookDto;
import com.example.test_boujouna_amine.entity.Book;
import com.example.test_boujouna_amine.exception.BookNotFoundException;
import com.example.test_boujouna_amine.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService implements IBookService{

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public BookDto createBook(BookDto bookDto) {
        Book book = mapToEntity(bookDto);
        book = bookRepository.save(book);
        return mapToDto(book);
    }

    @Override
    public Page<BookDto> getAllBooks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> booksPage = bookRepository.findAll(pageable);

        return booksPage.map(this::mapToDto);
    }

    @Override
    public BookDto updateBook(Long bookId, BookDto updatedBookDto) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setTitle(updatedBookDto.getTitle());
            book.setAuthor(updatedBookDto.getAuthor());
            book.setIsbn(updatedBookDto.getIsbn());
            book.setPublicationYear(updatedBookDto.getPublicationYear());
            book = bookRepository.save(book);
            return mapToDto(book);
        } else {
            throw new BookNotFoundException("Book not found with id: " + bookId);
        }
    }
    @Override
    public void deleteBook(Long bookId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            bookRepository.deleteById(bookId);
        } else {
            throw new BookNotFoundException("Book not found with id: " + bookId);
        }
    }
    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() ->new BookNotFoundException("Book not found with id " + id));

    }

    private BookDto mapToDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setTitle(book.getTitle());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setPublicationYear(book.getPublicationYear());
        return bookDto;
    }
    private Book mapToEntity(BookDto bookDto) {
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setAuthor(bookDto.getAuthor());
        book.setIsbn(bookDto.getIsbn());
        book.setTitle(bookDto.getTitle());
        book.setPublicationYear(bookDto.getPublicationYear());
        return book;
    }
}
