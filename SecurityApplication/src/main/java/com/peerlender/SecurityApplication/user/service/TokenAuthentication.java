package com.peerlender.SecurityApplication.user.service;

import com.google.common.collect.ImmutableMap;
import com.peerlender.SecurityApplication.user.repository.UserRepository;
import com.peerlender.SecurityApplication.user.model.User;
import com.peerlender.SecurityApplication.user.model.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class TokenAuthentication implements UserAuthenticationService {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<String> login(String username, String password) {
        //returna jwt token if login info is correct
        return Optional.ofNullable(userRepository
                .findByUserDetails_Username(username))
                .filter(user ->user.get().getUserDetails().getPassword().equals(password))
                .map(user -> tokenService.expiring(ImmutableMap.of("username", username)));
    }

    @Override
    public User findByToken(String token) {
        Map<String, String> result = tokenService.verify(token);
        return userRepository.findByUserDetails_Username(result.get("username")).get();

    }

    @Override
    public void logout(UserDetailsImpl userDetails) {

    }
}
