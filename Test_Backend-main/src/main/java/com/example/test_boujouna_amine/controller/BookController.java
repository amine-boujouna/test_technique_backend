package com.example.test_boujouna_amine.controller;

import com.example.test_boujouna_amine.dto.BookDto;
import com.example.test_boujouna_amine.entity.Book;
import com.example.test_boujouna_amine.service.BookService;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;





@RestController
@RequestMapping("/books")
@Validated
@CrossOrigin(origins = "http://localhost:4200")

public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Operation(summary = "Save new book")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Book created successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request")})
    @PostMapping("/AddBook")
    public ResponseEntity<BookDto> createBook(
            @RequestBody @Valid BookDto bookDto) {
        BookDto createdBook = bookService.createBook(bookDto);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }


    @Operation(summary = "Get all books")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Books retrieved successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")})
    @GetMapping("/GetAllBooks")
    public ResponseEntity<Page<BookDto>> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<BookDto> booksPage = bookService.getAllBooks(page, size);
        return new ResponseEntity<>(booksPage, HttpStatus.OK);
    }


    @Operation(summary = "Update book by id")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Book updated successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Book not found")})
    @PutMapping("/updatebook/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable("id") Long bookId, @RequestBody @Valid BookDto updatedBookDto) {
        BookDto updatedBook = bookService.updateBook(bookId, updatedBookDto);
        return ResponseEntity.ok(updatedBook);
    }


    @Operation(summary = "Delete book by id")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "Book deleted successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "book not found")})
    @DeleteMapping("/deletebook/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Long bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Get book by id")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Book retrieved successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Book not found")})

    @GetMapping("/GetbookbyId/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

}
