package ru.gaunter.productService.controller.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gaunter.productService.dto.ShopDto;
import ru.gaunter.productService.dto.dtoforcreate.ShopCreateDto;
import ru.gaunter.productService.service.ShopServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/shop/shop-management")
public class ShopRestController {
    private final ShopServiceImpl shopService;

    @GetMapping("/{id}")
    public ShopDto getByUuid(@PathVariable UUID id){
        return shopService.getByUuid(id);
    }

    @GetMapping
    public List<ShopDto> getAll(){
        return shopService.getAll();
    }


    @PostMapping("/create")
    public void create(@RequestBody ShopCreateDto shopDto){
        shopService.create(shopDto);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable UUID id, @RequestBody ShopDto shopDto){
        shopService.updateByUuid(id,shopDto);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        shopService.deleteByUuid(id);
    }
}
