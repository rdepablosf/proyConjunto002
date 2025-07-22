package es.cic.curso25.proyConjunto002.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import es.cic.curso25.proyConjunto002.model.SecurityCreationException;
import es.cic.curso25.proyConjunto002.model.SecurityModificationException;

@RestControllerAdvice
public class ControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SecurityCreationException.class)
    public void ModificationControl(){
        
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SecurityModificationException.class)
    public void CreationControl(){
        
    }
}
