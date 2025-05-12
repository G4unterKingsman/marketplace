package ru.gaunter.productService.dto.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.gaunter.productService.dto.CatalogDto;
import ru.gaunter.productService.dto.dtoforcreate.CatalogCreateDto;
import ru.gaunter.productService.dto.shortdto.ShopShortDto;
import ru.gaunter.productService.entity.*;

@Mapper
public interface CatalogMapper {
    CatalogMapper INSTANCE = Mappers.getMapper(CatalogMapper.class);

    CatalogEntity toEntity(CatalogDto catalogDto);

    CatalogDto toDto(CatalogEntity catalog);

    ShopShortDto toShortDto(ShopEntity shop);

    @Mapping(target = "shop", ignore = true)
    CatalogEntity toEntity(CatalogCreateDto catalogDto);

}
