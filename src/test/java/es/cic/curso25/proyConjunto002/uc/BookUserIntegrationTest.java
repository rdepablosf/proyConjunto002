package es.cic.curso25.proyConjunto002.uc;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.cic.curso25.proyConjunto002.model.Book;
import es.cic.curso25.proyConjunto002.repository.BookRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class BookUserIntegrationTest {
    

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private BookRepository bookRepository;

    @Test
    void makeRelationUserBook() throws Exception{
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
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String respuesta = result.getResponse().getContentAsString();
                    Book registroCreado=objectMapper.readValue(respuesta, Book.class);
                    
                    Optional<Book> registroCreadoRealmente=bookRepository.findById(registroCreado.getIdBook());
                    assertTrue(registroCreadoRealmente.isPresent());
                });
    }

}
