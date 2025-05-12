package ru.gaunter.userService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateDto {

    private UUID uuid;
    private String email;
    private String username;
    private String password;


    private String firstname;
    private String lastname;
    private LocalDateTime birthday;
    private String phone;

}
