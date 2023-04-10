package com.example.tradingCards.DTO;

import com.example.tradingCards.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class UserDTO {

    private Long id;

    private String name;
    private String password;
    // Enum from User might need to be included
    private User.Type role;

}
