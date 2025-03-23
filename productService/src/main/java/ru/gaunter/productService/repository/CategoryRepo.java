package ru.gaunter.productService.repository;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.gaunter.productService.entity.CatalogEntity;
import ru.gaunter.productService.entity.CategoryEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public class CategoryRepo {
    private final JdbcTemplate jdbcTemplate;

    public CategoryRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<CategoryEntity> categoryRowMapper = (rs, rowNum) ->
            CategoryEntity.builder()
            .uuid(UUID.fromString(rs.getString("uuid")))
            .name(rs.getString("name"))
            .catalog(CatalogEntity.builder()
                    .uuid(UUID.fromString(rs.getString("catalog_uuid")))
                    .build())
            .build();

    public void save(CategoryEntity category) {
        jdbcTemplate.update(
                "INSERT INTO category (uuid, name, catalog_uuid) VALUES (?, ?, ?)",
                category.getUuid(),
                category.getName(),
                category.getCatalog().getUuid()
        );
    }

    public void update(CategoryEntity category) {
        jdbcTemplate.update(
                "UPDATE category SET name = ?, catalog_uuid = ? WHERE uuid = ?",
                category.getName(),
                category.getCatalog().getUuid(),
                category.getUuid()
        );
    }

    public void delete(UUID uuid) {
        jdbcTemplate.update("DELETE FROM category WHERE uuid = ?", uuid);
    }

    public List<CategoryEntity> findAll() {

        return jdbcTemplate.query("SELECT * FROM category", categoryRowMapper);
    }

    public Optional<CategoryEntity> findById(UUID uuid) {
        return jdbcTemplate.query("SELECT * FROM category WHERE uuid = ?", categoryRowMapper, uuid).stream().findFirst();
    }


    public Optional<CategoryEntity> findByName(String name) {
        return jdbcTemplate.query(
                "SELECT * FROM category WHERE name = ?", categoryRowMapper, name
        ).stream().findFirst();
    }
}
