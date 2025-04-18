package ru.gaunter.productService.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "cart")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private UUID userUuid; // ID из User Service

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItemEntity> items = new ArrayList<>();
}
