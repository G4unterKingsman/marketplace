package ru.gaunter.productService.service.interfaces;

import ru.gaunter.productService.dto.ShopDto;
import ru.gaunter.productService.dto.dtoforcreate.ShopCreateDto;

import java.util.List;
import java.util.UUID;

public interface ShopService {

    ShopDto getByUuid(UUID uuid);
    List<ShopDto> getAll();
    void create(ShopCreateDto shopDto);
    void deleteByUuid(UUID uuid);
    void updateByUuid(UUID uuid, ShopDto shopDto);

}
