DROP TABLE IF EXISTS message;
DROP TABLE IF EXISTS room;
DROP TABLE IF EXISTS person;
DROP TABLE IF EXISTS role;

CREATE TABLE role
(
    id   BIGSERIAL PRIMARY KEY,
    role VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE person
(
    id       BIGSERIAL PRIMARY KEY,
    name     VARCHAR(50)  NOT NULL UNIQUE,
    enabled  BOOLEAN DEFAULT TRUE,
    password VARCHAR(100) NOT NULL,
    role_id  BIGINT       NOT NULL REFERENCES role (id)
);

CREATE TABLE room
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(2000),
    description TEXT,
    created     TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),
    person_id     BIGINT                      NOT NULL REFERENCES person (id)
);

CREATE TABLE message
(
    id          BIGSERIAL PRIMARY KEY,
    description TEXT                        NOT NULL,
    created     TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),
    room_id     BIGINT                      NOT NULL REFERENCES room (id),
    person_id   BIGINT                      NOT NULL REFERENCES person (id)
);
