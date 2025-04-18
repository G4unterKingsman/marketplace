package ru.gaunter.productService.service.interfaces;

import ru.gaunter.dto.CartCheckRequest;
import ru.gaunter.dto.CartDto;
import ru.gaunter.productService.entity.CartEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CartService {
    CartDto findByUserId(UUID userId);


    void checkProduct(List<CartCheckRequest> requests);

}
