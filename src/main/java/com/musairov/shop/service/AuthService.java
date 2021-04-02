package com.musairov.shop.service;

import com.musairov.shop.dao.User;
import com.musairov.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    public User login(String login, String password) {
        return userRepository.getByLoginAndPassword(login, password);
    }

    public User registration(String login, String password) {
        return userRepository.create(login, password);
    }

    public void exit() {
        System.out.println("Goodbye, see you next time!");
    }
}
