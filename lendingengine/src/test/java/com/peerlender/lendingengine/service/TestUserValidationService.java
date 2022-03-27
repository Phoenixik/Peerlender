package com.peerlender.lendingengine.service;

import Repository.UserRepository;
import service.TokenValidationService;
import domain.exception.UserNotFoundException;
import domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ActiveProfiles;

@Profile("test")
@Primary
@Service
@ActiveProfiles(profiles = "test")
public class TestUserValidationService implements TokenValidationService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User validateTokenAndGetUser(String token) {
        return userRepository.findById(token).orElseThrow(()->new UserNotFoundException(token));
    }
}
