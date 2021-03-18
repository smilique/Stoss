package com.epam.training.tasks.stoss.services;

import com.epam.training.tasks.stoss.entities.User;

import java.util.Optional;

public class UserService {

    public Optional<User> login(String username, String password) {
        return "admin".equals(username) && "admin".equals(password) ?
                Optional.of(new User("Anton")) : Optional.<User>empty();
    }
}
