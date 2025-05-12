package ru.gaunter.userService.dto;


import lombok.Data;

import java.util.UUID;

@Data
public class JwtRequest {

    private String username;
    private String password;
    private String email;
    private String phone;
    private String uuid;
}
