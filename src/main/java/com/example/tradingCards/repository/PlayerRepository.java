package com.example.tradingCards.repository;

import com.example.tradingCards.model.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {
    Player findFirstByName(String name);
}
