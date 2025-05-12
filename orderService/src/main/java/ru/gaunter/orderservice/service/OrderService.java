package ru.gaunter.orderservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gaunter.dto.CartCheckRequest;
import ru.gaunter.dto.CartDto;
import ru.gaunter.dto.exceprion.UserNotFoundException;
import ru.gaunter.orderservice.entity.enums.OrderStatus;
import ru.gaunter.orderservice.repo.OrderRepository;
import ru.gaunter.orderservice.entity.OrderEntity;
import ru.gaunter.orderservice.entity.OrderItemEntity;
import ru.gaunter.orderservice.feignclient.ProductServiceClient;
import ru.gaunter.orderservice.feignclient.UserServiceClient;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductServiceClient productClient;
    private final UserServiceClient userClient;
    private final OrderRepository orderRepository;

    public OrderEntity createOrder(UUID userId) {
        CartDto cart = productClient.getCart(userId);

        List<CartCheckRequest> checkRequests = cart.items().stream()
                .map(item -> new CartCheckRequest(item.productId(), item.quantity()))
                .toList();

        productClient.checkCartItems(checkRequests);
        if(userClient.userExists(userId)){
            throw new UserNotFoundException("Не найден пользователь с id " + userId);
        }

        OrderEntity order = new OrderEntity();

        order.setUserId(userId);
        order.setItems(cart.items().stream()
                .map(item -> new OrderItemEntity(
                        UUID.randomUUID(),
                        item.productId(),
                        item.quantity())
                )
                .toList());
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(OrderStatus.CREATED);
        order.setUuid(UUID.randomUUID());
        order.setTotalPrice(cart.totalPrice());

        return orderRepository.save(order);
    }
}
