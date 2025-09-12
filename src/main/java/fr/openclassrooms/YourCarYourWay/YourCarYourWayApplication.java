package fr.openclassrooms.YourCarYourWay;

import fr.openclassrooms.YourCarYourWay.model.User;
import fr.openclassrooms.YourCarYourWay.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class YourCarYourWayApplication {

	public static void main(String[] args) {
		SpringApplication.run(YourCarYourWayApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(UserService userService) {
		return args -> {
			if (userService.findAll().isEmpty()) {
				User u1 = new User();
				u1.setName("Alice");
				u1.setEmail("alice@example.com");
				u1.setPassword("alice-hash");
				userService.save(u1);

				User u2 = new User();
				u2.setName("Bob");
				u2.setEmail("bob@example.com");
				u2.setPassword("bob-hash");
				userService.save(u2);

				System.out.println("Users Alice and Bob généré pour POC");
			}
		};
	}
}