package com.example.tradingCards.service;

import com.example.tradingCards.DTO.UserDTO;
import com.example.tradingCards.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {


    UserDTO findById(Long Id);
    List<UserDTO> findAll();
    UserDTO login(String username, String password);

    void modifyBalance(User user, int sum);
    void createUser(User.Type checkedRole, String username,String name, String password, User.Type role);
    void deleteUser(User.Type role, String name);
    void deleteUserById(Long Id);

}
