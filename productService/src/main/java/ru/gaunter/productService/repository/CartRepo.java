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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class CartRepo {
    private JdbcTemplate jdbcTemplate;

    public CartRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<CartItemEntity> cartItemRowMapper = (rs, rowNum) ->
            CartItemEntity.builder()
                    .uuid(UUID.fromString(rs.getString("ci_uuid")))
                    .quantity(rs.getInt("quantity"))
                    .product(ProductEntity.builder()
                            .uuid(UUID.fromString(rs.getString("product_uuid")))
                            .cost(rs.getBigDecimal("cost"))
                            .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                            .weight(rs.getDouble("weight"))
                            .stock(rs.getInt("stock"))
                            .description(rs.getString("description"))
                            .name(rs.getString("name"))
                            .build())
                    .build();

    private final RowMapper<CartEntity> cartRowMapper = (rs, rowNum) -> {
        UUID cartUuid = UUID.fromString(rs.getString("uuid"));

        CartEntity cart = CartEntity.builder()
                .uuid(cartUuid)
                .userUuid(UUID.fromString(rs.getString("user_uuid")))
                .totalPrice(rs.getBigDecimal("total_price"))
                .items(new ArrayList<>())
                .build();

        List<CartItemEntity> items = jdbcTemplate.query(
                """
                                SELECT
                                        ci.uuid as ci_uuid,
                                        ci.quantity,
                                        p.uuid as product_uuid,
                                        p.cost,
                                        p.name,
                                        p.stock,
                                        p.weight,
                                        p.created_at,
                                        p.description
                                    FROM cart_item ci
                                    JOIN product p ON ci.product_uuid = p.uuid
                                    WHERE ci.cart_uuid = ?
                        """,
                cartItemRowMapper,
                cartUuid);

        items.forEach(item -> item.setCart(cart));
        cart.getItems().addAll(items);

        return cart;
    };

    public Optional<CartEntity> findById(UUID uuid) {
        return jdbcTemplate.query(
                "SELECT * FROM cart WHERE uuid = ?",
                cartRowMapper,
                uuid).stream().findFirst();
    }

    public Optional<CartEntity> findByUserUuid(UUID uuid) {
        return jdbcTemplate.query(
                "SELECT * FROM cart WHERE user_uuid = ?",
                cartRowMapper,
                uuid).stream().findFirst();
    }

    public void save(CartEntity cart) {
        boolean exists = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM cart WHERE uuid = ?",
                Integer.class,
                cart.getUuid()) > 0;

        if (exists) {
            jdbcTemplate.update(
                    "UPDATE cart SET user_uuid = ?, total_price = ? WHERE uuid = ?",
                    cart.getUserUuid(),
                    cart.getTotalPrice(),
                    cart.getUuid()
            );
        } else {
            jdbcTemplate.update(
                    "INSERT INTO cart (uuid, user_uuid, total_price) VALUES (?, ?, ?)",
                    cart.getUuid(),
                    cart.getUserUuid(),
                    cart.getTotalPrice()
            );
        }

        jdbcTemplate.update(
                "DELETE FROM cart_item WHERE cart_uuid = ?",
                cart.getUuid()
        );

        List<CartItemEntity> items = cart.getItems();
        if (items != null && !items.isEmpty()) {
            jdbcTemplate.batchUpdate(
                    "INSERT INTO cart_item (uuid, cart_uuid, product_uuid, quantity) VALUES (?, ?, ?, ?)",
                    items.stream().map(item -> new Object[]{
                            item.getUuid(),
                            cart.getUuid(),
                            item.getProduct().getUuid(),
                            item.getQuantity()
                    }).collect(Collectors.toList())
            );
        }
    }


    public Optional<CartItemEntity> findCartItemByProductId(UUID productId) {
        return jdbcTemplate.query(
                "SELECT * FROM cart_item WHERE product_uuid = ?",
                cartItemRowMapper,
                productId
        ).stream().findFirst();
    }

    public void updateTotalPrice(UUID uuidCart) {
        BigDecimal newTotalPrice = jdbcTemplate.queryForObject(
                """
                        SELECT SUM(ci.quantity * p.cost)
                        FROM cart_item ci
                        JOIN product p ON ci.product_uuid = p.uuid
                        WHERE ci.cart_uuid = ?
                        """,
                BigDecimal.class,
                uuidCart
        );

        jdbcTemplate.update(
                "UPDATE cart SET total_price = ? WHERE uuid = ?",
                newTotalPrice,
                uuidCart
        );
    }
}