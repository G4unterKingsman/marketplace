package ru.gaunter.productService.service.interfaces;

import ru.gaunter.productService.dto.CategoryDto;
import ru.gaunter.productService.dto.dtoforcreate.CategoryCreateDto;
import ru.gaunter.productService.entity.CategoryEntity;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    CategoryDto getByUuid(UUID uuid);
    void create(CategoryCreateDto categoryDto);
    List<CategoryDto> getAll();
    void deleteByUuid(UUID uuid);
    void updateByUuid(UUID uuid, CategoryDto categoryDto);
    CategoryEntity getByName(String name);
}
