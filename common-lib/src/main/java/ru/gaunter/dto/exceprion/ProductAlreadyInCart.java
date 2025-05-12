package ru.gaunter.dto.exceprion;

public class ProductAlreadyInCart extends RuntimeException {
    public ProductAlreadyInCart(String detail) {
        super(detail);
    }
}
