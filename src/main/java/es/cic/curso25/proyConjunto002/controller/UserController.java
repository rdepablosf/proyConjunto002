package es.cic.curso25.proyConjunto002.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.cic.curso25.proyConjunto002.model.SecurityCreationException;
import es.cic.curso25.proyConjunto002.model.SecurityModificationException;
import es.cic.curso25.proyConjunto002.model.User;
import es.cic.curso25.proyConjunto002.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // crear usuario
    @PostMapping
    public User createUser(@RequestBody User user) {
        if(user.getId()!=null){
            throw new SecurityCreationException("Se ha intentado insertar un registro nuevo con una id que no es null");
        }
        return userService.createUser(user);
    }

    // listar todos los usuarios
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // modificar usuario
    @PutMapping
    public User updateUser(@RequestBody User user) {
        if(user.getId()==null){
            throw new SecurityModificationException("Se ha intentado actualizar un registro con una id null");
        }
        return userService.updateUser(user);
    }

    // borrar usuario
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}
