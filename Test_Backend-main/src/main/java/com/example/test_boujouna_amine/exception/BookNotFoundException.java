package com.example.test_boujouna_amine.exception;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(String message) {

        super(message);
    }
}
