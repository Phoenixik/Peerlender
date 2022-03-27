package com.peerlender.SecurityApplication.user.service;

import com.peerlender.SecurityApplication.user.model.User;
import com.peerlender.SecurityApplication.user.model.UserDetailsImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserAuthenticationService {

    Optional<String> login(String username, String password);
    User findByToken(String token);
    void logout(UserDetailsImpl userDetails);
}
