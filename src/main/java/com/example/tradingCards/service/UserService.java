package com.example.tradingCards.service;

import com.example.tradingCards.model.User;
import org.springframework.stereotype.Component;

@Component
public interface UserService {

    int login(String name, String password);
    void createUser(User.Type checkedRole, String name, String password, User.Type role);
    void deleteUser(User.Type role, String name);

}
