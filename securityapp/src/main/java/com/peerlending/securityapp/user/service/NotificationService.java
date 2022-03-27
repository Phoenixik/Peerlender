package com.peerlending.securityapp.user.service;

import com.google.gson.Gson;
import com.peerlending.securityapp.user.dto.UserDTO;
import com.peerlending.securityapp.user.model.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    private final static Gson GSON = new Gson();

    public void sendMessage(UserDTO userDTO) {
        userDTO.setPassword(null);
        rabbitTemplate.convertAndSend("userRegisteredTopic", "user.registered", GSON.toJson(userDTO));
    }


}
