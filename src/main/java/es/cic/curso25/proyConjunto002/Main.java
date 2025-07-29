package es.cic.curso25.proyConjunto002;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import es.cic.curso25.proyConjunto002.model.User;
import es.cic.curso25.proyConjunto002.repository.UserRepository;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	// @Bean
	// public CommandLineRunner init(UserRepository userRepository) {
	// 	return args -> {
	// 		User user = new User();
	// 		user.setName("Ricardo");
	// 		user.setLastName("de Pablos");
	// 		user.setEmail("rdepablos@ejemplo.com");
	// 		user.setAddress("Santoña");

	// 		userRepository.save(user);
	// 	};
	// }
}
