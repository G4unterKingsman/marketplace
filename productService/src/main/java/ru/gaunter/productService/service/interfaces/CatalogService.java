package ru.gaunter.productService.service.interfaces;

import ru.gaunter.productService.dto.CatalogDto;
import ru.gaunter.productService.dto.dtoforcreate.CatalogCreateDto;

import java.util.List;
import java.util.UUID;

public interface CatalogService {
    CatalogDto getByUuid(UUID uuid);

    void create(CatalogCreateDto catalogDto);
    List<CatalogDto> getAll();
    void deleteByUuid(UUID uuid);
    void updateByUuid(UUID uuid, CatalogDto catalogDto);

}
