package ru.gaunter.productService.controller.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gaunter.productService.dto.CatalogDto;
import ru.gaunter.productService.dto.dtoforcreate.CatalogCreateDto;
import ru.gaunter.productService.service.CatalogServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/shop/catalog")
public class CatalogRestController {
    private final CatalogServiceImpl catalogService;

    @GetMapping("/{id}")
    public CatalogDto getByUuid(@PathVariable UUID id){
        return catalogService.getByUuid(id);
    }

    @GetMapping
    public List<CatalogDto> getAll(){
        return catalogService.getAll();
    }


    @PostMapping("/create")
    public void create(@RequestBody CatalogCreateDto catalogCreateDto){
        catalogService.create(catalogCreateDto);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable UUID id,
                       @RequestBody CatalogDto catalogDto){
        catalogService.updateByUuid(id,catalogDto);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        catalogService.deleteByUuid(id);
    }
}
