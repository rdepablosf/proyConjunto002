package es.cic.curso25.proyConjunto002.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.cic.curso25.proyConjunto002.model.User;
import es.cic.curso25.proyConjunto002.repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Test
    void createUserTest() throws Exception {
        userRepository.deleteAll();

        User user = new User();
        user.setName("Ricardo");
        user.setLastName("de Pablos");
        user.setEmail("rdepablos@ejemplo.com");
        user.setAddress("Santoña");

        String jsonUser = objectMapper.writeValueAsString(user);

        mockMvc.perform(post("/users")
                .contentType("application/json")
                .content(jsonUser))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String respuesta = result.getResponse().getContentAsString();
                    User userCreado = objectMapper.readValue(respuesta, User.class);

                    Optional<User> userGuardado = userRepository.findById(userCreado.getId());
                    assertTrue(userGuardado.isPresent());
                });
    }

    @Test
    void findUsersByLastNameTest() throws Exception {
        userRepository.deleteAll();

        User user1 = new User();
        user1.setName("Ricardo");
        user1.setLastName("de Pablos");
        user1.setEmail("rdepablos1@ejemplo.com");
        user1.setAddress("Santoña");
        userRepository.save(user1);

        User user2 = new User();
        user2.setName("Lola");
        user2.setLastName("Fernández");
        user2.setEmail("lola@ejemplo.com");
        user2.setAddress("Bilbao");
        userRepository.save(user2);

        User user3 = new User();
        user3.setName("Luis");
        user3.setLastName("García");
        user3.setEmail("luis@ejemplo.com");
        user3.setAddress("Madrid");
        userRepository.save(user3);

        mockMvc.perform(get("/users/lastname/de Pablos")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String json = result.getResponse().getContentAsString();

                    List<User> users = objectMapper.readValue(json, new TypeReference<List<User>>() {});

                    assertFalse(users.isEmpty());
                    for (User user : users) {
                        assertEquals("de Pablos", user.getLastName());
                    }
                });
    }

    @Test
    void deleteUserTest() throws Exception {
        userRepository.deleteAll();

        User user = new User();
        user.setName("Ricardo");
        user.setLastName("de Pablos");
        user.setEmail("rdepablos@ejemplo.com");
        user.setAddress("Santoña");
        user = userRepository.save(user);

        mockMvc.perform(delete("/users/" + user.getId()))
                .andExpect(status().isOk());

        Optional<User> eliminado = userRepository.findById(user.getId());
        assertFalse(eliminado.isPresent());
    }
}
