package com.djoly.hello.RESTJWT;

import com.djoly.hello.RESTJWT.model.User;
import com.djoly.hello.RESTJWT.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.nio.charset.Charset;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SpringBootApplication
public class RestJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestJwtApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository) {
		return args -> {



			Stream.of("usertest", "Julie", "Jennifer", "Helen", "Rachel").forEach(name -> {

				byte[] array = new byte[7]; // length is bounded by 7
				new Random().nextBytes(array);
				String generatedString = new String(array, Charset.forName("UTF-8"));

				User user = new User(name,
						"$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
						null);
				userRepository.save(user);
			});
			userRepository.findAll().forEach(System.out::println);
			userRepository.findUserByUsername("usertest").forEach(System.out::println);

		};
	}

}
