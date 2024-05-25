package com.example.test_boujouna_amine.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Book object")
public class BookDto {
    private Long id;
    @NotBlank(message = "Title cannot be null")
    @Size(max = 255, message = "Author must be less than 255 characters")
    @Pattern(regexp = ".*\\D.*", message = "Title must contain at least one non-digit character")
    private String title;
    @NotBlank(message = "Author cannot be null")
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
