package ru.gaunter.userService.entity.enums;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
public enum Role implements GrantedAuthority {
    ROLE_SHOP_OWNER,
    ROLE_BUYER,
    ROLE_ADMIN;
    @Override
    public String getAuthority() {
        return name();
    }

}

