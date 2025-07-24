package es.cic.curso25.proyConjunto002.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.cic.curso25.proyConjunto002.model.BookPublisher;

public interface BookPublisherRepository extends JpaRepository<BookPublisher,Long>{
    
}
