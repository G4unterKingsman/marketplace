package ru.gaunter.userService.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.gaunter.userService.dto.UserDto;
import ru.gaunter.userService.entity.enums.Role;
import ru.gaunter.userService.service.UserServiceImpl;
import ru.gaunter.userService.service.interfaces.UserService;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;



@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private UserController userController;



    @Test
    void getByUuid() {
        UUID uuid = UUID.randomUUID();
        UserDto userDto =  new UserDto(
                uuid,
                "test@mail.ru",
                "asfaaf",
                "eqweqq",
                "asdaw32",
                "fasda3d",
                LocalDateTime.now(),
                "+888",
                Set.of(Role.ROLE_ADMIN, Role.ROLE_SHOP_OWNER));

        when(userService.getByUuid(uuid)).thenReturn(userDto);


        assertEquals(userDto, userController.getByUuid(uuid));
    }

    @Test
    void getAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void checkUserExists() {
    }
}