INSERT INTO "PUBLIC"."MEMBER" VALUES
(1, 'user1', '$2a$10$XrVQnl1xZZ9c1ctQx2ek1e7GyPI22VBZxHmYohqgErYqWfqJkaCm2', U&'\d64d\ae38\b3d91', '010-1111-1111', 22, 'USER'),
(2, 'user2', '$2a$10$yosoEX5aZra8NqHdR0hB3OqyKbt5L0JZ9fXzhibzybItixSL3YdtW', U&'\d64d\ae38\b3d92', '010-2222-2222', 33, 'USER'),
(3, 'admin1', '$2a$10$vtC/zMDnSWjQV3DNsKlPVeuzAWX4DSQYfq2nb.wWD178dTE6rGZ2K', U&'\bc15\bcf4\ac801', '010-3333-3333', 44, 'ADMIN'),
(4, 'admin2', '$2a$10$EVGLVibVQCFSJiroojhohOMVFnfOVUrI3kEmU41PKeb3nJ34w2HfO', U&'\bc15\bcf4\ac802', '010-4444-44444', 38, 'ADMIN');

INSERT INTO "PUBLIC"."CAMPSITE" VALUES
(1, 3, U&'\bc15\bcf4\ac801', U&'\ba85\c6d4 \cea0\d551\c7a5', U&'\c774\bc88\c5d0 \c0c8\b85c \c624\d508\d55c \cea0\d551\c7a5\c785\b2c8\b2e4~', U&'\acbd\ae30\b3c4 \acfc\cc9c\c2dc', '051) 1111-1111', 50000, 1, 6, 12, 11, '/campus/campsite/509451e7-bdf0-44fc-8347-5b3ff2526650.jpg'),
(2, 4, U&'\bc15\bcf4\ac802', U&'\d64d\cc9c \cea0\d551\c7a5', U&'\acf5\ae30\ac00 \b9d1\c740 \cea0\d551\c7a5\c785\b2c8\b2e4!!', U&'\c11c\c6b8\c2dc \ad00\c545\ad6c', '02) 888-8888', 30000, 2, 4, 14, 12, '/campus/campsite/d13fe0c8-f9ce-4264-8bb3-751ed9298ecd.jpg');

INSERT INTO "PUBLIC"."CAMPSITE_IMG" VALUES
(1, 3, '509451e7-bdf0-44fc-8347-5b3ff2526650.jpg', U&'\b300\d45c \c774\bbf8\c9c01.jpg', '/campus/campsite/509451e7-bdf0-44fc-8347-5b3ff2526650.jpg'),
(2, 3, '6073d39b-5811-4948-86ef-f58c2566b29e.jpg', U&'\c18c\ac1c \c774\bbf8\c9c01.jpg', '/campus/campsite/6073d39b-5811-4948-86ef-f58c2566b29e.jpg'),
(3, 3, 'f8155312-e6ff-41f8-8639-98feca92f16c.jpg', U&'\c18c\ac1c \c774\bbf8\c9c02.jpg', '/campus/campsite/f8155312-e6ff-41f8-8639-98feca92f16c.jpg'),
(4, 4, 'd13fe0c8-f9ce-4264-8bb3-751ed9298ecd.jpg', U&'\b300\d45c \c774\bbf8\c9c02.jpg', '/campus/campsite/d13fe0c8-f9ce-4264-8bb3-751ed9298ecd.jpg'),
(5, 4, 'ae755471-7099-4e4d-9230-d1bdf974234a.jpg', U&'\c18c\ac1c \c774\bbf8\c9c03.jpg', '/campus/campsite/ae755471-7099-4e4d-9230-d1bdf974234a.jpg'),
(6, 4, '1d340854-4039-402f-af9d-e3ba8a1f316c.jpg', U&'\c18c\ac1c \c774\bbf8\c9c04.jpg', '/campus/campsite/1d340854-4039-402f-af9d-e3ba8a1f316c.jpg');

INSERT INTO "PUBLIC"."FACILITIES" VALUES
(1, U&'\c0e4\c6cc\c2e4'),
(1, U&'\c2dd\ae30\b958'),
(1, U&'\c7a5\be44\b300\c5ec'),
(2, U&'\bc14\bca0\d050\c7a5'),
(2, U&'\c218\c601\c7a5'),
(2, U&'\d654\c7a5\c2e4'),
(3, U&'\c0e4\c6cc\c2e4'),
(3, U&'\bc14\bca0\d050\c7a5'),
(3, U&'\d654\c7a5\c2e4');

INSERT INTO "PUBLIC"."ENVIRONMENTS" VALUES
(1, U&'\c0b0/\c232'),
(1, U&'\bc14\b2e4'),
(1, U&'\acc4\ace1'),
(1, U&'\c12c'),
(2, U&'\ac15'),
(2, U&'\c0b0/\c232'),
(2, U&'\b3c4\c2ec');

INSERT INTO "PUBLIC"."THEMES" VALUES
(1, U&'\c5f0\c778'),
(1, U&'\ac00\c871'),
(1, U&'\bc18\b824\b3d9\bb3c'),
(1, U&'\cea0\d551'),
(1, U&'\d0a4\c988'),
(2, U&'\cc28\bc15'),
(2, U&'\ae00\b7a8\d551'),
(2, U&'\ce74\b77c\bc18');

INSERT INTO "PUBLIC"."RESERVATION" VALUES
(1, 2, 1, TIMESTAMP '2023-01-07 19:10:31.660079', '2023-01-08', '2023-01-10', 2, 60000, 'WAITING'),
(2, 1, 1, TIMESTAMP '2023-01-07 19:10:45.574183', '2023-01-28', '2023-01-29', 4, 50000, 'WAITING'),
(3, 2, 2, TIMESTAMP '2023-01-07 19:11:09.654262', '2023-01-14', '2023-01-15', 4, 30000, 'WAITING');

INSERT INTO "PUBLIC"."FAVORITE_CAMPSITE" VALUES
(1, 2, 2),
(2, 1, 2),
(3, 1, 1);

INSERT INTO "PUBLIC"."REVIEW" VALUES
(1, 1, U&'\d64d\cc9c \cea0\d551\c7a5', U&'\d14c\c2a4\d2b8\c6a9 \c774\c6a9 \d6c4\ae30 \c785\b2c8\b2e4~1', U&'\d14c\c2a4\d2b8\c6a9 \c774\c6a9 \d6c4\ae30 \c785\b2c8\b2e4~1', U&'\d64d\ae38\b3d91', 1),
(2, 1, U&'\ba85\c6d4 \cea0\d551\c7a5', U&'\d14c\c2a4\d2b8\c6a9 \c774\c6a9 \d6c4\ae30 \c785\b2c8\b2e4~2', U&'\d14c\c2a4\d2b8\c6a9 \c774\c6a9 \d6c4\ae30 \c785\b2c8\b2e4~2', U&'\d64d\ae38\b3d91', 0),
(3, 2, U&'\d64d\cc9c \cea0\d551\c7a5', U&'\d14c\c2a4\d2b8\c6a9 \c774\c6a9 \d6c4\ae30 \c785\b2c8\b2e4~3', U&'\d14c\c2a4\d2b8\c6a9 \c774\c6a9 \d6c4\ae30 \c785\b2c8\b2e4~3', U&'\d64d\ae38\b3d92', 1);

INSERT INTO "PUBLIC"."REVIEW_IMG" VALUES
(1, 1, '5f207a68-87e5-4f4b-a3c5-922fb3a4d36a.jpg', U&'\b9ac\bdf01.jpg', '/campus/review/5f207a68-87e5-4f4b-a3c5-922fb3a4d36a.jpg'),
(2, 1, 'de8efdd8-7077-4649-b42c-04fb3bb8db36.jpg', U&'\b9ac\bdf02.jpg', '/campus/review/de8efdd8-7077-4649-b42c-04fb3bb8db36.jpg'),
(3, 1, '', '', ''),
(4, 2, 'b1c76adc-2792-49a0-a286-a63c7f170dae.jpg', U&'\b9ac\bdf03.jpg', '/campus/review/b1c76adc-2792-49a0-a286-a63c7f170dae.jpg'),
(5, 2, '', '', ''),
(6, 2, '', '', ''),
(7, 3, '2366ddc3-cc5c-4545-b2aa-e73462e152f8.jpeg', U&'\b9ac\bdf04.jpeg', '/campus/review/2366ddc3-cc5c-4545-b2aa-e73462e152f8.jpeg'),
(8, 3, '', '', ''),
(9, 3, '', '', '');