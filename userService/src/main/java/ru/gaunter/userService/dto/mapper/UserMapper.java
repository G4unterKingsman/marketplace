package ru.gaunter.userService.dto.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.gaunter.userService.dto.UserCreateDto;
import ru.gaunter.userService.dto.UserDto;
import ru.gaunter.userService.entity.User;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserDto userDto);

    User toEntity(UserCreateDto userDto);


    UserDto toDto(User user);
}
