package ru.gaunter.productService.controller.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gaunter.productService.dto.AddressDto;
import ru.gaunter.productService.dto.dtoforcreate.AddressCreateDto;
import ru.gaunter.productService.service.AddressServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/shop/address")
public class AddressRestController {
    private final AddressServiceImpl addressService;

    @GetMapping("/{id}")
    public AddressDto getByUuid(@PathVariable UUID id){
        return addressService.getByUuid(id);
    }

    @GetMapping
    public List<AddressDto> getAll(){
        return addressService.getAll();
    }


    @PostMapping("/create")
    public void create(@RequestBody AddressCreateDto addressDto){
        addressService.create(addressDto);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable UUID id,
                       @RequestBody AddressDto addressDto){
        addressService.updateByUuid(id,addressDto);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        addressService.deleteByUuid(id);
    }
}
