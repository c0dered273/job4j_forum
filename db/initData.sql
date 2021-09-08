INSERT INTO authorities(name)
VALUES ('ROLE_ADMIN');
INSERT INTO authorities(name)
VALUES ('ROLE_USER');

INSERT INTO users(username, password, enabled)
VALUES ('Admin', '$2a$10$0Kf2u0c5MsHLlMXBNzh9E.O0HT5sGwOwIvbX2DdAitWc8WnAq5AQK', true);
INSERT INTO users(username, password, enabled)
VALUES ('User', '$2a$10$tGe8/kkEuFlIwkKgLnioJOxLp5gPUekD0hXNEzpuMlpxsF/tC.DDS', true);

INSERT INTO users_authorities(user_id, authority_id)
VALUES ((SELECT id FROM users u WHERE u.username = 'Admin'),
        (SELECT id from authorities a WHERE a.name = 'ROLE_ADMIN'));

INSERT INTO users_authorities(user_id, authority_id)
VALUES ((SELECT id FROM users u WHERE u.username = 'User'),
        (SELECT id from authorities a WHERE a.name = 'ROLE_USER'));

INSERT INTO posts(name, description, user_id, created)
VALUES ('Продаю машину ладу 01',
        '2106',
        (SELECT id from users u where u.username = 'Admin'),
        timestamp '2021-09-03 01:00:00');

INSERT INTO posts(name, description, user_id, created)
VALUES ('Продаю машину ладу 02',
        '2112',
        (SELECT id from users u where u.username = 'User'),
        timestamp '2020-08-28 01:00:00');

INSERT INTO posts(name, description, user_id, created)
VALUES ('Продаю машину ладу 03',
        '2109',
        (SELECT id from users u where u.username = 'User'),
        timestamp '1990-04-17 01:00:00');