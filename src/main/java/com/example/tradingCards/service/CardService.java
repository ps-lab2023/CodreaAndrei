package com.example.tradingCards.service;

import com.example.tradingCards.model.Market;
import com.example.tradingCards.model.Pack;
import com.example.tradingCards.model.Player;
import com.example.tradingCards.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CardService {

    void createCard(String type, String position, Double minPrice, Double maxPrice);


    void deleteCardById(Integer id);

}
