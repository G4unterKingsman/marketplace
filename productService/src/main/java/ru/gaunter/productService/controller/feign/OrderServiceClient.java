package ru.gaunter.productService.controller.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient(name = "order-service", url = "${order-service.url}")
public interface OrderServiceClient {
    @PostMapping("/api/orders")
    void createOrder(@RequestParam UUID userId);
}
