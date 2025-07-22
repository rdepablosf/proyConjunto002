package es.cic.curso25.proyConjunto002.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.cic.curso25.proyConjunto002.model.Book;
import es.cic.curso25.proyConjunto002.repository.BookRepository;

@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;

    @Test
    void getTest(){
        Book book=new Book();
        book.setAutor("Alguno");
        book.setGenre("Misterio");
        book.setIsbn("A1234zxczxv56789");
        book.setPrice(9.99);
        book.setTitle("El misterio de alguno");
       
        bookService.create(book);

        Book bookGet=bookService.get(Long.valueOf(1));

        assertNotNull(bookGet);
    }

    @Test
    void getAllTest(){
        Book book=new Book();
        book.setAutor("Alguno");
        book.setGenre("Misterio");
        book.setIsbn("A1234856789");
        book.setPrice(9.99);
        book.setTitle("El misterio de alguno");
       
        bookService.create(book);

        List<Book> bookGet=bookService.get();

        assertNotNull(bookGet);
    }

    @Test
    void postTest(){
        Book book=new Book();
        book.setAutor("Alguno");
        book.setGenre("Misterio");
        book.setIsbn("A1234567b89");
        book.setPrice(9.99);
        book.setTitle("El misterio de alguno");
       
        Book bookComp=bookService.create(book);

        assertNotNull(bookComp);
    }

    @Test
    void putTest(){
        Book book=new Book();
        book.setAutor("Alguno");
        book.setGenre("Misterio");
        book.setIsbn("Av123456789");
        book.setPrice(9.99);
        book.setTitle("El misterio de alguno");
       
        Book bookComp=bookService.create(book);
        Book bookMirror=bookService.get(bookComp.getId());

        bookMirror.setAutor("ninguno");

        bookService.update(bookMirror);

        bookComp=bookService.get(bookMirror.getId());

        assertEquals(bookComp, bookMirror);
    }

    @Test
    void deleteTest(){
        Book book=new Book();
        book.setAutor("Alguno");
        book.setGenre("Misterio");
        book.setIsbn("A1234z56789");
        book.setPrice(9.99);
        book.setTitle("El misterio de alguno");

        Book bookComp=bookService.create(book);

        bookService.delete(bookComp.getId());

        Optional<Book> bookOp=bookRepository.findById(bookComp.getId());
        assertFalse(bookOp.isPresent());
    }
}
