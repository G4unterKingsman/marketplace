package ru.gaunter.orderservice.feignclient;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.gaunter.dto.CartCheckRequest;
import ru.gaunter.dto.CartDto;
import ru.gaunter.dto.ProductDtoForFeign;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "product-service")
public interface ProductServiceClient {
    @PostMapping("/api/shop/cart/check")
    ResponseEntity<?> checkCartItems(@RequestBody List<CartCheckRequest> items);

    @GetMapping("/api/shop/product/for_order/{uuid}")
    ProductDtoForFeign getProductInfo(@PathVariable UUID uuid);

    @GetMapping("/api/shop/cart/{userId}")
    CartDto getCart(@PathVariable  UUID userId);
}
