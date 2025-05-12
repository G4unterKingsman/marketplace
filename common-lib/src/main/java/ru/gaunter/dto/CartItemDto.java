package ru.gaunter.dto;

import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;


public record CartItemDto(
        UUID productId,
        BigDecimal price,
        Integer quantity
) {}
