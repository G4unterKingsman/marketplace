package ru.gaunter.productService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gaunter.productService.dto.CategoryDto;
import ru.gaunter.productService.dto.dtoforcreate.CategoryCreateDto;
import ru.gaunter.productService.dto.mapper.CategoryMapper;
import ru.gaunter.productService.entity.CatalogEntity;
import ru.gaunter.productService.entity.CategoryEntity;
import ru.gaunter.productService.repository.CategoryRepo;
import ru.gaunter.productService.service.interfaces.CategoryService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper = CategoryMapper.INSTANCE;
    private final CategoryRepo categoryRepo;

    private final CatalogServiceImpl catalogService;

    public CategoryDto getByUuid(UUID uuid) {
        return categoryMapper.toDto(categoryRepo.findById(uuid).orElseThrow());
    }

    public void create(CategoryCreateDto categoryDto) {
        CatalogEntity catalog = catalogService.getEntityByUuid(categoryDto.getCatalogUuid());
        CategoryEntity categoryEntity = categoryMapper.toEntity(categoryDto);

        categoryEntity.setUuid(UUID.randomUUID());
        categoryEntity.setCatalog(catalog);

        categoryRepo.save(categoryEntity);
    }

    public List<CategoryDto> getAll() {
        return categoryRepo.findAll()
                .stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }

    public void deleteByUuid(UUID uuid) {
        categoryRepo.delete(uuid);
    }

    public void updateByUuid(UUID uuid, CategoryDto categoryDto) {
        CategoryEntity categoryEntity = categoryMapper.toEntity(categoryDto);
        categoryEntity.setUuid(uuid);
        categoryRepo.save(categoryEntity);
    }

    public CategoryEntity getByName(String name) {
        return categoryRepo.findByName(name).orElseThrow();
    }

}
