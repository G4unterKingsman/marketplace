package ru.gaunter.productService.controller.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gaunter.dto.CartCheckRequest;
import ru.gaunter.dto.CartDto;
import ru.gaunter.dto.CartItemDto;
import ru.gaunter.dto.exceprion.InsufficientStockException;
import ru.gaunter.dto.exceprion.ProductNotFoundException;
import ru.gaunter.productService.entity.CartEntity;
import ru.gaunter.productService.entity.ProductEntity;
import ru.gaunter.productService.service.interfaces.CartService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/shop/")
@RequiredArgsConstructor
public class CartRestController {
    private final CartService cartService;
    @GetMapping("/cart/{userId}")
    public CartDto getCart(@PathVariable UUID userId) {
        CartDto cart = cartService.findByUserId(userId);

        return new CartDto(
                cart.userId(),
                cart.items().stream()
                        .map(item -> new CartItemDto(item.productId(), item.quantity()))
                        .toList()
        );
    }


    @PostMapping("/cart/check")
    public void checkCart(@RequestBody List<CartCheckRequest> requests) {
        cartService.checkProduct(requests);
    }
}
