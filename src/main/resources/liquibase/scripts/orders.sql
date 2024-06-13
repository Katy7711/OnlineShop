-- liquibase formatted sql

-- changeset denieva:1

CREATE TABLE IF NOT EXISTS orders
(
    id         BIGSERIAL PRIMARY KEY,
    user_id   integer REFERENCES users (id),
    product_name      varchar NOT NULL,
    sum       int  NOT NULL,
    status       varchar NOT NULL
    );
