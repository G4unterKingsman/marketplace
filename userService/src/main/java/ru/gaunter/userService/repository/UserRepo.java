package ru.gaunter.userService.repository;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import ru.gaunter.userService.entity.enums.Role;
import ru.gaunter.userService.entity.User;

import java.sql.SQLException;
import java.util.*;


@Repository
public class UserRepo {

    private final JdbcTemplate jdbcTemplate;

    public UserRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final ResultSetExtractor<List<User>> userWithRolesExtractor = rs -> {
        Map<UUID, User> userMap = new HashMap<>();
        while (rs.next()) {
            UUID uuid = UUID.fromString(rs.getString("uuid"));
            User user = userMap.computeIfAbsent(uuid, id -> {
                try {
                    return User.builder()
                            .uuid(id)
                            .birthday(rs.getTimestamp("birthday").toLocalDateTime())
                            .email(rs.getString("email"))
                            .firstname(rs.getString("firstname"))
                            .lastname(rs.getString("lastname"))
                            .password(rs.getString("password"))
                            .phone(rs.getString("phone"))
                            .username(rs.getString("username"))
                            .roles(new HashSet<>())
                            .build();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            user.getRoles().add(Role.valueOf(rs.getString("role")));
        }
        return new ArrayList<>(userMap.values());
    };


    public List<User> findAll() {
        return jdbcTemplate.query("""
                SELECT u.uuid, u.email, u.password, u.username, u.firstname, u.lastname, u.birthday, u.phone, r.user_uuid, r.role
                FROM users u
                LEFT OUTER JOIN roles r on r.user_uuid = u.uuid
                """, userWithRolesExtractor);
    }

    public Optional<User> findById(UUID uuid) {
        return jdbcTemplate.query("""
                        SELECT u.uuid, u.email, u.password, u.username, u.firstname, u.lastname, u.birthday, u.phone, r.user_uuid, r.role
                        FROM users u
                        LEFT OUTER JOIN roles r on r.user_uuid = u.uuid
                        WHERE u.uuid = ?
                        """, userWithRolesExtractor, uuid)
                .stream()
                .findFirst();
    }


    public Optional<User> findByUsername(String username) {
        return jdbcTemplate.query("""
                        SELECT u.uuid, u.email, u.password, u.username, u.firstname, u.lastname, u.birthday, u.phone, r.user_uuid, r.role
                        FROM users u
                        LEFT OUTER JOIN roles r on r.user_uuid = u.uuid
                        WHERE u.username = ?
                        """, userWithRolesExtractor, username)
                .stream()
                .findFirst();
    }
    public void saveUser(User user) {
        String sql = "INSERT INTO users (uuid, email, username, password, firstname, lastname, birthday, phone) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                user.getUuid(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword(),
                user.getFirstname(),
                user.getLastname(),
                user.getBirthday(),
                user.getPhone());

        saveUserRoles(user);
    }

    private void saveUserRoles(User user) {
        String deleteRolesSql = "DELETE FROM roles WHERE user_uuid = ?";
        jdbcTemplate.update(deleteRolesSql, user.getUuid());

        String insertRoleSql = "INSERT INTO roles (user_uuid, role) VALUES (?, ?)";
        for (Role role : user.getRoles()) {
            jdbcTemplate.update(insertRoleSql, user.getUuid(), role.name());
        }
    }


    public void updateUser(User user) {
        String sql = """
                UPDATE users
                SET email = ?, username = ?, password = ?, firstname = ?, lastname = ?, birthday = ?, phone = ?
                WHERE uuid = ?
                """;
        jdbcTemplate.update(sql,
                user.getEmail(),
                user.getUsername(),
                user.getPassword(),
                user.getFirstname(),
                user.getLastname(),
                user.getBirthday(),
                user.getPhone(),
                user.getUuid());

        saveUserRoles(user);
    }

    public void deleteUser(UUID userUuid) {
        String deleteRolesSql = "DELETE FROM roles WHERE user_uuid = ?";
        jdbcTemplate.update(deleteRolesSql, userUuid);

        String deleteUserSql = "DELETE FROM users WHERE uuid = ?";
        jdbcTemplate.update(deleteUserSql, userUuid);
    }

}

