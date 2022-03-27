package com.peerlender.SecurityApplication.user.controller;

import com.peerlender.SecurityApplication.user.model.User;
import com.peerlender.SecurityApplication.user.model.UserDetailsImpl;
import com.peerlender.SecurityApplication.user.repository.UserRepository;
import com.peerlender.SecurityApplication.user.service.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepositor;
    @Autowired
    private UserAuthenticationService userAuthenticationService;

    @PostMapping("/register")
    public String register(@RequestBody UserDetailsImpl userDetails) {
        userRepositor.save(new User(userDetails));
        return login(userDetails);
    }

    @PostMapping("/login")
    public  String login(@RequestBody UserDetailsImpl userDetails) {
        return  userAuthenticationService.login(userDetails.getUsername(), userDetails.getPassword())
                .orElseThrow(() -> new RuntimeException("invalid login details"));
    }

}
