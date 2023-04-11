package com.example.tradingCards.controller;

import com.example.tradingCards.DTO.UserDTO;
import com.example.tradingCards.model.User;
import com.example.tradingCards.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")

public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = "/deleteById", method = RequestMethod.DELETE)
    public void delete(@RequestParam Long Id){
        userService.deleteUserById(Id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<UserDTO> findAll(){
        return  userService.findAll();
    }

    @RequestMapping(value = "/login")
    public ResponseEntity login(@RequestBody User user){
        UserDTO user1 = userService.login(user.getUsername(), user.getPassword());

        return ResponseEntity.status(HttpStatus.OK).body(user1);
    }





}
