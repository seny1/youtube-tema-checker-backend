CREATE TABLE client (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR,
    surname VARCHAR,
    nickname VARCHAR,
    login VARCHAR,
    password VARCHAR
);

CREATE TABLE channel (
    id BIGSERIAL PRIMARY KEY,
    uuid UUID NOT NULL,
    title VARCHAR(50),
    link VARCHAR,
    thematic VARCHAR(50),
    client_id BIGINT REFERENCES client (id)
);

CREATE TABLE video (
    id BIGSERIAL PRIMARY KEY,
    uuid UUID NOT NULL,
    title VARCHAR,
    link VARCHAR,
    client_id BIGINT REFERENCES client (id),
    channel_id BIGINT REFERENCES channel(id)
);