package ru.gaunter.productService.exceprion;

import javax.security.sasl.AuthenticationException;

public class JwtAuthenticationException extends AuthenticationException {
    public JwtAuthenticationException(String detail) {
        super(detail);
    }
}
