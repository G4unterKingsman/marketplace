package ru.gaunter.dto;

import java.util.UUID;

public record CartCheckRequest(
        UUID productId,
        Integer quantity
) {}