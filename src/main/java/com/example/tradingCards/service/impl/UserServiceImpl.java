package com.example.tradingCards.service.impl;

import com.example.tradingCards.model.User;
import com.example.tradingCards.repository.UserRepository;
import com.example.tradingCards.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl (UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public int login(String name, String password) {

        User user = userRepository.findFirstByName(name);

        //check if user exists
        if(user == null)
            return -2;

        //check password
        if (user.getPassword().equals(password)) {
            return 0;
        } else {
            return -1;
        }

    }

    @Override
    public void createUser(User.Type role, String newName, String newPassword, User.Type newRole) {

        if(role != User.Type.ADMIN) {
            System.out.println("Not an admin!");
            return;
        }

        User newUser = new User();
        newUser.setName(newName);
        newUser.setPassword(newPassword);
        newUser.setRole(newRole);
        userRepository.save(newUser);
    }

    @Override
    public void deleteUser(User.Type role, String name) {

        if(role != User.Type.ADMIN) {
            System.out.println("Not an admin!");
            return;
        }

        userRepository.delete(userRepository.findFirstByName(name));
    }


}
