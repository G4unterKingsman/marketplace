package ru.gaunter.productService.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.gaunter.productService.entity.CategoryEntity;
import ru.gaunter.productService.entity.ProductEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ProductRepo {
    private final JdbcTemplate jdbcTemplate;

    public ProductRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private final RowMapper<ProductEntity> productEntityRowMapper = (rs, rowNum) ->
            ProductEntity.builder()
                    .uuid(UUID.fromString(rs.getString("uuid")))
                    .cost(rs.getBigDecimal("cost"))
                    .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                    .weight(rs.getDouble("weight"))
                    .description(rs.getString("description"))
                    .name(rs.getString("name"))
                    .category(rs.getString("category_uuid") != null ?
                            CategoryEntity.builder()
                                    .uuid(UUID.fromString(rs.getString("category_uuid")))
                                    .name(rs.getString("category_name"))
                                    .build() : null)
                    .build();

    public List<ProductEntity> findAll() {
        return jdbcTemplate.query("""
                SELECT p.uuid, p.name, p.created_at, p.cost, p.description, p.weight, c.uuid as category_uuid, c.name as category_name
                FROM product p
                LEFT OUTER JOIN category c on c.uuid = p.category_uuid
                """, productEntityRowMapper);
    }

    public Optional<ProductEntity> findById(UUID uuid) {
        return jdbcTemplate.query("""
                        SELECT p.uuid, p.name, p.created_at, p.cost, p.description, p.weight, c.uuid as category_uuid
                        FROM product p
                        LEFT OUTER JOIN category c on c.uuid = p.category_uuid
                        WHERE p.uuid = ?
                        """, productEntityRowMapper, uuid)
                .stream().findFirst();
    }

    public void save(ProductEntity product) {
        jdbcTemplate.update(
                "INSERT INTO product (uuid, cost, weight, category_uuid,description,created_at,name) VALUES (?, ?, ?, ?, ?, ?, ?)",
                product.getUuid(),
                product.getCost(),
                product.getWeight(),
                product.getCategory().getUuid(),
                product.getDescription(),
                product.getCreatedAt(),
                product.getName()
        );
    }

    public void update(ProductEntity product) {
        jdbcTemplate.update(
                "UPDATE product SET uuid = ?, cost = ?, weight = ?, category_uuid = ?,description = ?,created_at = ?, name = ? WHERE uuid = ?",
                product.getUuid(),
                product.getCost(),
                product.getWeight(),
                product.getCategory().getUuid(),
                product.getDescription(),
                product.getCreatedAt(),
                product.getName()
        );
    }

    public void delete(UUID uuid) {
        jdbcTemplate.update("DELETE FROM product WHERE uuid = ?", uuid);
    }

}
