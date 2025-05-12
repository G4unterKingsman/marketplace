package ru.gaunter.productService.controller.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gaunter.dto.ProductDtoForFeign;
import ru.gaunter.productService.dto.ProductDto;
import ru.gaunter.productService.dto.dtoforcreate.ProductCreateDto;
import ru.gaunter.productService.service.ProductServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/shop/product")
public class ProductRestController {
    private final ProductServiceImpl productService;

    @GetMapping("/{id}")
    public ProductDto getByUuid(@PathVariable UUID id){
        return productService.getByUuid(id);
    }


    @GetMapping("/for_order/{uuid}")
    public ProductDtoForFeign getForOrder(@PathVariable UUID uuid) {
        return productService.getForOrder(uuid);
    }


    @GetMapping
    public List<ProductDto> getAll(){
        return productService.getAll();
    }


    @PostMapping("/create")
    public void create(@RequestBody ProductCreateDto productDto){
        productService.create(productDto);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable UUID id,
                       @RequestBody ProductDto productDto){
        productService.updateByUuid(id,productDto);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        productService.deleteByUuid(id);
    }
}
