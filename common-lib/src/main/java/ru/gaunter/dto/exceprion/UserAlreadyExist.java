package ru.gaunter.dto.exceprion;

public class UserAlreadyExist extends RuntimeException{

    public UserAlreadyExist(String message) {
        super(message);
    }
}
