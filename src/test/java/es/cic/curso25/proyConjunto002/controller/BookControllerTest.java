package es.cic.curso25.proyConjunto002.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.body;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.cic.curso25.proyConjunto002.model.Book;
import es.cic.curso25.proyConjunto002.repository.BookRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private BookRepository bookRepository;

    @Test
    void testCreate() throws Exception{
        Book book=new Book();
        book.setAutor("Alguno");
        book.setGenre("Misterio");
        book.setIsbn("A123456789");
        book.setPrice(9.99);
        book.setTitle("El misterio de alguno");

        String jsonBook=objectMapper.writeValueAsString(book);

        mockMvc.perform(post("/book")
                .contentType("application/json")
                .content(jsonBook))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String respuesta = result.getResponse().getContentAsString();
                    Book registroCreado=objectMapper.readValue(respuesta, Book.class);
                    
                    Optional<Book> registroCreadoRealmente=bookRepository.findById(registroCreado.getId());
                    assertTrue(registroCreadoRealmente.isPresent());
                });
    }
    
    @Test
    void getTest() throws Exception{
        
    }
}

