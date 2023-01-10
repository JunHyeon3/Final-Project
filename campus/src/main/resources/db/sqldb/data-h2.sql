INSERT INTO "PUBLIC"."MEMBER" VALUES
(1, 'user1', '$2a$10$XrVQnl1xZZ9c1ctQx2ek1e7GyPI22VBZxHmYohqgErYqWfqJkaCm2', U&'\d64d\ae38\b3d91', '010-1111-1111', 22, 'USER'),
(2, 'user2', '$2a$10$yosoEX5aZra8NqHdR0hB3OqyKbt5L0JZ9fXzhibzybItixSL3YdtW', U&'\d64d\ae38\b3d92', '010-2222-2222', 33, 'USER'),
(3, 'admin1', '$2a$10$vtC/zMDnSWjQV3DNsKlPVeuzAWX4DSQYfq2nb.wWD178dTE6rGZ2K', U&'\bc15\bcf4\ac801', '010-3333-3333', 44, 'ADMIN'),
(4, 'admin2', '$2a$10$EVGLVibVQCFSJiroojhohOMVFnfOVUrI3kEmU41PKeb3nJ34w2HfO', U&'\bc15\bcf4\ac802', '010-4444-44444', 31, 'ADMIN'),
(5, 'admin3', '$2a$10$EVGLVibVQCFSJiroojhohOMVFnfOVUrI3kEmU41PKeb3nJ34w2HfO', U&'\bc15\bcf4\ac803', '010-4444-44444', 32, 'ADMIN'),
(6, 'admin4', '$2a$10$EVGLVibVQCFSJiroojhohOMVFnfOVUrI3kEmU41PKeb3nJ34w2HfO', U&'\bc15\bcf4\ac804', '010-4444-44444', 33, 'ADMIN'),
(7, 'admin5', '$2a$10$EVGLVibVQCFSJiroojhohOMVFnfOVUrI3kEmU41PKeb3nJ34w2HfO', U&'\bc15\bcf4\ac805', '010-4444-44444', 34, 'ADMIN'),
(8, 'admin6', '$2a$10$EVGLVibVQCFSJiroojhohOMVFnfOVUrI3kEmU41PKeb3nJ34w2HfO', U&'\bc15\bcf4\ac806', '010-4444-44444', 35, 'ADMIN'),
(9, 'admin7', '$2a$10$EVGLVibVQCFSJiroojhohOMVFnfOVUrI3kEmU41PKeb3nJ34w2HfO', U&'\bc15\bcf4\ac807', '010-4444-44444', 36, 'ADMIN'),
(10, 'admin8', '$2a$10$EVGLVibVQCFSJiroojhohOMVFnfOVUrI3kEmU41PKeb3nJ34w2HfO', U&'\bc15\bcf4\ac808', '010-4444-44444', 28, 'ADMIN'),
(11, 'admin9', '$2a$10$EVGLVibVQCFSJiroojhohOMVFnfOVUrI3kEmU41PKeb3nJ34w2HfO', U&'\bc15\bcf4\ac809', '010-4444-44444', 39, 'ADMIN'),
(12, 'admin10', '$2a$10$EVGLVibVQCFSJiroojhohOMVFnfOVUrI3kEmU41PKeb3nJ34w2HfO', U&'\bc15\bcf4\ac810', '010-4444-44444', 40, 'ADMIN'),
(13, 'admin11', '$2a$10$EVGLVibVQCFSJiroojhohOMVFnfOVUrI3kEmU41PKeb3nJ34w2HfO', U&'\bc15\bcf4\ac811', '010-4444-44444', 55, 'ADMIN');

INSERT INTO "PUBLIC"."CAMPSITE" VALUES
(1, 3, U&'\bc15\bcf4\ac801', U&'\ba85\c6d4 \cea0\d551\c7a5', U&'\c774\bc88\c5d0 \c0c8\b85c \c624\d508\d55c \cea0\d551\c7a5\c785\b2c8\b2e4~', U&'\acbd\ae30\b3c4 \acfc\cc9c\c2dc', '051) 1111-1111', 50000, 1, 6, 12, 11, '/campus/campsite/509451e7-bdf0-44fc-8347-5b3ff2526650.jpg'),
(2, 4, U&'\bc15\bcf4\ac802', U&'\d64d\cc9c \cea0\d551\c7a5', U&'\acf5\ae30\ac00 \b9d1\c740 \cea0\d551\c7a5\c785\b2c8\b2e4!!', U&'\c11c\c6b8\c2dc \ad00\c545\ad6c', '02) 888-8888', 30000, 2, 4, 14, 12, '/campus/campsite/d13fe0c8-f9ce-4264-8bb3-751ed9298ecd.jpg'),
(3, 5, U&'\bc15\bcf4\ac803', '한신 캠핑장', U&'\acf5\ae30\ac00 \b9d1\c740 \cea0\d551\c7a5\c785\b2c8\b2e4!!', '경기도 광명', '02) 888-8888', 30000, 2, 4, 14, 12, '/campus/campsite/9612e63d-9b9e-4fe1-ae9e-c50c674e8340.jpg'),
(4, 6, U&'\bc15\bcf4\ac804', '주원 캠핑장', U&'\acf5\ae30\ac00 \b9d1\c740 \cea0\d551\c7a5\c785\b2c8\b2e4!!', '강원도 평창', '02) 123-1234', 40000, 2, 4, 14, 12, '/campus/campsite/791056df-5650-4f3f-98c1-fb3941b25703.jpg'),
(5, 7, U&'\bc15\bcf4\ac805', '대신 캠핑장', U&'\acf5\ae30\ac00 \b9d1\c740 \cea0\d551\c7a5\c785\b2c8\b2e4!!', '제주도 서귀포', '02) 435-3455', 50000, 2, 4, 14, 12, '/campus/campsite/72e9388f-faec-4911-acd0-ae092a292985.jpg'),
(6, 8, U&'\bc15\bcf4\ac806', '개룡 캠핑장', U&'\acf5\ae30\ac00 \b9d1\c740 \cea0\d551\c7a5\c785\b2c8\b2e4!!', '강원도 강릉', '02) 888-1234', 20000, 2, 4, 14, 12, '/campus/campsite/81222e46-a605-46dd-a347-56317bf9860e.jpg'),
(7, 9, U&'\bc15\bcf4\ac807', '풍악 캠핑장', U&'\acf5\ae30\ac00 \b9d1\c740 \cea0\d551\c7a5\c785\b2c8\b2e4!!', '부산 해운대', '02) 999-6132', 70000, 2, 4, 14, 12, '/campus/campsite/8e94cbc4-9740-445e-b9ac-97fdc62bcd64.jpg'),
(8, 10, U&'\bc15\bcf4\ac808', '태릉 캠핑장', U&'\acf5\ae30\ac00 \b9d1\c740 \cea0\d551\c7a5\c785\b2c8\b2e4!!', '강원도 고성', '02) 2651-2521', 50000, 2, 4, 14, 12, '/campus/campsite/c24f4f85-c49a-4be3-ae80-264771a62491.jpg'),
(9, 11, U&'\bc15\bcf4\ac810', '미성 캠핑장', U&'\acf5\ae30\ac00 \b9d1\c740 \cea0\d551\c7a5\c785\b2c8\b2e4!!', '강원도 속초', '02) 9075-2345', 60000, 2, 4, 14, 12, '/campus/campsite/dbf15958-691f-4a36-8efe-d4674ada7ada.jpg'),
(10, 12, U&'\bc15\bcf4\ac811', '수원 캠핑장', U&'\acf5\ae30\ac00 \b9d1\c740 \cea0\d551\c7a5\c785\b2c8\b2e4!!', '강원도 철원', '02) 123-7777', 40000, 2, 4, 14, 12, '/campus/campsite/cc32c218-b12a-4d66-87dd-2f10e352734b.jpg'),
(11, 13, U&'\bc15\bcf4\ac812', '한강 캠핑장', U&'\acf5\ae30\ac00 \b9d1\c740 \cea0\d551\c7a5\c785\b2c8\b2e4!!', '서울시 용산구', '02) 552-2465', 10000, 2, 4, 14, 12, '/campus/campsite/6c540862-099e-48f8-9428-655037c1fda6.jpg');

INSERT INTO "PUBLIC"."CAMPSITE_IMG" VALUES
(1, 1, '509451e7-bdf0-44fc-8347-5b3ff2526650.jpg', U&'\b300\d45c \c774\bbf8\c9c01.jpg', '/campus/campsite/509451e7-bdf0-44fc-8347-5b3ff2526650.jpg'),
(2, 1, '6073d39b-5811-4948-86ef-f58c2566b29e.jpg', U&'\c18c\ac1c \c774\bbf8\c9c01.jpg', '/campus/campsite/6073d39b-5811-4948-86ef-f58c2566b29e.jpg'),
(3, 1, 'f8155312-e6ff-41f8-8639-98feca92f16c.jpg', U&'\c18c\ac1c \c774\bbf8\c9c02.jpg', '/campus/campsite/f8155312-e6ff-41f8-8639-98feca92f16c.jpg'),
(4, 2, 'd13fe0c8-f9ce-4264-8bb3-751ed9298ecd.jpg', U&'\b300\d45c \c774\bbf8\c9c02.jpg', '/campus/campsite/d13fe0c8-f9ce-4264-8bb3-751ed9298ecd.jpg'),
(5, 2, 'ae755471-7099-4e4d-9230-d1bdf974234a.jpg', U&'\c18c\ac1c \c774\bbf8\c9c03.jpg', '/campus/campsite/ae755471-7099-4e4d-9230-d1bdf974234a.jpg'),
(6, 2, '1d340854-4039-402f-af9d-e3ba8a1f316c.jpg', U&'\c18c\ac1c \c774\bbf8\c9c04.jpg', '/campus/campsite/1d340854-4039-402f-af9d-e3ba8a1f316c.jpg'),
(7, 3, U&'\b300\d45c \c774\bbf8\c9c03.jpg', '9612e63d-9b9e-4fe1-ae9e-c50c674e8340.jpg', '/campus/campsite/9612e63d-9b9e-4fe1-ae9e-c50c674e8340.jpg'),
(8, 3, '', '', ''),
(9, 3, '', '', ''),
(10, 4, U&'\b300\d45c \c774\bbf8\c9c04.jpg', '791056df-5650-4f3f-98c1-fb3941b25703.jpg', '/campus/campsite/791056df-5650-4f3f-98c1-fb3941b25703.jpg'),
(11, 4, '', '', ''),
(12, 4, '', '', ''),
(13, 5, U&'\b300\d45c \c774\bbf8\c9c05.jpg', '72e9388f-faec-4911-acd0-ae092a292985.jpg', '/campus/campsite/72e9388f-faec-4911-acd0-ae092a292985.jpg'),
(14, 5, '', '', ''),
(15, 5, '', '', ''),
(16, 6, U&'\b300\d45c \c774\bbf8\c9c06.jpg', '81222e46-a605-46dd-a347-56317bf9860e.jpg', '/campus/campsite/81222e46-a605-46dd-a347-56317bf9860e.jpg'),
(17, 6, '', '', ''),
(18, 6, '', '', ''),
(19, 7, U&'\b300\d45c \c774\bbf8\c9c07.jpg', '8e94cbc4-9740-445e-b9ac-97fdc62bcd64.jpg', '/campus/campsite/8e94cbc4-9740-445e-b9ac-97fdc62bcd64.jpg'),
(20, 7, '', '', ''),
(21, 7, '', '', ''),
(22, 8, U&'\b300\d45c \c774\bbf8\c9c08.jpg', 'c24f4f85-c49a-4be3-ae80-264771a62491.jpg', '/campus/campsite/c24f4f85-c49a-4be3-ae80-264771a62491.jpg'),
(23, 8, '', '', ''),
(24, 8, '', '', ''),
(25, 9, U&'\b300\d45c \c774\bbf8\c9c09.jpg', 'dbf15958-691f-4a36-8efe-d4674ada7ada.jpg', '/campus/campsite/dbf15958-691f-4a36-8efe-d4674ada7ada.jpg'),
(26, 9, '', '', ''),
(27, 9, '', '', ''),
(28, 10, U&'\b300\d45c \c774\bbf8\c9c010.jpg', 'cc32c218-b12a-4d66-87dd-2f10e352734b.jpg', '/campus/campsite/cc32c218-b12a-4d66-87dd-2f10e352734b.jpg'),
(29, 10, '', '', ''),
(30, 10, '', '', ''),
(31, 11, U&'\b300\d45c \c774\bbf8\c9c011.jpg', '6c540862-099e-48f8-9428-655037c1fda6.jpg', '/campus/campsite/6c540862-099e-48f8-9428-655037c1fda6.jpg'),
(32, 11, '', '', ''),
(33, 11, '', '', '');

INSERT INTO "PUBLIC"."FAVORITE_CAMPSITE" VALUES
(1, 2, 2),
(2, 1, 2),
(3, 1, 1);

INSERT INTO "PUBLIC"."FACILITIES" VALUES
(1, U&'\c0e4\c6cc\c2e4'), (1, U&'\c2dd\ae30\b958'), (1, U&'\c7a5\be44\b300\c5ec'),
(2, U&'\bc14\bca0\d050\c7a5'), (2, U&'\c218\c601\c7a5'), (2, U&'\d654\c7a5\c2e4'),
(3, U&'\c0e4\c6cc\c2e4'), (3, U&'\bc14\bca0\d050\c7a5'), (3, U&'\d654\c7a5\c2e4'),
(4, '화장실'), (4, '샤워실'), (4, '개별 주차'), 
(5, '화장실'), (5, '샤워실'), (5, '장비대여'), 
(6, '화장실'), (6, '샤워실'), (6, '매점'), 
(7, '화장실'), (7, '샤워실'), (7, '장비대여'), 
(8, '화장실'), (8, '샤워실'), (8, '개별 주차'), 
(9, '화장실'), (9, '샤워실'), (9, '매점'), 
(10, '화장실'), (10, '샤워실'), (10, '장비대여'),
(11, '화장실'), (11, '샤워실'), (11, '개별 주차');

INSERT INTO "PUBLIC"."ENVIRONMENTS" VALUES
(1, U&'\c0b0/\c232'), (1, U&'\bc14\b2e4'), (1, U&'\acc4\ace1'), (1, U&'\c12c'), 
(2, U&'\ac15'), (2, U&'\c0b0/\c232'), (2, U&'\b3c4\c2ec'),
(3, '도심'), (3, '강'),
(4, '산/숲'), (4, '계곡'), 
(5, '바다'), (5, '섬'),
(6, '바다'), (6, '계곡'), 
(7, '바다'), (7, '계곡'), 
(8, '바다'), (8, '계곡'), 
(9, '도심'), (9, '강'), 
(10, '도심'), (10, '계곡'), 
(11, '도심'), (11, '계곡');


INSERT INTO "PUBLIC"."THEMES" VALUES
(1, U&'\c5f0\c778'), (1, U&'\ac00\c871'), (1, U&'\bc18\b824\b3d9\bb3c'), (1, U&'\cea0\d551'), (1, U&'\d0a4\c988'),
(2, U&'\cc28\bc15'), (2, U&'\ae00\b7a8\d551'), (2, U&'\ce74\b77c\bc18'),
(3, '캠핑'), (3, '가족'),(3, '반려동물'), 
(4, '캠핑'), (4, '가족'), (4, '반려동물'), 
(5, '캠핑'), (5, '글램핑'), 
(6, '글램핑'), (6, '키즈'), (6, '가족'), 
(7, '글램핑'), (7, '가족'), (7, '연인'), 
(8, '차박'), (8, '가족'), (8, '키즈'), 
(9, '카라반'), (9, '연인'), 
(10, '차박'), (10, '가족'), (10, '키즈'), 
(11, '카라반'), (11, '연인');

INSERT INTO "PUBLIC"."RESERVATION" VALUES
(1, 2, 1, TIMESTAMP '2023-01-07 19:10:31.660079', '2023-01-08', '2023-01-10', 2, 60000, 'WAITING'),
(2, 1, 1, TIMESTAMP '2023-01-07 19:10:45.574183', '2023-01-28', '2023-01-29', 4, 50000, 'WAITING'),
(3, 2, 2, TIMESTAMP '2023-01-07 19:11:09.654262', '2023-01-14', '2023-01-15', 4, 30000, 'WAITING');


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