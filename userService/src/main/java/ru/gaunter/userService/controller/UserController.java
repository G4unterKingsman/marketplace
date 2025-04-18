package ru.gaunter.userService.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gaunter.userService.dto.UserCreateDto;
import ru.gaunter.userService.dto.UserDto;
import ru.gaunter.userService.entity.User;
import ru.gaunter.userService.service.UserServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {


    private final UserServiceImpl userService;

    @GetMapping("/{id}")
    public UserDto getByUuid(@PathVariable UUID id){
        return userService.getByUuid(id);
    }

    @GetMapping()
    public List<UserDto> getAll(){
        return userService.getAll();
    }


    @PostMapping("/create")
    public void create(@RequestBody UserCreateDto userCreateDto){
        userService.create(userCreateDto);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable UUID id,
                       @RequestBody UserDto userDto){
        userService.updateByUuid(id,userDto);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        userService.deleteByUuid(id);
    }


    @GetMapping("/{userId}/exists")
    public ResponseEntity<Boolean> checkUserExists(@PathVariable UUID userId) {
        return ResponseEntity.ok(userService.checkUserExists(userId));
    }
}
