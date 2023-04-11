package com.example.tradingCards.service.impl;

import com.example.tradingCards.DTO.UserDTO;
import com.example.tradingCards.mapper.UserMapper;
import com.example.tradingCards.model.User;
import com.example.tradingCards.repository.UserRepository;
import com.example.tradingCards.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    private UserMapper userMapper;

    public UserServiceImpl (UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO findById(Long Id) {
        User user = userRepository.findById(Id)
                .orElseThrow(()
                        ->
                        new IllegalArgumentException("Invalid user id"));
        return  userMapper.mapModelToDto(user);
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().
                stream().
                map(userMapper::mapModelToDto).
                collect(Collectors.toList());
    }

    @Override
    public UserDTO login(String username, String password) {
        try {
            final User user = userRepository.findByUsernameAndPassword(username, password)
                    .<EntityNotFoundException>orElseThrow(()
                            ->
                    {
                        throw new EntityNotFoundException("Username or password is incorrect");
                    });
            System.out.println("Successful login");
            return userMapper.mapModelToDto(user);

        } catch (Exception e){
            System.out.println(e.getMessage());
        }


        return null;
    }

    @Override
    public void modifyBalance(User user, int sum) {
        try {
            int currentBalance = user.getBalance();
            int newBalance = currentBalance + sum;
            if (newBalance < 0){
                throw new RuntimeException("Balance for user " + user.getUsername() + " is too small");
            }
            user.setBalance(newBalance);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void createUser(User.Type role, String newUsername, String newName, String newPassword, User.Type newRole) {

        if(role != User.Type.ADMIN) {
            System.out.println("Not an admin!");
            return;
        }

        User newUser = new User();
        newUser.setUsername(newUsername);
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

    @Override
    public void deleteUserById(Long Id) {
        try {
            final User user = userRepository.findById(Id)
                    .orElseThrow(() ->
                    {
                        throw new EntityNotFoundException("There is no user with Id: " + Id);
                    });
            userRepository.delete(user);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


}
