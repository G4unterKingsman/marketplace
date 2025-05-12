package ru.gaunter.productService.controller.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import ru.gaunter.dto.CartCheckRequest;
import ru.gaunter.dto.CartDto;
import ru.gaunter.dto.CartItemDto;
import ru.gaunter.productService.entity.CartEntity;
import ru.gaunter.productService.service.interfaces.CartService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/shop/cart")
@RequiredArgsConstructor
public class CartRestController {
    private final CartService cartService;
    @GetMapping("/get_for_order")
    public CartDto getCartForOrderService(@AuthenticationPrincipal Jwt jwt) {
        return cartService.findByUserIdForOrderService(UUID.fromString(jwt.getSubject()));
    }

    @GetMapping("/")
    public CartEntity getCart(@AuthenticationPrincipal Jwt jwt) {
        return cartService.findById(UUID.fromString(jwt.getSubject()));
    }


    @PostMapping("/check")
    public void checkCart(@RequestBody List<CartCheckRequest> requests) {
        cartService.checkProduct(requests);
    }

    @PostMapping("/add/{productId}")
    public ResponseEntity<?> addProduct(
            @PathVariable UUID productId,
            @RequestParam(defaultValue = "1") int quantity,
            @AuthenticationPrincipal Jwt jwt
    ) {
        UUID userId = UUID.fromString(jwt.getSubject());
        return cartService.addProduct(userId, productId, quantity);
    }



    @DeleteMapping("/remove/{productId}")
    public void removeProduct(
            @PathVariable UUID productId,
            @AuthenticationPrincipal Jwt jwt
    ) {
        UUID userId = UUID.fromString(jwt.getSubject());
        cartService.removeProduct(userId, productId);
    }
}
