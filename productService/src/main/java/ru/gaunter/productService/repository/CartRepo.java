package ru.gaunter.productService.repository;


import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.gaunter.dto.CartDto;
import ru.gaunter.productService.entity.CartEntity;
import ru.gaunter.productService.entity.CartItemEntity;
import ru.gaunter.productService.entity.CatalogEntity;
import ru.gaunter.productService.entity.ProductEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CartRepo {
    private JdbcTemplate jdbcTemplate;
    public CartRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<CartItemEntity> cartItemRowMapper = (rs, rowNum) ->
            CartItemEntity.builder()
                    .uuid(UUID.fromString(rs.getString("uuid")))
                    .product(ProductEntity.builder()
                            .uuid(UUID.fromString(rs.getString("product_uuid")))
                            .build())
                    .cart(CartEntity.builder()
                            .uuid(UUID.fromString(rs.getString("cart_uuid")))
                            .build())
                    .build();

    private final RowMapper<CartEntity> cartRowMapper = (rs, rowNum) -> {
        UUID cartUuid = UUID.fromString(rs.getString("uuid"));

        CartEntity cart = CartEntity.builder()
                .uuid(cartUuid)
                .userUuid(UUID.fromString(rs.getString("user_uuid")))
                .items(new ArrayList<>())
                .build();

        List<CartItemEntity> items = jdbcTemplate.query(
                "SELECT * FROM cart_item WHERE cart_uuid = ?",
                cartItemRowMapper,
                cartUuid.toString());

        items.forEach(item -> item.setCart(cart));
        cart.getItems().addAll(items);

        return cart;
    };

    public Optional<CartEntity> findById(UUID uuid) {
            return jdbcTemplate.query(
                    "SELECT * FROM cart WHERE uuid = ?",
                    cartRowMapper,
                    uuid.toString()).stream().findFirst();
    }

    public Optional<CartEntity> findByUserUuid(UUID uuid) {
        return jdbcTemplate.query(
                "SELECT * FROM cart WHERE user_uuid = ?",
                cartRowMapper,
                uuid.toString()).stream().findFirst();
    }
}