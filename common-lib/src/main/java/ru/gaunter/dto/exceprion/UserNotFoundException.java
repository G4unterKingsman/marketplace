package ru.gaunter.dto.exceprion;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message ) {
        super(message);
    }
}
