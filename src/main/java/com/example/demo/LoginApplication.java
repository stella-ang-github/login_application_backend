package com.example.demo;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.mdbspringboot.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ComponentScan({"com.example"})
@EntityScan("com.example.model")
@EnableMongoRepositories(basePackageClasses=UserRepository.class)
@RestController
public class LoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(UserRepository userRepository) {
		return args -> {
			String username = "aaa123";
			User user = new User("Stella", username, "aaa", Role.USER);
			userRepository.findUserByUsername(username).ifPresentOrElse( u -> {
				System.out.println("user already exist.");
			}
			, () -> {
				System.out.println("Success.");
				userRepository.insert(user);
			});
		};
	}

	@GetMapping
	public String hello() {
		return "Hello World";
	}

}
