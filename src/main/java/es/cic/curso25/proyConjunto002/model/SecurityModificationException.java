package es.cic.curso25.proyConjunto002.model;

public class SecurityModificationException extends RuntimeException{

    public SecurityModificationException(){

    }

    public SecurityModificationException(String message){
        super(message);
    }

    public SecurityModificationException(String message, Throwable throwable){
        super(message,throwable);
    }
}
