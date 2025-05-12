package ru.gaunter.orderservice.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.gaunter.orderservice.entity.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order")
public class OrderEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private UUID userId;          // ID из UserService
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private BigDecimal totalPrice;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItemEntity> items = new ArrayList<>();

}