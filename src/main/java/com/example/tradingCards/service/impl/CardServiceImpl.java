package com.example.tradingCards.service.impl;

import com.example.tradingCards.model.*;
import com.example.tradingCards.repository.CardRepository;
import com.example.tradingCards.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardServiceImpl implements CardService{

    @Autowired
    private CardRepository cardRepository;

    public CardServiceImpl(CardRepository cardRepository){
        this.cardRepository = cardRepository;
    }

    @Override
    public void createCard(String type, String position, Double minPrice, Double maxPrice) {

        Card newCard = new Card();
        newCard.setType(type);
        newCard.setPosition(position);
        newCard.setMinPrice(minPrice);
        newCard.setMaxPrice(maxPrice);

        cardRepository.save(newCard);
    }

    @Override
    public void deleteCardById(Integer id) {
        cardRepository.delete(cardRepository.findFirstById(id));
    }


}
