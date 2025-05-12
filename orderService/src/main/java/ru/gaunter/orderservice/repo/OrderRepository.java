package ru.gaunter.orderservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gaunter.orderservice.entity.OrderEntity;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
}
