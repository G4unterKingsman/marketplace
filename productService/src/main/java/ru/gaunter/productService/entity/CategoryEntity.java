package ru.gaunter.productService.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "category")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryEntity {


    /**
     * Категории
     * У 1 категории - множество продуктов
     * 1 категория имеет 1 каталог
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private String name;

    @ManyToOne
    @JoinColumn(name = "catalog_uuid", nullable = false)
    private CatalogEntity catalog;

}
