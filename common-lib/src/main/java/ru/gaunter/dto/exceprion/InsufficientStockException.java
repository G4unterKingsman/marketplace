package ru.gaunter.dto.exceprion;

public class InsufficientStockException extends RuntimeException{

    public InsufficientStockException(String detail) {
        super(detail);
    }
}
