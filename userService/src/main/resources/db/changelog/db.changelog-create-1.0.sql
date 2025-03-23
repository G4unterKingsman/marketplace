--liquibase formatted sql

--changeset gaunter:1
CREATE TABLE users
(
    uuid      UUID PRIMARY KEY,
    email     varchar(255) not null unique,
    password  varchar(255) not null,
    username  varchar(255) not null unique,

    firstname varchar(255),
    lastname  varchar(255),
    birthday  TIMESTAMP,
    phone     varchar(20)
);

--changeset gaunter:2
CREATE TABLE roles
(
    user_uuid UUID         NOT NULL,
    role      VARCHAR(255) NOT NULL,
    CONSTRAINT fk_user_roles FOREIGN KEY (user_uuid) REFERENCES users (uuid) ON DELETE CASCADE
);

--changeset gaunter:3
CREATE INDEX idx_role_user ON roles (user_uuid, role);