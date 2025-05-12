package ru.gaunter.productService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gaunter.productService.dto.shortdto.ShopShortDto;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CatalogDto {
    private UUID uuid;
    private ShopShortDto shop;
}
