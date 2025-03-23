package ru.gaunter.productService.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.gaunter.productService.entity.CatalogEntity;
import ru.gaunter.productService.entity.ShopEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CatalogRepo {
    private final JdbcTemplate jdbcTemplate;

    public CatalogRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<CatalogEntity> catalogRowMapper = (rs, rowNum) -> CatalogEntity.builder()
            .uuid(UUID.fromString(rs.getString("uuid")))
            .shop(ShopEntity.builder()
                    .uuid(UUID.fromString(rs.getString("shop_uuid")))
                    .build())
            .build();

    public void save(CatalogEntity catalog) {
        jdbcTemplate.update(
                "INSERT INTO catalog (uuid, shop_uuid) VALUES (?, ?)",
                catalog.getUuid(),
                catalog.getShop().getUuid()
        );
    }

    public void update(CatalogEntity catalog) {
        jdbcTemplate.update(
                "UPDATE catalog SET shop_uuid = ? WHERE uuid = ?",
                catalog.getShop().getUuid(),
                catalog.getUuid()
        );

    }

    public void delete(UUID uuid) {
        jdbcTemplate.update("DELETE FROM category WHERE catalog_uuid = ?", uuid);
        jdbcTemplate.update("DELETE FROM catalog WHERE uuid = ?", uuid);
    }

    public List<CatalogEntity> findAll() {
        return jdbcTemplate.query("SELECT * FROM catalog", catalogRowMapper);
    }

    public Optional<CatalogEntity> findById(UUID uuid) {
        return jdbcTemplate.query("SELECT * FROM catalog WHERE uuid = ?", catalogRowMapper, uuid).stream().findFirst();
    }
}
