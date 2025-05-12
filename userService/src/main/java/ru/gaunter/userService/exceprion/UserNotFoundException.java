package ru.gaunter.userService.exceprion;

public class UserNotFoundException extends Exception{

    public UserNotFoundException(String message ) {
        super(message);
    }
}
