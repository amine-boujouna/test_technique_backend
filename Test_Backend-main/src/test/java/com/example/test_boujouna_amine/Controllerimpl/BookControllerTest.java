package com.example.test_boujouna_amine.Controllerimpl;

import com.example.test_boujouna_amine.dto.BookDto;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.test_boujouna_amine.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Date;
@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void testCreateBookWithAllFields() throws Exception {
        BookDto bookDto = new BookDto();
        bookDto.setTitle("amine");
        bookDto.setAuthor("amine");
        bookDto.setIsbn("9781234567899");
        bookDto.setPublicationYear(new Date());

        String jsonRequest = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(post("/books/AddBook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("amine"))
                .andExpect(jsonPath("$.author").value("amine"))
                .andExpect(jsonPath("$.isbn").value("9781234567899"))
                .andExpect(jsonPath("$.publicationYear").exists());
    }

}
