package ru.practicum.mainService.service.impl.publics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.mainService.error.exception.user.UserNotFoundException;
import ru.practicum.mainService.model.User;
import ru.practicum.mainService.repository.publics.UserRepositoryPublic;
import ru.practicum.mainService.service.api.publics.UserServicePublic;

import java.util.Optional;

@Service
public class UserServicePublicImpl implements UserServicePublic {

    private final UserRepositoryPublic repository;

    @Autowired
    public UserServicePublicImpl(UserRepositoryPublic repository) {
        this.repository = repository;
    }

    @Override
    public User getUserById(Long userId) {
        Optional<User> user = repository.findById(userId);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User with id=" + userId + " not found");
        }
        return user.get();
    }
}
