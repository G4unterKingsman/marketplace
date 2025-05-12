package ru.gaunter.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductDtoForFeign(
        UUID id,
        String name,
        BigDecimal cost
) {}

