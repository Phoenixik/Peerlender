package com.peerlending.securityapp.user.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
        super("Username does not exist");
    }
}
