package com.example.test_boujouna_amine.serviceimpl;

import com.example.test_boujouna_amine.dto.BookDto;
import com.example.test_boujouna_amine.entity.Book;
import com.example.test_boujouna_amine.exception.BookNotFoundException;
import com.example.test_boujouna_amine.repository.BookRepository;
import com.example.test_boujouna_amine.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import java.util.Date;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;


    @Test
    void testCreateBook() {
        BookDto bookDto = new BookDto();
        bookDto.setAuthor("Author");
        bookDto.setTitle("Title");
        bookDto.setIsbn("9781234567897");
        bookDto.setPublicationYear(new Date());

        Book book = new Book();
        book.setId(1L);
        book.setAuthor("Author");
        book.setTitle("Title");
        book.setIsbn("9781234567897");
        book.setPublicationYear(bookDto.getPublicationYear());


        when(bookRepository.save(any(Book.class))).thenReturn(book);

        BookDto createdBookDto = bookService.createBook(bookDto);


        assertNotNull(createdBookDto);
        assertEquals(book.getId(), createdBookDto.getId());
        assertEquals(book.getAuthor(), createdBookDto.getAuthor());
        assertEquals(book.getTitle(), createdBookDto.getTitle());
        assertEquals(book.getIsbn(), createdBookDto.getIsbn());
        assertEquals(book.getPublicationYear(), createdBookDto.getPublicationYear());
    }
    @Test
    void testDeleteBook() {
        Long bookId = 1L;
        Book book = new Book();
        book.setId(bookId);

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        bookService.deleteBook(bookId);

        verify(bookRepository, times(1)).deleteById(bookId);
    }
    @Test
    void testDeleteBookNotFound() {
        when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> bookService.deleteBook(100L));

        verify(bookRepository, never()).deleteById(anyLong());
    }
}
