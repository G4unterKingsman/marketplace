package ru.gaunter.dto;

import lombok.Data;
import lombok.Getter;

import java.util.UUID;


public record CartItemDto(
        UUID productId,
        Integer quantity
) {}
