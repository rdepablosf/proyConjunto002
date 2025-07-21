package es.cic.curso25.proyConjunto002.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.cic.curso25.proyConjunto002.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

}
