package ru.gaunter.productService.dto.dtoforcreate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShopCreateDto {
    private UUID uuid;
    private String name;
    // тут должно быть поле User с ролью Owner (Владелец) Работа с пользователями будет на другом микросервисе
}
