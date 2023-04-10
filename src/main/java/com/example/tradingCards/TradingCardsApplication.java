package com.example.tradingCards;

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
			user.setName("User1");
			user.setPassword("1234");
			user.setRole(User.Type.REGULAR);
			userRepository.save(user);

			int loginValue = userService.login("User1", "1234");
			checkLogin(loginValue);

			loginValue = userService.login("User", "");
			checkLogin(loginValue);

			//Admin actions
			User admin = new User();
			admin.setName("Admin1");
			admin.setPassword("1234");
			admin.setRole(User.Type.ADMIN);
			userRepository.save(admin);

			loginValue = userService.login("Admin1", "1234");
			if (loginValue >= 0){
				userService.createUser(admin.getRole(), "User2", "aaa", User.Type.REGULAR);
				int loginValue2 = userService.login("User2", "aaa");
				checkLogin(loginValue2);

				userService.deleteUser(admin.getRole(), "User1");
			}



		};
	}


}
