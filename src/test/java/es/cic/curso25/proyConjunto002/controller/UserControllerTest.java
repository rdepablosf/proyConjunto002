package es.cic.curso25.proyConjunto002.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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
                    User registroCreado=objectMapper.readValue(respuesta, User.class);
                    
                    Optional<User> registroCreadoRealmente=userRepository.findById(registroCreado.getId());
                    assertTrue(registroCreadoRealmente.isPresent());
                });
        
    }
}
