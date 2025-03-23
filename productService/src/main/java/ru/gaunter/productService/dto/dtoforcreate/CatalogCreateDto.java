package ru.gaunter.productService.dto.dtoforcreate;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CatalogCreateDto {
    private UUID uuid;
    private UUID shopUuid;
}
