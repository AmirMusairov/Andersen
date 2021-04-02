package com.musairov.shop.dao;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

import java.util.UUID;

@Data
@AllArgsConstructor
public class User {
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String login;
    private String password;
}
