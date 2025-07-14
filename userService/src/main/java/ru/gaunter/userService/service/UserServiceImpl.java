package ru.gaunter.userService.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.gaunter.userService.aop.Loggable;
import ru.gaunter.userService.dto.UserCreateDto;
import ru.gaunter.userService.dto.UserDto;
import ru.gaunter.userService.dto.mapper.UserMapper;
import ru.gaunter.userService.entity.enums.Role;
import ru.gaunter.userService.entity.User;
import ru.gaunter.userService.repository.UserRepo;
import ru.gaunter.userService.service.interfaces.UserService;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto getByUuid(UUID uuid) {
        return userMapper.toDto(userRepo.findById(uuid).orElseThrow());
    }

    public Optional<User> getByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public List<UserDto> getAll() {
        return userRepo.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Loggable
    public void create(UserCreateDto userCreateDto) {
        User user = userMapper.toEntity(userCreateDto);
        user.setUuid(UUID.randomUUID());
        user.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        user.setRoles(Set.of(Role.ROLE_BUYER));
        userRepo.saveUser(user);
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        userRepo.deleteUser(uuid);
    }

    @Override
    public void updateByUuid(UUID uuid, UserDto userDto) {
        User user = userMapper.toEntity(userDto);

        userRepo.updateUser(user);
        user.setRoles(userDto.getRoles());

        userRepo.saveUser(user);
    }

    @Override
    public Boolean checkUserExists(UUID userId) {
        return userRepo.findById(userId).isPresent();
    }
}
