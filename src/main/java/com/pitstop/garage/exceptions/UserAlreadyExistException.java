package com.pitstop.garage.exceptions;

public class UserAlreadyExistException extends  RuntimeException {

    public UserAlreadyExistException(String message) {
        super(message);
    }

}
