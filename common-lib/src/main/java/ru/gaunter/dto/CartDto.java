package ru.gaunter.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Builder
public record CartDto(
        UUID userId,
        BigDecimal totalPrice,
        List<CartItemDto> items
) {}