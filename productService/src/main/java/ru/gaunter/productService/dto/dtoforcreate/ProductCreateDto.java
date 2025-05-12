package ru.gaunter.productService.dto.dtoforcreate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gaunter.productService.dto.shortdto.CategoryShortDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCreateDto {
    private UUID uuid;
    private String name;
    private LocalDateTime createdAt;
    private BigDecimal cost;
    private Double weight;
    private Integer stock;
    private String description;
    private String categoryName;
}
