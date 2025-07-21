package es.cic.curso25.proyConjunto002.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.cic.curso25.proyConjunto002.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}
