package ru.gaunter.productService.service.interfaces;

import ru.gaunter.productService.dto.ProductDto;
import ru.gaunter.productService.dto.dtoforcreate.ProductCreateDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductDto getByUuid(UUID uuid);

    void create(ProductCreateDto productDto);
    List<ProductDto> getAll();
    void deleteByUuid(UUID uuid);
    void updateByUuid(UUID uuid, ProductDto productDto);

    void updateAllPrices(BigDecimal usdRate);
}
