package ru.gaunter.productService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gaunter.productService.dto.AddressDto;
import ru.gaunter.productService.dto.dtoforcreate.AddressCreateDto;
import ru.gaunter.productService.dto.mapper.AddressMapper;
import ru.gaunter.productService.entity.AddressEntity;
import ru.gaunter.productService.repository.AddressRepo;
import ru.gaunter.productService.service.interfaces.AddressService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressMapper addressMapper = AddressMapper.INSTANCE;
    private final AddressRepo addressRepo;

    public AddressDto getByUuid(UUID uuid) {
        return addressMapper.toDto(addressRepo.findById(uuid).orElseThrow());
    }

    public AddressDto create(AddressCreateDto addressCreateDto) {
        AddressEntity addressEntity = addressMapper.toEntity(addressCreateDto);
        addressEntity.setUuid(UUID.randomUUID());
        return addressMapper.toDto(addressRepo.save(addressEntity).orElseThrow());
    }

    public List<AddressDto> getAll() {
        return addressRepo.findAll()
                .stream()
                .map(addressMapper::toDto)
                .collect(Collectors.toList());
    }

    public void deleteByUuid(UUID uuid) {
        addressRepo.delete(uuid);
    }

    public void updateByUuid(UUID uuid, AddressDto addressDto) {
        AddressEntity addressEntity = addressMapper.toEntity(addressDto);
        addressEntity.setUuid(uuid);
        addressRepo.save(addressEntity);
    }


    public AddressEntity getEntityByUuid(UUID uuid) {
        return addressRepo.findById(uuid).orElseThrow();
    }
}
