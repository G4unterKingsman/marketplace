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
@Table(name = "shop")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShopEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private String name;


    @OneToOne
    private AddressEntity address;
}
