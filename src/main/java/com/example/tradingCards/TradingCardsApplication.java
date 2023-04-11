package com.example.tradingCards;

import com.example.tradingCards.DTO.UserDTO;
import com.example.tradingCards.model.*;
import com.example.tradingCards.repository.*;
import com.example.tradingCards.service.CardService;
import com.example.tradingCards.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan
@EnableJpaRepositories
@SpringBootApplication
public class TradingCardsApplication {


	public static void main(String[] args){

		SpringApplication.run(TradingCardsApplication.class, args);
	}

	void checkLogin(int loginValue){

		if (loginValue >= 0){
			System.out.println("Successful login!");
		} else {
			System.out.println("Unsuccessful login!");
		}
	}

	@Bean
	CommandLineRunner init(CardRepository cardRepository, UserRepository userRepository, MarketRepository marketRepository,
						   PackRepository packRepository, PlayerRepository playerRepository,
						   UserService userService, CardService cardService){
		return args -> {

			//User login
			User user = new User();
			user.setUsername("user1");
			user.setName("User1");
			user.setPassword("1234");
			user.setRole(User.Type.REGULAR);
			userRepository.save(user);

			userService.login("user1", "1234");


			userService.login("User", "");


			//Admin actions
			User admin = new User();
			admin.setUsername("admin1");
			admin.setName("Admin1");
			admin.setPassword("1234");
			admin.setRole(User.Type.ADMIN);
			userRepository.save(admin);

			UserDTO adminDTO = userService.login("admin1", "1234");
			if (adminDTO != null){
				userService.createUser(admin.getRole(), "admin","User2", "aaa", User.Type.REGULAR);
				userService.login("admin", "aaa");


				userService.deleteUser(admin.getRole(), "User1");
			}

			//userService.deleteUserById(3L);
			userService.deleteUserById(200L);

		};
	}


}
