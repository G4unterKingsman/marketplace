package ru.gaunter.orderservice.entity.enums;


import lombok.Getter;

@Getter
public enum OrderStatus {
    CREATED,
    PAID,
    DELIVERED,
    CANCELED;

}
