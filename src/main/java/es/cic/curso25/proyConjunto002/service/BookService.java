package es.cic.curso25.proyConjunto002.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso25.proyConjunto002.model.Book;
import es.cic.curso25.proyConjunto002.repository.BookRepository;

@Service
public class BookService {

    private static final Logger LOGGER=LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookRepository bookRepository;

    //GET 
    //Obtiene todos los registros 
    public List<Book> get(){
        LOGGER.info("Leer todos los campos");
        return bookRepository.findAll();
    }

    //Obtiene un registro por id
    public Book get(Long id){
        LOGGER.info("Leer un campo con id "+id);
        return bookRepository.findById(id).orElse(null);
    }

    //POST 
    //crear un registro 
    public Book create(Book book){
        LOGGER.info("Se crea un registro");
        return bookRepository.save(book);
    }

    //PUT
    //Modificar un registro
    public void update(Book book){
        LOGGER.info("Se modifica un registro");
        bookRepository.save(book);
    }

    //DELETE
    //Eliminar un registro
    public void delete(Long id){
        LOGGER.info("Se elimina un registro");
        bookRepository.deleteById(id);
    }
}
