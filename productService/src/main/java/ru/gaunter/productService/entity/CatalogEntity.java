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
@Table(name = "catalog")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CatalogEntity {

    /**
     * Промежуточная таблица
     * у 1 магазина - 1 каталог
     * нужна на случай если надо будет создавать несколько каталогов для 1 магазина
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @OneToOne
    private ShopEntity shop;

}
