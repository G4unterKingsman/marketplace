package ru.gaunter.productService.service.interfaces;

import org.springframework.http.ResponseEntity;
import ru.gaunter.dto.CartCheckRequest;
import ru.gaunter.dto.CartDto;
import ru.gaunter.productService.entity.CartEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CartService {
    CartDto findByUserIdForOrderService(UUID userId);


    void checkProduct(List<CartCheckRequest> requests);

    ResponseEntity<?> addProduct(UUID productId, UUID userId, int quantity);

    void removeProduct(UUID userId, UUID productId);

    CartEntity findById(UUID uuid);
}
