package ru.gaunter.dto;

import lombok.Data;
import java.util.List;
import java.util.UUID;

@Data
public class OrderRequest {
    private UUID userId;
    private List<CartItemDto> items;
}