package ru.gaunter.productService.dto.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import ru.gaunter.productService.dto.ShopDto;
import ru.gaunter.productService.dto.dtoforcreate.ShopCreateDto;
import ru.gaunter.productService.entity.ShopEntity;

@Mapper
public interface ShopMapper {
    ShopMapper INSTANCE = Mappers.getMapper(ShopMapper.class);

    ShopDto toDto(ShopEntity shop);

    ShopEntity toEntity(ShopDto shopDto);


    @Mapping(target = "address", ignore = true)
    ShopEntity toEntity(ShopCreateDto shopDto);

    void updateEntityFromDto(ShopDto dto, @MappingTarget ShopEntity entity);

}
