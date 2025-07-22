package es.cic.curso25.proyConjunto002.model;

public class SecurityCreationException extends RuntimeException{

    public SecurityCreationException(){

    }

    public SecurityCreationException(String message){
        super(message);
    }

    public SecurityCreationException(String message, Throwable throwable){
        super(message,throwable);
    }
}

