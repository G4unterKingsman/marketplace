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
@Table(name = "cart_item")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @ManyToOne
    private CartEntity cart;

    @ManyToOne
    private ProductEntity product;

    private Integer quantity;
}
