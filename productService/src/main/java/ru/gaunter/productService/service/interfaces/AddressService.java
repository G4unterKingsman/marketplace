package ru.gaunter.productService.service.interfaces;

import ru.gaunter.productService.dto.AddressDto;
import ru.gaunter.productService.dto.dtoforcreate.AddressCreateDto;

import java.util.List;
import java.util.UUID;

public interface AddressService {
    AddressDto getByUuid(UUID uuid);
    AddressDto create(AddressCreateDto addressDto);
    List<AddressDto> getAll();
    void deleteByUuid(UUID uuid);
    void updateByUuid(UUID uuid, AddressDto addressDto);

}
