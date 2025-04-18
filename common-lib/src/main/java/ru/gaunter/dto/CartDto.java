package ru.gaunter.dto;


import java.util.List;
import java.util.UUID;


public record CartDto(
        UUID userId,
        List<CartItemDto> items
) {}