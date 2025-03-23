package ru.gaunter.productService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gaunter.productService.dto.ShopDto;
import ru.gaunter.productService.dto.dtoforcreate.ShopCreateDto;
import ru.gaunter.productService.dto.mapper.AddressMapper;
import ru.gaunter.productService.dto.mapper.ShopMapper;
import ru.gaunter.productService.entity.AddressEntity;
import ru.gaunter.productService.entity.ShopEntity;
import ru.gaunter.productService.repository.ShopRepo;
import ru.gaunter.productService.service.interfaces.ShopService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {
    private final ShopMapper shopMapper = ShopMapper.INSTANCE;
    private final ShopRepo shopRepo;

    private final AddressServiceImpl addressService;
    private final AddressMapper addressMapper;

    public ShopDto getByUuid(UUID uuid) {
        ShopEntity shopEntity = shopRepo.findById(uuid).orElseThrow();
        return shopMapper.toDto(shopEntity);
    }

    public void create(ShopCreateDto shopDto) {
        ShopEntity shopEntity = shopMapper.toEntity(shopDto);

        shopEntity.setUuid(UUID.randomUUID());

        shopRepo.save(shopEntity);
    }

    public List<ShopDto> getAll() {
        return shopRepo.findAll()
                .stream()
                .map(shopMapper::toDto)
                .collect(Collectors.toList());
    }

    public void deleteByUuid(UUID uuid) {
        shopRepo.delete(uuid);
    }

    public void updateByUuid(UUID uuid, ShopDto shopDto) {
        AddressEntity address = addressMapper.toEntity(addressService.create(shopDto.getAddress()));
        ShopEntity shopEntity = shopRepo.findById(uuid).orElseThrow();

        shopMapper.updateEntityFromDto(shopDto,shopEntity);
        shopEntity.setAddress(address);

        shopRepo.update(shopEntity);
    }


    public ShopEntity getEntityByUuid(UUID uuid) {
        return shopRepo.findById(uuid).orElseThrow();
    }
}
