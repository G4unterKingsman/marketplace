package ru.gaunter.productService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gaunter.productService.dto.dtoforcreate.AddressCreateDto;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShopDto {
    private UUID uuid;
    private String name;
    private AddressCreateDto address;
}
