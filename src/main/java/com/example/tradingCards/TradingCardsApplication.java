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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
			admin.setBalance(10);
			userRepository.save(admin);

			UserDTO adminDTO = userService.login("admin1", "1234");
			if (adminDTO != null){
				userService.createUser(admin.getRole(), "admin","User2", "aaa", User.Type.REGULAR);
				userService.login("admin", "aaa");


				userService.deleteUser(admin.getRole(), "User1");
			}

			//userService.deleteUserById(3L);
			userService.deleteUserById(200L);

			//Cards
			Card card = new Card();
			card.setType("Gold");
			card.setPosition("ST");
			card.setMaxPrice(200);
			card.setMinPrice(100);
			card.setChance(0.5);
			card.setOverall(90);
			cardRepository.save(card);

			Card card1 = new Card();
			card1.setType("Silver");
			card1.setPosition("GK");
			card1.setMaxPrice(5000);
			card1.setMinPrice(10);
			card1.setChance(0.7);
			card1.setOverall(74);
			cardRepository.save(card1);

			//Packs
			Pack pack= new Pack();
			pack.setPrice(5000);
			pack.setName("Regular pack");
			pack.setDescription("Desc");
			List<Card> cardList = new ArrayList<>();
			cardList.add(card);
			cardList.add(card1);
			pack.setCardList(cardList);
			packRepository.save(pack);

			Pack pack1 = new Pack();

			List<Pack> packList = new ArrayList<>();
			packList.add(pack);
			card.setPackList(packList);
			cardRepository.save(card);
			System.out.println("Card packList: " + card.getPackList());
			System.out.println("Pack cardList: " + pack.getCardList());
			admin.getOwnedCards().addAll(Arrays.asList(card, card1));
			System.out.println("Admin: " + admin);


		};
	}


}
