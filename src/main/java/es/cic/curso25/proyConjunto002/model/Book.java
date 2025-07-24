package es.cic.curso25.proyConjunto002.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_book")
    private Long idBook;
    @Column(name="isbn",nullable = false, unique=true)
    private String isbn;
    @Column(name="title",length = 50)
    private String title;
    @Column(name="autor",length = 50)
    private String autor;
    @Column(name="genre",length = 20)
    private String genre;
    @Column(name="price")
    private double price;

    public Book() {
    }
    public Book(Long idBook, String isbn, String title, String autor, String genre, double price) {
        this.idBook = idBook;
        this.isbn = isbn;
        this.title = title;
        this.autor = autor;
        this.genre = genre;
        this.price = price;
    }
    
    public Long getId() {
        return idBook;
    }
    public void setId(Long idBook) {
        this.idBook = idBook;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    
    @Override
    public String toString() {
        return "Book [idBook=" + idBook + ", isbn=" + isbn + ", title=" + title + ", autor=" + autor + ", genre=" + genre
                + ", price=" + price + "]";
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idBook == null) ? 0 : idBook.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Book other = (Book) obj;
        if (idBook == null) {
            if (other.idBook != null)
                return false;
        } else if (!idBook.equals(other.idBook))
            return false;
        return true;
    }
    
    

}
