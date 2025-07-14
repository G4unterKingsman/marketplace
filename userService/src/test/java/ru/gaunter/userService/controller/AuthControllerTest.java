package ru.gaunter.userService.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.gaunter.userService.dto.UserCreateDto;
import ru.gaunter.userService.entity.User;
import ru.gaunter.userService.service.UserServiceImpl;
import ru.gaunter.userService.service.interfaces.UserService;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class AuthControllerTest {


    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private AuthController authController;


    @Test
    void createUser() {
        UUID uuid = UUID.randomUUID();
        UserCreateDto userCreateDto = UserCreateDto.builder()
                .birthday(LocalDateTime.now())
                .email("asda@mail.ru")
                .firstname("aaaa")
                .lastname("dasd")
                .password("qweqrq")
                .phone("+123131")
                .uuid(uuid)
                .username("ddddd")
                .build();

        when(userService.getByUsername(userCreateDto.getUsername())).thenReturn(Optional.of(new User()));
        when(userService.getByUsername(userCreateDto.getUsername()).isPresent()).thenReturn(true);
        ResponseEntity<?> response = authController.createUser(userCreateDto);

        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals("Успешная регистрация",response.getBody());

    }


    @Test
    void failedCreateUser() {
        UUID uuid = UUID.randomUUID();
        UserCreateDto userCreateDto = UserCreateDto.builder()
                .birthday(LocalDateTime.now())
                .email("asda@mail.ru")
                .firstname("aaaa")
                .lastname("dasd")
                .password("qweqrq")
                .phone("+123131")
                .uuid(uuid)
                .username("ddddd")
                .build();


        when(userService.getByUsername(userCreateDto.getUsername())).thenReturn(Optional.of(new User()));
        ResponseEntity<?> response = authController.createUser(userCreateDto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Пользователь с username ddddd уже существует", response.getBody());
        verify(userService, never()).create(any());

    }
}