package ru.gaunter.productService.repository;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.gaunter.productService.entity.CartEntity;
import ru.gaunter.productService.entity.CartItemEntity;
import ru.gaunter.productService.entity.ProductEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public class CartItemRepo {


    private JdbcTemplate jdbcTemplate;

    public CartItemRepo(JdbcTemplate jdbcTemplate) {
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



    public Optional<CartItemEntity> findById(UUID uuid) {
        return jdbcTemplate.query(
                "SELECT * FROM cart_item WHERE uuid = ?",
                cartItemRowMapper,
                uuid.toString()).stream().findFirst();
    }
}
