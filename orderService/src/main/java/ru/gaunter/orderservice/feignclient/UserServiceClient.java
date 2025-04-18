package ru.gaunter.orderservice.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "user-service")
public interface UserServiceClient {
    @GetMapping("/api/user/{userId}/exists")
    boolean userExists(@PathVariable UUID userId);
}