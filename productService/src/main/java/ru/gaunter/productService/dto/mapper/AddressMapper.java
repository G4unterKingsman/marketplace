package ru.gaunter.productService.dto.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.gaunter.productService.dto.AddressDto;
import ru.gaunter.productService.dto.dtoforcreate.AddressCreateDto;
import ru.gaunter.productService.dto.shortdto.ShopShortDto;
import ru.gaunter.productService.entity.AddressEntity;
import ru.gaunter.productService.entity.ShopEntity;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    AddressDto toDto(AddressEntity address);

    AddressEntity toEntity(AddressDto addressDto);

    AddressEntity toEntity(AddressCreateDto addressDto);

    @Mapping(source = "uuid", target = "uuid")
    ShopShortDto toShortDto(ShopEntity entity);
}
