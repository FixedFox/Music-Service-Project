INSERT INTO users (id, username, password, name)
VALUES (1, 'fox', '$2a$10$gBptCf5PWkASnqv5MyzLhOdBKPFNzc6bYW.GP/prJ6RF8ZBxddRtW', 'FixedFox'),
       (2, 'red', '$2a$10$xQXLTRJIZUReStPRmynXwOMldIU7g1yye/4vxJAgGIuZo.oxxLUVm', 'Red');

INSERT INTO users_authorities (user_id, authorities_id)
VALUES (1, 2),
       (2, 1);

INSERT INTO creators (id, creator_name, user_id)
VALUES (1, 'Flume', 1),
       (2, 'Justice', 1),
       (3, 'Dorian Electra', 1);

INSERT INTO genres (id, genre_name)
VALUES (1, 'Electronica'),
       (2, 'House'),
       (3, 'Hyper-Pop');

INSERT INTO tracks (id, track_name, count_of_plays, is_adult_content, genre_id, user_id)
VALUES (1, 'Holdin On', 26755537, false, 1, 1),
       (2, 'Genesis', 14230304, false, 2, 1),
       (3, 'D.A.N.C.E.', 100000000, false, 2, 1),
       (4, 'Adam & Steve', 4000202, true, 3, 1);

INSERT INTO tracklists (id, tracklist_type_id, name, genre_id, add_time, published)
VALUES (1, 3, 'Flume', 1, now(), false),
       (2, 3, 'Cross', 2, now(), true),
       (3, 3, 'Flamboyant', 3, now(), false);

INSERT INTO tracks_tracklists (track_id, tracklist_id)
VALUES (2,2),
       (3,2);

INSERT INTO creators_tracks (creator_id, track_id)
VALUES (2, 2),
       (2,3);

INSERT INTO creators_tracklists (creator_id, tracklist_id)
VALUES (2,2);


