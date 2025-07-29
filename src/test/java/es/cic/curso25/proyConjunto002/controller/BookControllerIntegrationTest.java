package es.cic.curso25.proyConjunto002.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.cic.curso25.proyConjunto002.model.Book;
import es.cic.curso25.proyConjunto002.model.BookPublisher;
import es.cic.curso25.proyConjunto002.repository.BookPublisherRepository;
import es.cic.curso25.proyConjunto002.repository.BookRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookPublisherRepository bookPublisherRepository;

    BookPublisher bookPublisher=new BookPublisher();

    @BeforeEach
    private  void crearBookPublisher(){
        bookPublisher.setEmail("email@email");
        bookPublisher.setName("El publicador");
        bookPublisher.setPhone("34 555565859");
    }

    //POST
    @Test
    void CreateTest() throws Exception{
        
        
        Book book=new Book();
        book.setAutor("Alguno");
        book.setGenre("Misterio");
        book.setIsbn("A123456789");
        book.setPrice(9.99);
        book.setTitle("El misterio de alguno");
        book.setBookPublisher(bookPublisher);

        String jsonBook=objectMapper.writeValueAsString(book);

        mockMvc.perform(post("/book")
                .contentType("application/json")
                .content(jsonBook))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String respuesta = result.getResponse().getContentAsString();
                    Book registroCreado=objectMapper.readValue(respuesta, Book.class);
                    
                    Optional<Book> registroCreadoRealmente=bookRepository.findById(registroCreado.getIdBook());
                    assertTrue(registroCreadoRealmente.isPresent() && registroCreadoRealmente.get().getBookPublisher() != null);
                });     
    }

    @Test
    void getExceptionTest() throws Exception{
        Book book=new Book();
        book.setIdBook(Long.valueOf(5));
        book.setAutor("Alguno 2");
        book.setGenre("Misterio 2");
        book.setIsbn("A22345q789");
        book.setPrice(2.99);
        book.setTitle("El misterio de alguno 2 la venganza");

        String jsonBook=objectMapper.writeValueAsString(book);

        mockMvc.perform(post("/book")
                .contentType("application/json")
                .content(jsonBook))
                .andDo(print())
                .andExpect(status().isBadRequest());                             
    }

    //GET
    @Test
    void getTest() throws Exception{
        Book book=new Book();
        book.setAutor("Alguno 2");
        book.setGenre("Misterio 2");
        book.setIsbn("A22w56789");
        book.setPrice(2.99);
        book.setTitle("El misterio de alguno 2 la venganza");
        book.setBookPublisher(bookPublisher);

        Book bok=bookRepository.save(book);

        MvcResult mvcResult=mockMvc.perform(get("/book/"+bok.getIdBook()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        
        Book result=objectMapper.readValue(mvcResult.getResponse().getContentAsString(),Book.class);

        assertTrue(result!=null && result.getBookPublisher()!=null);
    }

    @Test
    void getAllTest() throws Exception{
        Book book=new Book();
        book.setAutor("Alguno 2");
        book.setGenre("Misterio 2");
        book.setIsbn("A2234567asd89");
        book.setPrice(2.99);
        book.setTitle("El misterio de alguno 2 la venganza");
        book.setBookPublisher(bookPublisher);
        
        BookPublisher bookPublisher2=new BookPublisher();
        bookPublisher2.setEmail("email@email");
        bookPublisher2.setName("El publicador");
        bookPublisher2.setPhone("34 555565859");

        Book book2=new Book();
        book2.setAutor("Alguno 22");
        book2.setGenre("Misterio 22");
        book2.setIsbn("A2224567gggg89");
        book2.setPrice(2.29);
        book2.setTitle("El misterio de alguno 22 la venganza");
        book2.setBookPublisher(bookPublisher2);

        

        bookRepository.save(book);
        bookRepository.save(book2);

        mockMvc.perform(get("/book"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(result -> {
                        String response = result.getResponse().getContentAsString();
                        List<Book> books = objectMapper.readValue(response,new TypeReference<List<Book>>() {});
                        assertTrue(books.size()>=2);
                });  
    }

    //PUT
    @Test
    void updateTest() throws Exception{
        Book book=new Book();
        book.setAutor("Alguno 2");
        book.setGenre("Misterio 2");
        book.setIsbn("A22z3456789");
        book.setPrice(2.99);
        book.setTitle("El misterio de alguno 2 la venganza");
        book.setBookPublisher(bookPublisher);
        
        Book bookFirstSave=bookRepository.save(book);

        bookFirstSave.setAutor("Juan antonio");
        bookFirstSave.getBookPublisher().setName("Antonio Juan"); 

        String jsonBook=objectMapper.writeValueAsString(bookFirstSave);

        //se actualiza el libro
        mockMvc.perform(put("/book")
                .contentType("application/json")
                .content(jsonBook))
                .andDo(print())
                .andExpect(status().isOk());
        //se obtiene el libro
        
        Book bok=bookRepository.findById(bookFirstSave.getIdBook()).orElse(null);
        
        assertFalse(bok.getAutor().equals(book.getAutor()) && bok.getBookPublisher().getName().equals(book.getBookPublisher().getName()));
    }

    //DELETE
    @Test 
    void deleteTest() throws Exception{
        Book book=new Book();
        book.setAutor("Alguno 2");
        book.setGenre("Misterio 2");
        book.setIsbn("A223456789");
        book.setPrice(2.99);
        book.setTitle("El misterio de alguno 2 la venganza");
        book.setBookPublisher(bookPublisher);

        Book result=bookRepository.save(book);

        mockMvc.perform(delete("/book/"+ result.getIdBook()))
                .andDo(print())
                .andExpect(status().isOk());
        
        Optional<Book> bok=bookRepository.findById(result.getIdBook());
        
        Optional<BookPublisher> resultPublisher=bookPublisherRepository.findById(result.getBookPublisher().getId());

        assertFalse(bok.isPresent() && resultPublisher.isPresent());
    }
}

