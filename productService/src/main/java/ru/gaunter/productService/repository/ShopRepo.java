package ru.gaunter.productService.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.gaunter.productService.entity.AddressEntity;
import ru.gaunter.productService.entity.ShopEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ShopRepo {
    private final JdbcTemplate jdbcTemplate;

    public ShopRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<ShopEntity> shopEntityRowMapper = (resultSet, rowNum) ->
            ShopEntity.builder()
                    .uuid(UUID.fromString(resultSet.getString("uuid")))
                    .name(resultSet.getString("name"))
                    .address(resultSet.getString("address_uuid")
                            != null ?
                            AddressEntity.builder()
                                    .uuid(UUID.fromString(resultSet.getString("address_uuid")))
                                    .street(resultSet.getString("street"))
                                    .city(resultSet.getString("city"))
                                    .house(resultSet.getString("house"))
                                    .build()
                            : null)
                    .build();

    public List<ShopEntity> findAll() {
        return jdbcTemplate.query("""
                SELECT s.uuid, s.name, s.address_uuid as address_uuid, a.city, a.street, a.house
                FROM shop s
                LEFT JOIN address a ON s.address_uuid = a.uuid
                """, shopEntityRowMapper);
    }

    public Optional<ShopEntity> findById(UUID uuid) {
        return jdbcTemplate.query("""
                            SELECT s.uuid, s.name, s.address_uuid, a.city, a.street, a.house
                            FROM shop s
                            LEFT JOIN address a ON s.address_uuid = a.uuid
                            WHERE s.uuid = ?
                        """, shopEntityRowMapper, uuid)
                .stream().findFirst();
    }


    public void save(ShopEntity shop) {
        jdbcTemplate.update("""
                        INSERT INTO shop (uuid, name) VALUES (?, ?)
                       """,
                shop.getUuid(),
                shop.getName()
        );
    }

    public void update(ShopEntity shop) {
        jdbcTemplate.update("""
                            UPDATE shop SET address_uuid = ?, name = ? WHERE uuid = ?
                        """,
                shop.getAddress() != null ? shop.getAddress().getUuid() : null,
                shop.getName(),
                shop.getUuid()
        );
    }

    public void delete(UUID uuid) {
        jdbcTemplate.update("DELETE FROM shop WHERE uuid = ?", uuid);
    }
}
