package com.musairov.shop.service;

import com.musairov.shop.dao.User;
import com.musairov.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private User user;

    public User login(String login, String password) {
        user = userRepository.getByLoginAndPassword(login, password);
        return user;
    }

    public User registration(String login, String password) {
        user = userRepository.create(login, password);
        return user;
    }

    public User getAuthUser() {
        return user;
    }

    public void exit() {
        System.out.println("Goodbye, see you next time!");
    }
}
