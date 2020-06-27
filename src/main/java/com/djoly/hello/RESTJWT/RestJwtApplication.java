package com.djoly.hello.RESTJWT;

import com.djoly.hello.RESTJWT.model.User;
import com.djoly.hello.RESTJWT.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.Charset;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SpringBootApplication
public class RestJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestJwtApplication.class, args);
	}

	@Autowired
	PasswordEncoder encoder;



	@Bean
	CommandLineRunner init(UserRepository userRepository) {


		return args -> {

			Stream.of("usertest", "Julie", "Jennifer", "Helen", "Rachel").forEach(name -> {

				User user = new User(name,
						encoder.encode("password"),
						null);
				userRepository.save(user);
			});
			userRepository.findAll().forEach(System.out::println);
			userRepository.findUserByUsername("usertest").forEach(System.out::println);

		};
	}

}
