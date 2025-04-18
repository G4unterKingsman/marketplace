package ru.gaunter.userService.service.interfaces;

import ru.gaunter.userService.dto.UserCreateDto;
import ru.gaunter.userService.dto.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserDto getByUuid(UUID uuid);
    List<UserDto> getAll();

    void create(UserCreateDto userCreateDto);
    void deleteByUuid(UUID uuid);
    void updateByUuid(UUID uuid, UserDto userDto);

    Boolean checkUserExists(UUID userId);
}
