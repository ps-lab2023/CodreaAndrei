package com.example.tradingCards.repository;

import com.example.tradingCards.model.Card;
import com.example.tradingCards.model.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {

    Card findFirstByPlayer(Player player);
    Card findFirstById(Integer id);

    Card findFirstByType(String type);
}
