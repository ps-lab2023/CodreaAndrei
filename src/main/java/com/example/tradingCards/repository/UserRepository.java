package com.example.tradingCards.repository;

import com.example.tradingCards.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository

public interface UserRepository extends CrudRepository<User, Long> {
    User findFirstByName(String name);
}
