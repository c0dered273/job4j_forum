DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS users_authorities;
DROP TABLE IF EXISTS posts;

CREATE TABLE authorities
(
    id      INT GENERATED ALWAYS AS IDENTITY,
    name    VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE users
(
    id       INT GENERATED ALWAYS AS IDENTITY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(60)  NOT NULL,
    enabled  BOOLEAN,
    PRIMARY KEY (id)
);

CREATE TABLE users_authorities
(
    user_id      INT NOT NULL,
    authority_id INT NOT NULL,
    PRIMARY KEY (user_id, authority_id)
);

CREATE TABLE posts
(
    id          INT GENERATED ALWAYS AS IDENTITY,
    name        VARCHAR(255) NOT NULL,
    description TEXT,
    user_id     INT          NOT NULL,
    created     TIMESTAMP,
    PRIMARY KEY (id)
);