package ru.gaunter.productService.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.gaunter.dto.CartCheckRequest;
import ru.gaunter.dto.CartDto;
import ru.gaunter.dto.CartItemDto;
import ru.gaunter.dto.exceprion.InsufficientStockException;
import ru.gaunter.dto.exceprion.ProductAlreadyInCart;
import ru.gaunter.dto.exceprion.ProductNotFoundException;
import ru.gaunter.productService.dto.ProductDto;
import ru.gaunter.productService.dto.mapper.CartMapper;
import ru.gaunter.productService.dto.mapper.ProductMapper;
import ru.gaunter.productService.entity.CartEntity;
import ru.gaunter.productService.entity.CartItemEntity;
import ru.gaunter.productService.entity.ProductEntity;
import ru.gaunter.productService.repository.CartRepo;
import ru.gaunter.productService.service.interfaces.CartService;
import ru.gaunter.productService.service.interfaces.ProductService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepo cartRepo;

    private final ProductService productService;
    private final ProductMapper productMapper;
    private final CartMapper cartMapper;





    @Override
    public CartDto findByUserIdForOrderService(UUID userId) {
        CartEntity cart = cartRepo.findByUserUuid(userId).orElseThrow();
        updateTotalPriceCart(cart);

        List<CartItemDto> cartItemDtos = new ArrayList<>();
        for (CartItemEntity item : cart.getItems()) {
            cartItemDtos.add(new CartItemDto(
                    item.getProduct().getUuid(),
                    item.getProduct().getCost(),
                    item.getQuantity()
            ));
        }

        return new CartDto(
                cart.getUuid(),
                cart.getTotalPrice(),
                cartItemDtos
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

    @Transactional
    public ResponseEntity<?> addProduct(UUID userId, UUID productId, int quantity) {
        CartEntity cart = getOrCreateCart(userId);
        ProductEntity product = productMapper.toEntity(productService.getByUuid(productId));

        for (CartItemEntity item : cart.getItems()) {
            if(product.getUuid().equals(item.getProduct().getUuid()))
                return ResponseEntity.ok(new ProductAlreadyInCart("Товар с id " +item.getProduct().getUuid() + " уже есть в корзине"));
        }


        CartItemEntity item = new CartItemEntity(
                UUID.randomUUID(),
                cart,
                product,
                quantity);

        cart.getItems().add(item);

        updateTotalPriceCart(cart);
        cartRepo.save(cart);
        return ResponseEntity.ok("Товар Добавлен");
    }



    @Transactional
    public void removeProduct(UUID userId, UUID productId) {
        CartEntity cart = getOrCreateCart(userId);
        CartItemEntity cartItemEntity = cartRepo.findCartItemByProductId(productId).orElseThrow(
                () -> new InsufficientStockException("В корзине нет продукта с id " + productId));
        cart.getItems().remove(cartItemEntity);


        updateTotalPriceCart(cart);
        cartRepo.save(cart);
    }

    @Override
    public CartEntity findById(UUID uuid) {


        return cartRepo.findByUserUuid(uuid).orElseThrow();
    }

    private CartEntity getOrCreateCart(UUID userId) {
        CartEntity cart = cartRepo.findByUserUuid(userId)
                .orElseGet(() -> {
                    CartEntity newCart = new CartEntity();
                    newCart.setUuid(UUID.randomUUID());
                    newCart.setUserUuid(userId);
                    newCart.setTotalPrice(BigDecimal.ZERO);
                    cartRepo.save(newCart);
                    return newCart;
                });

        updateTotalPriceCart(cart);
        cart = cartRepo.findByUserUuid(userId).orElseThrow();
        return cart;
    }


    public void updateTotalPriceCart(CartEntity cart){
        cartRepo.updateTotalPrice(cart.getUuid());
    }
}
