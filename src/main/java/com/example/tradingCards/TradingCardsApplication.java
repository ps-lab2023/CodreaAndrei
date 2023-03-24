package com.example.tradingCards;

import com.example.tradingCards.controller.Controller;
import com.example.tradingCards.model.*;
import com.example.tradingCards.repository.*;
import com.example.tradingCards.service.CardService;
import com.example.tradingCards.service.UserService;
import com.example.tradingCards.service.impl.UserServiceImpl;
import com.example.tradingCards.view.LoginMenu;
import com.mysql.cj.log.Log;
import jakarta.persistence.Entity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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

			Player player = new Player();
			player.setName("John");
			player.setAge(25);
			playerRepository.save(player);

			Player player2 = new Player();
			player.setName("Jack");
			player.setAge(25);
			playerRepository.save(player2);


			Card card = new Card();
			card.setType("Gold");
			card.setPlayer(player);
			cardRepository.save(card);


			Card card1 = new Card();
			card1.setType("Silver");
			card1.setPlayer(player);
			cardRepository.save(card1);

			Pack pack = new Pack();
			pack.setName("A pack");
			ArrayList<Card> cards = new ArrayList<>();
			cards.add(card);
			cards.add(card1);
			pack.setCardList(cards);

			Market market = new Market();
			market.setCard(card);
			market.setPrice(120.0);

		};
	}


}
