package ru.gaunter.productService.dto.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.gaunter.productService.dto.ProductDto;
import ru.gaunter.productService.dto.dtoforcreate.ProductCreateDto;
import ru.gaunter.productService.dto.shortdto.CategoryShortDto;
import ru.gaunter.productService.entity.CategoryEntity;
import ru.gaunter.productService.entity.ProductEntity;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDto toDto(ProductEntity product);

    ProductEntity toEntity(ProductDto productDto);

    @Mapping(source = "uuid", target = "uuid")
    CategoryShortDto toShortDto(CategoryEntity category);

    @Mapping(target = "category", ignore = true)
    ProductEntity toEntity(ProductCreateDto dto);
}
