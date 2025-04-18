package ru.gaunter.productService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gaunter.dto.CartCheckRequest;
import ru.gaunter.dto.CartDto;
import ru.gaunter.dto.CartItemDto;
import ru.gaunter.dto.exceprion.InsufficientStockException;
import ru.gaunter.dto.exceprion.ProductNotFoundException;
import ru.gaunter.productService.dto.ProductDto;
import ru.gaunter.productService.entity.CartEntity;
import ru.gaunter.productService.entity.CartItemEntity;
import ru.gaunter.productService.entity.ProductEntity;
import ru.gaunter.productService.repository.CartRepo;
import ru.gaunter.productService.service.interfaces.CartService;
import ru.gaunter.productService.service.interfaces.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepo cartRepo;

    private final ProductService productService;
    @Override
    public CartDto findByUserId(UUID userId) {
        CartEntity cartEntity = cartRepo.findByUserUuid(userId).orElseThrow();

        List<CartItemDto> itemsDto = new ArrayList<>();
        List<CartItemEntity> itemEntities = cartEntity.getItems();

        for (CartItemEntity itemEntity : itemEntities) {
            itemsDto.add(new CartItemDto(
                    itemEntity.getProduct().getUuid(),
                    itemEntity.getQuantity()
            ));
        }

        return new CartDto(
                cartEntity.getUserUuid(),
                itemsDto
        );
    }


    public void checkProduct(List<CartCheckRequest> requests){
        requests.forEach(request -> {
            ProductDto product = productService.getByUuid(request.productId());

            if(product.getStock() < request.quantity()) {
                throw new InsufficientStockException("На складе нет столько товаров с id " + request.productId());
            }
        });
    }
}
