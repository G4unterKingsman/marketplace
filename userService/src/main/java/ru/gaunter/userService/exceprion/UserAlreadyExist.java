package ru.gaunter.userService.exceprion;

public class UserAlreadyExist extends RuntimeException{

    public UserAlreadyExist(String message) {
        super(message);
    }
}
