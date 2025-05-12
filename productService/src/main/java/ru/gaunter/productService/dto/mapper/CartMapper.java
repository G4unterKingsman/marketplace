package ru.gaunter.productService.dto.mapper;


import org.mapstruct.Mapper;
import ru.gaunter.dto.CartDto;
import ru.gaunter.productService.entity.CartEntity;

@Mapper
public interface CartMapper {


    CartEntity toEntity(CartDto cartDto);

    CartDto toDto(CartEntity cart);
}
