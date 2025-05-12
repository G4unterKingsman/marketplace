package ru.gaunter.productService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gaunter.dto.ProductDtoForFeign;
import ru.gaunter.dto.exceprion.ProductNotFoundException;
import ru.gaunter.productService.dto.ProductDto;
import ru.gaunter.productService.dto.dtoforcreate.ProductCreateDto;
import ru.gaunter.productService.dto.mapper.ProductMapper;
import ru.gaunter.productService.entity.CategoryEntity;
import ru.gaunter.productService.entity.ProductEntity;
import ru.gaunter.productService.repository.ProductRepo;
import ru.gaunter.productService.service.interfaces.ProductService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper = ProductMapper.INSTANCE;
    private final ProductRepo productRepo;
    private final CategoryServiceImpl categoryService;

    public ProductDto getByUuid(UUID uuid) {
        return productMapper.toDto(productRepo.findById(uuid)
                .orElseThrow(() -> new ProductNotFoundException("нет продукта с id " + uuid)));
    }

    public void create(ProductCreateDto productDto) {
        CategoryEntity category = categoryService.getByName(productDto.getCategoryName());
        ProductEntity product = productMapper.toEntity(productDto);

        product.setUuid(UUID.randomUUID());
        product.setCategory(category);

        productRepo.save(product);
    }

    public List<ProductDto> getAll() {
        return productRepo.findAll()
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    public void deleteByUuid(UUID uuid) {
        productRepo.delete(uuid);
    }

    public void updateByUuid(UUID uuid, ProductDto productDto) {
        ProductEntity productEntity = productMapper.toEntity(productDto);
        productEntity.setUuid(uuid);
        productRepo.save(productEntity);
    }



    public ProductDtoForFeign getForOrder(UUID uuid){
        ProductEntity product = productRepo.findById(uuid).orElseThrow();

        return new ProductDtoForFeign(
                product.getUuid(),
                product.getName(),
                product.getCost()
        );
    }
}
