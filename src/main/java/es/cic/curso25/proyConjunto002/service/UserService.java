package es.cic.curso25.proyConjunto002.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso25.proyConjunto002.model.User;
import es.cic.curso25.proyConjunto002.repository.UserRepository;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    // crear usuario
    public User createUser(User user) {
        LOGGER.info("Usuario creado");
        return userRepository.save(user);
    }

    // listar todos los usuarios
    public List<User> getAllUsers() {
        LOGGER.info("Leer todos los usuarios");
        return userRepository.findAll();
    }
    
    // modificar usuario
    public User updateUser(User user) {
        LOGGER.info("Usuario creado");
        return userRepository.save(user);
    }

    // borrar usuario
    public void deleteUser(Long id) {
        LOGGER.info("Usuario eliminado");
        userRepository.deleteById(id);
    }

}
