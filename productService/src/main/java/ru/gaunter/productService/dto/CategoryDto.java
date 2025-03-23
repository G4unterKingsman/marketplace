package ru.gaunter.productService.dto;

import lombok.*;
import ru.gaunter.productService.dto.shortdto.CatalogShortDto;
import java.util.UUID;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {
    private UUID uuid;
    private String name;

    private CatalogShortDto catalog;
}
