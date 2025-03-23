--liquibase formatted sql

--changeset gaunter:1
CREATE TABLE address
(
    uuid      UUID PRIMARY KEY,
    city      VARCHAR(255),
    street    VARCHAR(255),
    house     VARCHAR(255),
    shop_uuid UUID UNIQUE
);

--changeset gaunter:2
CREATE TABLE shop
(
    uuid         UUID PRIMARY KEY,
    name         VARCHAR(255),
    address_uuid UUID UNIQUE
);

--changeset gaunter:3
CREATE TABLE catalog
(
    uuid      UUID PRIMARY KEY,
    shop_uuid UUID UNIQUE
);

--changeset gaunter:4
CREATE TABLE category
(
    uuid  UUID PRIMARY KEY,
    name         VARCHAR(255),
    catalog_uuid UUID
);

--changeset gaunter:5
CREATE TABLE product
(
    uuid        UUID PRIMARY KEY,
    name        VARCHAR(255),
    created_at  TIMESTAMP,
    cost        DECIMAL(19, 2),
    weight      DOUBLE PRECISION,
    description TEXT,
    category_uuid UUID
);

--changeset gaunter:7
ALTER TABLE shop
    ADD CONSTRAINT fk_shop_address FOREIGN KEY (address_uuid) REFERENCES address (uuid) ON DELETE CASCADE;

--changeset gaunter:8
ALTER TABLE catalog
    ADD CONSTRAINT fk_catalog_shop FOREIGN KEY (shop_uuid) REFERENCES shop (uuid) ON DELETE CASCADE;

--changeset gaunter:9
ALTER TABLE category
    ADD CONSTRAINT fk_category_catalog FOREIGN KEY (catalog_uuid) REFERENCES catalog (uuid) ON DELETE CASCADE;

--changeset gaunter:10
ALTER TABLE product
    ADD CONSTRAINT fk_product_category FOREIGN KEY (category_uuid) REFERENCES category (uuid) ON DELETE CASCADE;