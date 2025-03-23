package ru.gaunter.productService.dto.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.gaunter.productService.dto.CategoryDto;
import ru.gaunter.productService.dto.dtoforcreate.CategoryCreateDto;
import ru.gaunter.productService.entity.CategoryEntity;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDto toDto(CategoryEntity category);

    CategoryEntity toEntity(CategoryDto categoryDto);


    @Mapping(target = "catalog", ignore = true)
    CategoryEntity toEntity(CategoryCreateDto categoryDto);
}
