package es.cic.curso25.proyConjunto002.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.cic.curso25.proyConjunto002.model.Book;
import es.cic.curso25.proyConjunto002.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {

    private static final Logger LOGGER=LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    //GET
    //Obtiene todos los registros 
    @GetMapping
    public List<Book> get(){
        LOGGER.info("Leer todos los campos");
        return bookService.get();
    }

    //Obtiene un registro por id
    @GetMapping("/{id}")
    public Book get(@PathVariable long id){
        LOGGER.info("Leer todos los campos");
        return bookService.get(id);
    }

    //POST 
    //Crea un nuevo registro
    @PostMapping
    public Book post(@RequestBody Book book){
        LOGGER.info("Leer todos los campos");
        if(book.getId()!=null){
            //TODO - lanzar la excepcion personalizada con el 400 
        }
        return bookService.create(book);
    }

    //PUT
    //Modifica un registro 
    @PutMapping
    public void put(@RequestBody Book book){
        if(book.getId()==null){
            //TODO - lanzar la excepcion personalizada con el 400
        }
    }

    //DELETE 
    //Borrar un registro
    @DeleteMapping
    public void delte(@PathVariable long id){

    }
}
