package ru.gaunter.productService.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.gaunter.productService.entity.AddressEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AddressRepo {
    private final JdbcTemplate jdbcTemplate;
    public AddressRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<AddressEntity> addressEntityRowMapper = (resultSet, rowNum) -> AddressEntity.builder()
            .uuid(UUID.fromString(resultSet.getString("uuid")))
            .house(resultSet.getString("house"))
            .street(resultSet.getString("street"))
            .city(resultSet.getString("city"))
            .build();

    public List<AddressEntity> findAll() {
        return jdbcTemplate.query("SELECT * FROM address", addressEntityRowMapper);
    }

    public Optional<AddressEntity> findById(UUID uuid) {
        return jdbcTemplate.query("SELECT * FROM address WHERE uuid = ?", addressEntityRowMapper, uuid)
                .stream().findFirst();
    }

    public Optional<AddressEntity> save(AddressEntity address) {
        int rows = jdbcTemplate.update(
                "INSERT INTO address (uuid, city, street,house) VALUES (?, ?, ?, ?)",
                address.getUuid(),
                address.getCity(),
                address.getStreet(),
                address.getHouse()
        );
        return rows > 0 ? Optional.of(address) : Optional.empty();
    }

    public void update(AddressEntity address) {
        jdbcTemplate.update(
                "UPDATE address SET uuid = ?, city = ?, street = ?,house = ? WHERE uuid = ?",
                address.getUuid(),
                address.getCity(),
                address.getStreet(),
                address.getHouse()
        );
    }

    public void delete(UUID uuid) {
        jdbcTemplate.update("DELETE FROM address WHERE uuid = ?", uuid.toString());
    }

}
