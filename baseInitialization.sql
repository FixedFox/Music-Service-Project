CREATE TABLE authorities_types (
    id INTEGER PRIMARY KEY,
    authority VARCHAR(10) NOT NULL
);
INSERT INTO authorities_types (id, authority)
VALUES (1, 'USER'),
       (2, 'MUSICIAN');

CREATE TABLE tracklist_types (
    id INTEGER PRIMARY KEY,
    tracklist_type_name VARCHAR(8) NOT NULL
);
INSERT INTO tracklist_types (id, tracklist_type_name)
VALUES (1, 'SINGLE'),
       (2, 'EP'),
       (3, 'ALBUM'),
       (4, 'MIXTAPE');

CREATE TABLE genres (
    id SERIAL PRIMARY KEY,
    genre_name VARCHAR(64));

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255) DEFAULT ('<empty>'),
    telegram_nickname VARCHAR(255),
    telegram_id INTEGER
);

CREATE TABLE users_authorities (
    user_id INTEGER REFERENCES users(id),
    authorities_id INTEGER REFERENCES authorities_types(id),
    PRIMARY KEY (user_id, authorities_id)
);

CREATE TABLE tracks (
    id SERIAL PRIMARY KEY,
    track_name VARCHAR(255) NOT NULL,
    count_of_plays INTEGER NOT NULL DEFAULT (0),
    is_adult_content BOOLEAN NOT NULL DEFAULT (FALSE),
    genre_id INTEGER REFERENCES genres(id),
    user_id INTEGER REFERENCES users(id)
);

CREATE TABLE librarys (
    user_id INTEGER REFERENCES users(id),
    track_id INTEGER REFERENCES tracks(id),
    PRIMARY KEY (user_id, track_id)
);

CREATE TABLE creators (
    id SERIAL PRIMARY KEY,
    creator_name VARCHAR(255) UNIQUE NOT NULL,
    user_id INTEGER REFERENCES users(id)
);

CREATE TABLE tracklists (
    id SERIAL PRIMARY KEY,
    tracklist_type_id INTEGER REFERENCES tracklist_types(id),
    name VARCHAR(255) NOT NULL,
    genre_id INTEGER REFERENCES genres(id),
    add_time TIMESTAMP WITH TIME ZONE NOT NULL,
    published BOOLEAN NOT NULL DEFAULT (FALSE)
);

CREATE TABLE creators_tracks (
    creator_id INTEGER REFERENCES creators(id),
    track_id INTEGER REFERENCES tracks(id),
    PRIMARY KEY (creator_id, track_id)
);

CREATE TABLE creators_tracklists (
    creator_id INTEGER REFERENCES creators(id),
    tracklist_id INTEGER REFERENCES tracklists(id),
    PRIMARY KEY (creator_id, tracklist_id)
);

CREATE TABLE tracks_tracklists (
    track_id INTEGER REFERENCES tracks(id),
    tracklist_id INTEGER REFERENCES tracklists(id),
    PRIMARY KEY (track_id, tracklist_id)
);

CREATE TABLE subscriptions (
    user_id INTEGER REFERENCES users(id),
    creator_id INTEGER REFERENCES creators(id),
    PRIMARY KEY (user_id, creator_id)
);
