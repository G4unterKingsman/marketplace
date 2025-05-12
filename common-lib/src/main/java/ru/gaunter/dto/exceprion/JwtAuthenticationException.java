package ru.gaunter.dto.exceprion;

import javax.security.sasl.AuthenticationException;

public class JwtAuthenticationException extends AuthenticationException {
    public JwtAuthenticationException(String detail) {
        super(detail);
    }
}
