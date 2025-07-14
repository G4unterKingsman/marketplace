package ru.gaunter.userService.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.gaunter.userService.dto.mapper.UserMapper;
import ru.gaunter.userService.entity.User;
import ru.gaunter.userService.entity.enums.Role;
import ru.gaunter.userService.repository.UserRepo;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepo userRepo;

    @Mock
    UserMapper userMapper;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    void getByUuid() {
        UUID uuid = UUID.randomUUID();
        User user = User.builder()
                .birthday(LocalDateTime.now())
                .email("asda@mail.ru")
                .firstname("aaaa")
                .lastname("dasd")
                .password("qweqrq")
                .phone("+123131")
                .uuid(uuid)
                .username("ddddd")
                .build();


        when(userRepo.findById(uuid)).thenReturn(Optional.of(user));


        assertEquals(user, userMapper.toEntity(userService.getByUuid(uuid)));
    }

    @Test
    void getByUsername() {
    }

    @Test
    void getAll() {
    }

    @Test
    void create() {
    }

    @Test
    void deleteByUuid() {
    }

    @Test
    void updateByUuid() {
    }

    @Test
    void checkUserExists() {
    }
}