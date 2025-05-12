--liquibase formatted sql
--changeset gaunter:1
INSERT INTO users (uuid, email, password, username, firstname, lastname, birthday, phone)
VALUES ('990e8400-f22e-41d4-a716-446655440001', 'user1@example.com', '$2b$12$8pIbjjuUhPcYSaI8r3WFbO1cQM.s1..y/qIe7tJ1h/NXhkd3gZuIK', 'user1', 'John', 'Doe', '1990-01-01',
        '+1-234-567-8900'),
       ('990e8400-f22e-41d4-a716-446655440002', 'user2@example.com', '$2b$12$/K85RM9r/CLW9eT2BIyV7u6.IfAGTIiuT6XGl7o7Dz9kUmB/9EJyu', 'user2', 'Jane', 'Smith', '1985-05-15',
        '+1-234-567-8901'),
       ('990e8400-f22e-41d4-a716-446655440003', 'user3@example.com', '$2b$12$8uMv1RsBeTXppS.t7HGZoOf..OMW5kkYQ3zefLCLiodwmKezgiCiG', 'user3', 'Alice', 'Johnson', '2000-12-25',
        '+1-234-567-8902');

--changeset gaunter:2
INSERT INTO roles (user_uuid, role)
VALUES ((SELECT uuid FROM users WHERE email = 'user1@example.com') , 'ROLE_BUYER'),
       ((SELECT uuid FROM users WHERE email = 'user2@example.com'), 'ROLE_SHOP_OWNER'),
       ((SELECT uuid FROM users WHERE email = 'user3@example.com'), 'ROLE_ADMIN');


--changeset gaunter:3
INSERT INTO roles (user_uuid, role)
VALUES ((SELECT uuid FROM users WHERE email = 'user1@example.com') , 'ROLE_SHOP_OWNER');