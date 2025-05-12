package ru.gaunter.productService.controller.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gaunter.productService.dto.CategoryDto;
import ru.gaunter.productService.dto.dtoforcreate.CategoryCreateDto;
import ru.gaunter.productService.service.interfaces.CategoryService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/shop/category")
public class CategoryRestController {
    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public CategoryDto getByUuid(@PathVariable UUID id){
        return categoryService.getByUuid(id);
    }

    @GetMapping
    public List<CategoryDto> getAll(){
        return categoryService.getAll();
    }


    @PostMapping("/create")
    public void create(@RequestBody CategoryCreateDto categoryCreateDto){
        categoryService.create(categoryCreateDto);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable UUID id,
                       @RequestBody CategoryDto categoryDto){
        categoryService.updateByUuid(id,categoryDto);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        categoryService.deleteByUuid(id);
    }
}
