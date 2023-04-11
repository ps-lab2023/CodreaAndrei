package com.example.tradingCards.mapper;

import com.example.tradingCards.DTO.UserDTO;
import com.example.tradingCards.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO mapModelToDto(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setName(user.getName());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole());

        return userDTO;
    }

}
