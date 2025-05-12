package ru.gaunter.productService.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "address")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressEntity {

    /**
     * Вспомогательная таблица
     * нужна чтобы легче было работать с адресами магазинов
     *
     * !ВАЖНО
     * в будущем если понадобится проверка равенства двух адресов, можно переопределить equals hashcode чтобы выдавали true БЕЗ проверки ID адреса
     * Чтобы валидировать 2 магазина на одном адресе (если надо)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    private String city;
    private String street;
    private String house;
}
