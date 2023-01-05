INSERT INTO "PUBLIC"."CAMPSITE" VALUES
(1, 3, U&'\bc15\bcf4\ac801', U&'\ba85\c6d4 \cea0\d551\c7a5', U&'\bb3c\c774 \b9d1\c740 \cea0\d551\c7a5\c785\b2c8\b2e4~', U&'\c11c\c6b8\c2dc \ad00\c545\ad6c ~', '02)888-8888', 50000, 2, 6, 14, 12, U&'\c0b0/\c232, \b3c4\c2ec', U&'\d654\c7a5\c2e4, \c0e4\c6cc\c2e4, \bc14\bca0\d050\c7a5, \ac1c\bcc4 \c8fc\cc28, \b9e4\c810, \c7a5\be44\b300\c5ec', U&'\cea0\d551, \cc28\bc15, \d0a4\c988, \ac00\c871', '/campus/campsite/5ba7c3fb-61ee-4351-843b-a9cee7459af1.jpg'),
(2, 4, U&'\bc15\bcf4\ac802', U&'\d64d\cc9c \cea0\d551\c7a5', U&'\acf5\ae30\ac00 \c88b\c740 \cea0\d551\c7a5\c785\b2c8\b2e4~', U&'\acbd\ae30\b3c4 \c591\c8fc', '022)9999-99999', 30000, 1, 4, 13, 13, U&'\c0b0/\c232, \acc4\ace1', U&'\d654\c7a5\c2e4, \c0e4\c6cc\c2e4, \bc14\bca0\d050\c7a5, \ac1c\bcc4 \c8fc\cc28, \c628\b09c\bc29\ae30, \c7a5\be44\b300\c5ec, \c2dd\ae30\b958, \ce68\ad6c\b958', U&'\cea0\d551, \ce74\b77c\bc18, \cc28\bc15, \d0a4\c988, \ac00\c871, \c5f0\c778', '/campus/campsite/982a8da9-42c2-4f10-9108-9be14739cac2.jpg');

INSERT INTO "PUBLIC"."CAMPSITE_IMG" VALUES
(1, 1, '5ba7c3fb-61ee-4351-843b-a9cee7459af1.jpg', U&'\b300\d45c \c774\bbf8\c9c01.jpg', '/campus/campsite/5ba7c3fb-61ee-4351-843b-a9cee7459af1.jpg'),
(2, 1, 'b6140c75-27e6-4550-8797-d30220973cdc.jpg', U&'\c18c\ac1c \c774\bbf8\c9c01.jpg', '/campus/campsite/b6140c75-27e6-4550-8797-d30220973cdc.jpg'),
(3, 1, 'f79351b6-1a9f-482e-893b-393373ff9f2a.jpg', U&'\c18c\ac1c \c774\bbf8\c9c02.jpg', '/campus/campsite/f79351b6-1a9f-482e-893b-393373ff9f2a.jpg'),
(4, 2, '982a8da9-42c2-4f10-9108-9be14739cac2.jpg', U&'\b300\d45c \c774\bbf8\c9c02.jpg', '/campus/campsite/982a8da9-42c2-4f10-9108-9be14739cac2.jpg'),
(5, 2, '5b7307a2-05c4-44c6-be74-075dccf66f22.jpg', U&'\c18c\ac1c \c774\bbf8\c9c03.jpg', '/campus/campsite/5b7307a2-05c4-44c6-be74-075dccf66f22.jpg'),
(6, 2, '1d36471e-f026-4f5e-8f94-6a5b2066f1bd.jpg', U&'\c18c\ac1c \c774\bbf8\c9c04.jpg', '/campus/campsite/1d36471e-f026-4f5e-8f94-6a5b2066f1bd.jpg');

INSERT INTO "PUBLIC"."FAVORITE_CAMPSITE" VALUES
(1, 2, 1),
(2, 1, 1),
(3, 1, 2);

INSERT INTO "PUBLIC"."MEMBER" VALUES
(1, 'user1', '$2a$10$XrVQnl1xZZ9c1ctQx2ek1e7GyPI22VBZxHmYohqgErYqWfqJkaCm2', U&'\d64d\ae38\b3d91', '010-1111-1111', 22, 'USER'),
(2, 'user2', '$2a$10$yosoEX5aZra8NqHdR0hB3OqyKbt5L0JZ9fXzhibzybItixSL3YdtW', U&'\d64d\ae38\b3d92', '010-2222-2222', 33, 'USER'),
(3, 'admin1', '$2a$10$vtC/zMDnSWjQV3DNsKlPVeuzAWX4DSQYfq2nb.wWD178dTE6rGZ2K', U&'\bc15\bcf4\ac801', '010-3333-3333', 44, 'ADMIN'),
(4, 'admin2', '$2a$10$EVGLVibVQCFSJiroojhohOMVFnfOVUrI3kEmU41PKeb3nJ34w2HfO', U&'\bc15\bcf4\ac802', '010-4444-44444', 38, 'ADMIN');

INSERT INTO "PUBLIC"."RESERVATION" VALUES
(1, 2, 1, TIMESTAMP '2023-01-05 14:58:46.754152', DATE '2023-01-10', DATE '2023-01-12', 4, 90000, 'WAITING'),
(2, 1, 1, TIMESTAMP '2023-01-05 14:59:05.326493', DATE '2023-01-17', DATE '2023-01-20', 4, 200000, 'WAITING'),
(3, 2, 2, TIMESTAMP '2023-01-05 14:59:29.215499', DATE '2023-02-03', DATE '2023-02-04', 3, 60000, 'WAITING'),
(4, 1, 2, TIMESTAMP '2023-01-05 15:00:25.128091', DATE '2023-01-19', DATE '2023-01-21', 1, 100000, 'WAITING');

INSERT INTO "PUBLIC"."REVIEW" VALUES
(1, 1, U&'\d64d\cc9c \cea0\d551\c7a5', U&'\d14c\c2a4\d2b8\c6a9 \c774\c6a9\d6c4\ae30 \c785\b2c8\b2e4.', U&'\d14c\c2a4\d2b8\c6a9 \c774\c6a9\d6c4\ae30 \c785\b2c8\b2e4.', U&'\d64d\ae38\b3d91', 1),
(2, 2, U&'\ba85\c6d4 \cea0\d551\c7a5', U&'\d14c\c2a4\d2b8\c6a9 \c774\c6a9\d6c4\ae30 \c785\b2c8\b2e42.', U&'\d14c\c2a4\d2b8\c6a9 \c774\c6a9\d6c4\ae30 \c785\b2c8\b2e42.', U&'\d64d\ae38\b3d92', 1);

INSERT INTO "PUBLIC"."REVIEW_IMG" VALUES
(1, 1, '2f3808c0-43a2-4b6f-b119-a2a0abc47d64.jpg', U&'\b9ac\bdf01.jpg', '/campus/review/2f3808c0-43a2-4b6f-b119-a2a0abc47d64.jpg'),
(2, 1, '8c0278dc-5731-48ef-acea-27700a3f6d2e.jpg', U&'\b9ac\bdf02.jpg', '/campus/review/8c0278dc-5731-48ef-acea-27700a3f6d2e.jpg'),
(3, 1, '', '', ''),
(4, 2, '0b7f73af-3cd1-4463-abd4-ca954112a8e2.jpg', U&'\b9ac\bdf03.jpg', '/campus/review/0b7f73af-3cd1-4463-abd4-ca954112a8e2.jpg'),
(5, 2, '49f3dcfd-4c21-4c91-b24f-0dc5dc5e26db.jpeg', U&'\b9ac\bdf04.jpeg', '/campus/review/49f3dcfd-4c21-4c91-b24f-0dc5dc5e26db.jpeg'),
(6, 2, '', '', '');