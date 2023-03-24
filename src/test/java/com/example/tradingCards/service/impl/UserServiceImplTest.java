package com.example.tradingCards.service.impl;

import com.example.tradingCards.model.User;
import com.example.tradingCards.repository.UserRepository;
import com.example.tradingCards.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class UserServiceImplTest {

    private UserRepository userRepository;
    private UserService userService;
    @Autowired
    public UserServiceImplTest (UserRepository userRepository, UserService userService) { this.userRepository = userRepository; this.userService = userService; }

    @Test
    void login() {

        User user = new User();
        user.setName("John");
        user.setPassword("1234");
        user.setRole(User.Type.REGULAR);
        userRepository.save(user);

        assertThat(userService.login("John Doe", "1234")).isEqualTo(0);
        assertThat(userService.login("Johnny Doe", "123")).isEqualTo(-1);
        assertThat(userService.login("Random", "1234")).isEqualTo(-2);

    }

    @Test
    void createUser() {

        User user = new User();
        user.setName("John One");
        user.setPassword("1234");
        user.setRole(User.Type.ADMIN);

        userService.createUser(user.getRole(), "John Two", "", User.Type.REGULAR);

        assertThat(userRepository.findFirstByName("John Two")).isNotNull();

        User user1 = new User();
        user.setName("John Three");
        user.setPassword("1234");
        user.setRole(User.Type.REGULAR);

        userService.createUser(user.getRole(), "John Four", "", User.Type.REGULAR);

        assertThat(userRepository.findFirstByName("John Four")).isNull();
    }

    @Test
    void deleteUser() {

        User user = new User();
        user.setName("John One");
        user.setPassword("1234");
        user.setRole(User.Type.ADMIN);

        User user2 = new User();
        user2.setName("John Two");
        user2.setPassword("1234");
        user2.setRole(User.Type.REGULAR);
        userRepository.save(user2);

        userService.deleteUser(user.getRole(), "John Two");

        assertThat(userRepository.findFirstByName("John Two")).isNull();

        User user1 = new User();
        user1.setName("John Three");
        user1.setPassword("1234");
        user1.setRole(User.Type.REGULAR);
        userRepository.save(user1);
        userRepository.save(user2);

        userService.deleteUser(user2.getRole(), "John Three");

        assertThat(userRepository.findFirstByName("John Three")).isNotNull();

    }
}