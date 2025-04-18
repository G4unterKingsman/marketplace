package ru.gaunter.productService.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "product")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductEntity {
    /**
     * Таблица продуктов
     *
     * у 1 магазина - 1 каталог
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private String name;
    private LocalDateTime createdAt;
    private BigDecimal cost;
    private Double weight;
    private String description;
    private Integer stock;

    @ManyToOne
    private CategoryEntity category;

}
