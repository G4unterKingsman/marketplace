package ru.gaunter.dto.exceprion;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(String detail) {
        super(detail);
    }
}
