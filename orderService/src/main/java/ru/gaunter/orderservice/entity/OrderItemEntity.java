package ru.gaunter.orderservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_item")
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID productId;
    private Integer quantity;
}
