package ru.gaunter.productService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gaunter.productService.dto.CatalogDto;
import ru.gaunter.productService.dto.dtoforcreate.CatalogCreateDto;
import ru.gaunter.productService.dto.mapper.CatalogMapper;
import ru.gaunter.productService.entity.CatalogEntity;
import ru.gaunter.productService.entity.ShopEntity;
import ru.gaunter.productService.repository.CatalogRepo;
import ru.gaunter.productService.service.interfaces.CatalogService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {
    private final CatalogMapper catalogMapper = CatalogMapper.INSTANCE;
    private final CatalogRepo catalogRepo;
    private final ShopServiceImpl shopService;



    public CatalogDto getByUuid(UUID uuid) {
        return catalogMapper.toDto(catalogRepo.findById(uuid).orElseThrow());
    }

    public void create(CatalogCreateDto catalogDto) {
        ShopEntity shop = shopService.getEntityByUuid(catalogDto.getShopUuid());
        CatalogEntity catalogEntity = catalogMapper.toEntity(catalogDto);

        catalogEntity.setUuid(UUID.randomUUID());
        catalogEntity.setShop(shop);

        catalogRepo.save(catalogEntity);
    }

    public List<CatalogDto> getAll() {
        return catalogRepo.findAll()
                .stream()
                .map(catalogMapper::toDto)
                .collect(Collectors.toList());
    }

    public void deleteByUuid(UUID uuid) {
        catalogRepo.delete(uuid);
    }

    public void updateByUuid(UUID uuid, CatalogDto catalogDto) {
        CatalogEntity catalogEntity = catalogMapper.toEntity(catalogDto);
        catalogEntity.setUuid(uuid);
        catalogRepo.save(catalogEntity);
    }

    public CatalogEntity getEntityByUuid(UUID uuid) {
        return catalogRepo.findById(uuid).orElseThrow();
    }
}
