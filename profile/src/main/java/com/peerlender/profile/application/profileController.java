package com.peerlender.profile.application;

import com.peerlender.profile.domain.model.User;
import com.peerlender.profile.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class profileController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/user")
    public void newUser(@RequestBody User user) {
        user.setRegisterdSince(LocalDate.now());
         userRepository.save(user);
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody User user) {
        user.setRegisterdSince(LocalDate.now());
        userRepository.save(user);
    }
}
