package com.everis.security.user.exceptions;

public class UserStateNotValid extends RuntimeException {
    public UserStateNotValid(String msg){
        super(msg);
    }
}