package com.peerlender.profile.domain.event;

import com.peerlender.profile.domain.model.User;
import com.peerlender.profile.domain.repository.UserRepository;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRegisteredEventHandler {

    @Autowired
    private UserRepository userRepository;

    private final static Gson GSON = new Gson();

    private Logger LOGGER = LoggerFactory.getLogger(UserRegisteredEventHandler.class);

    public void handleUserRegistration(String userDetails) {
        User user = GSON.fromJson(userDetails, User.class);
        LOGGER.info("user {} registered", user.getUsername());
        try{
        userRepository.save(user);}
        catch (Exception e) {LOGGER.info(String.valueOf(e));
            System.out.println(e);}
    }
}