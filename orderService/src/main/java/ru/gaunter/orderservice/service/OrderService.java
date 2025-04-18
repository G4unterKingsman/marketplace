package ru.gaunter.orderservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gaunter.dto.CartCheckRequest;
import ru.gaunter.dto.CartDto;
import ru.gaunter.orderservice.repo.OrderRepository;
import ru.gaunter.orderservice.entity.OrderEntity;
import ru.gaunter.orderservice.entity.OrderItemEntity;
import ru.gaunter.orderservice.feignclient.ProductServiceClient;
import ru.gaunter.orderservice.feignclient.UserServiceClient;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductServiceClient productClient;
    private final UserServiceClient userClient;
    private final OrderRepository orderRepository;

    public OrderEntity createOrder(UUID userId) {
        // 1. Получение корзины из ProductService
        CartDto cart = productClient.getCart(userId);

        // 2. Преобразование в запрос для проверки
        List<CartCheckRequest> checkRequests = cart.items().stream()
                .map(item -> new CartCheckRequest(item.productId(), item.quantity()))
                .toList();

        // 3. Проверка корзины
        productClient.checkCartItems(checkRequests);

        // 4. Создание заказа
        OrderEntity order = new OrderEntity();
        order.setUserId(userId);
        order.setItems(cart.items().stream()
                .map(item -> new OrderItemEntity(
                        UUID.randomUUID(),
                        item.productId(),
                        item.quantity())

                )
                .toList());

        return orderRepository.save(order);
    }
}
