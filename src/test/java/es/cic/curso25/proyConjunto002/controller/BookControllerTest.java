package es.cic.curso25.proyConjunto002.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

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
    void CreateTest() throws Exception{
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
        Book book=new Book();
        book.setAutor("Alguno 2");
        book.setGenre("Misterio 2");
        book.setIsbn("A22w56789");
        book.setPrice(2.99);
        book.setTitle("El misterio de alguno 2 la venganza");

        String jsonBook=objectMapper.writeValueAsString(book);

        mockMvc.perform(post("/book")
                .contentType("application/json")
                .content(jsonBook))
                .andExpect(status().isOk());
        
       mockMvc.perform(get("/book/1"))
                .andExpect(status().isOk());
                     
    }

    @Test
    void getExceptionTest() throws Exception{
        Book book=new Book();
        book.setId(Long.valueOf(5));
        book.setAutor("Alguno 2");
        book.setGenre("Misterio 2");
        book.setIsbn("A22345q789");
        book.setPrice(2.99);
        book.setTitle("El misterio de alguno 2 la venganza");

        String jsonBook=objectMapper.writeValueAsString(book);

        mockMvc.perform(post("/book")
                .contentType("application/json")
                .content(jsonBook))
                .andExpect(status().isBadRequest());                             
    }

    @Test
    void getAllTest() throws Exception{
        Book book=new Book();
        book.setAutor("Alguno 2");
        book.setGenre("Misterio 2");
        book.setIsbn("A2234567asd89");
        book.setPrice(2.99);
        book.setTitle("El misterio de alguno 2 la venganza");

        
        String jsonBook=objectMapper.writeValueAsString(book);

        mockMvc.perform(post("/book")
                .contentType("application/json")
                .content(jsonBook))
                .andExpect(status().isOk());

        Book book2=new Book();
        book2.setAutor("Alguno 22");
        book2.setGenre("Misterio 22");
        book2.setIsbn("A2224567gggg89");
        book2.setPrice(2.29);
        book2.setTitle("El misterio de alguno 22 la venganza");

        String jsonBook2=objectMapper.writeValueAsString(book2);

        mockMvc.perform(post("/book")
                .contentType("application/json")
                .content(jsonBook2))
                .andExpect(status().isOk());

        mockMvc.perform(get("/book"))
                .andExpect(status().isOk());                 
    }

    @Test
    void updateTest() throws Exception{
        Book book=new Book();
        book.setAutor("Alguno 2");
        book.setGenre("Misterio 2");
        book.setIsbn("A22z3456789");
        book.setPrice(2.99);
        book.setTitle("El misterio de alguno 2 la venganza");

        String jsonBook=objectMapper.writeValueAsString(book);

        MvcResult mvcResult=mockMvc.perform(post("/book")
                .contentType("application/json")
                .content(jsonBook))
                .andExpect(status().isOk())
                .andReturn();                
        //se obtiene la id del registro recien creado
        Long idResult=objectMapper.readValue(mvcResult.getResponse().getContentAsString(),Book.class).getId();

        mvcResult=mockMvc.perform(get("/book/"+idResult))
                .andExpect(status().isOk())
                .andReturn(); 
        
        //se sobrescribe el autor 
        Book bookChange=objectMapper.readValue(mvcResult.getResponse().getContentAsString(),Book.class);
        bookChange.setAutor("ninguno");
        jsonBook=objectMapper.writeValueAsString(bookChange);
        //se actualiza el libro
        mockMvc.perform(put("/book")
                .contentType("application/json")
                .content(jsonBook))
                .andExpect(status().isOk());
        //se obtiene el libro
        mvcResult=mockMvc.perform(get("/book/"+idResult))
                .andExpect(status().isOk())
                .andReturn();
        Book bookResult=objectMapper.readValue(mvcResult.getResponse().getContentAsString(),Book.class);
        

        assertTrue(bookResult.getAutor().equals("ninguno"));
    }

    @Test 
    void deleteTest() throws Exception{
        Book book=new Book();
        book.setAutor("Alguno 2");
        book.setGenre("Misterio 2");
        book.setIsbn("A223456789");
        book.setPrice(2.99);
        book.setTitle("El misterio de alguno 2 la venganza");

        String jsonBook=objectMapper.writeValueAsString(book);

        MvcResult mvcResult=mockMvc.perform(post("/book")
                .contentType("application/json")
                .content(jsonBook))
                .andExpect(status().isOk())
                .andReturn();

        Long idResult=objectMapper.readValue(mvcResult.getResponse().getContentAsString(),Book.class).getId();
        mockMvc.perform(delete("/book/"+idResult))
                .andExpect(status().isOk());
    }
}

