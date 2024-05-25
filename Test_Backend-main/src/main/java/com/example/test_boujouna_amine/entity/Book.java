package com.example.test_boujouna_amine.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="Books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title must be less than 255 characters")
    @Pattern(regexp = ".*\\D.*", message = "Title must contain at least one non-digit character")
    private String title;
    @NotBlank(message = "Author is required")
    @Size(max = 255, message = "Author must be less than 255 characters")
    @Pattern(regexp = ".*\\D.*", message = "Author must contain at least one non-digit character")
    private String author;
    @NotNull(message = "Publication year cannot be null")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date publicationYear;
    @NotBlank(message = "ISBN is required")
    @Pattern(regexp = "^(97(8|9))?\\d{9}(\\d|X)$", message = "Invalid ISBN format")
    private String isbn;


}