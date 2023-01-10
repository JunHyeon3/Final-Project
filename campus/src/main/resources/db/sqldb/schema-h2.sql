drop table if exists CAMPSITE cascade;
drop table if exists CAMPSITE_IMG cascade;
drop table if exists FAVORITE_CAMPSITE cascade;
drop table if exists MEMBER cascade;
drop table if exists RESERVATION cascade;
drop table if exists REVIEW cascade;
drop table if exists REVIEW_IMG cascade;
drop table if exists ENVIRONMENTS cascade;
drop table if exists FACILITIES cascade;
drop table if exists THEMES cascade;

CREATE CACHED TABLE "PUBLIC"."MEMBER"(
    "MEMBER_NO" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1 RESTART WITH 14) NOT NULL,
    "MEMBER_ID" CHARACTER VARYING(255) NOT NULL,
    "MEMBER_PW" CHARACTER VARYING(255) NOT NULL,
    "MEMBER_NAME" CHARACTER VARYING(255) NOT NULL,
    "MEMBER_PHONE" CHARACTER VARYING(255) NOT NULL,
    "MEMBER_AGE" INTEGER NOT NULL,
    "MEMBER_ROLE" CHARACTER VARYING(255) NOT NULL
);

CREATE CACHED TABLE "PUBLIC"."CAMPSITE"(
    "CAMPSITE_ID" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1 RESTART WITH 12) NOT NULL,
    "MEMBER_NO" BIGINT NOT NULL,
    "CAMPSITE_MANAGER" CHARACTER VARYING(255) NOT NULL,
    "CAMPSITE_NAME" CHARACTER VARYING(255) NOT NULL,
    "CAMPSITE_INTRODUCTION" CHARACTER VARYING(255) NOT NULL,
    "CAMPSITE_ADDRESS" CHARACTER VARYING(255) NOT NULL,
    "CAMPSITE_TEL" CHARACTER VARYING(255) NOT NULL,
    "CAMPSITE_PRICE" INTEGER NOT NULL,
    "CAMPSITE_MIN" INTEGER NOT NULL,
    "CAMPSITE_MAX" INTEGER NOT NULL,
    "CAMPSITE_CHECKIN" INTEGER NOT NULL,
    "CAMPSITE_CHECKOUT" INTEGER NOT NULL,
    "CAMPSITE_MAIN_IMG_PATH" CHARACTER VARYING(255)
);

CREATE CACHED TABLE "PUBLIC"."CAMPSITE_IMG"(
    "CAMPSITE_IMG_ID" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1 RESTART WITH 7) NOT NULL,
    "CAMPSITE_ID" BIGINT NOT NULL,
    "CAMPSITE_IMG_ORIGINAL" CHARACTER VARYING(255) NOT NULL,
    "CAMPSITE_IMG_NAME" CHARACTER VARYING(255) NOT NULL,
    "CAMPSITE_IMG_PATH" CHARACTER VARYING(255) NOT NULL
);

CREATE CACHED TABLE "PUBLIC"."FAVORITE_CAMPSITE"(
    "FAVORITE_ID" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1 RESTART WITH 4) NOT NULL,
    "CAMPSITE_ID" BIGINT,
    "MEMBER_NO" BIGINT
);

CREATE CACHED TABLE "PUBLIC"."FACILITIES"(
    "CAMPSITE_ID" BIGINT NOT NULL,
    "FACILITIES" CHARACTER VARYING(255)
);

CREATE CACHED TABLE "PUBLIC"."ENVIRONMENTS"(
    "CAMPSITE_ID" BIGINT NOT NULL,
    "ENVIRONMENTS" CHARACTER VARYING(255)
);

CREATE CACHED TABLE "PUBLIC"."THEMES"(
    "CAMPSITE_ID" BIGINT NOT NULL,
    "THEMES" CHARACTER VARYING(255)
);

CREATE CACHED TABLE "PUBLIC"."RESERVATION"(
    "RESERVATION_ID" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1 RESTART WITH 4) NOT NULL,
    "CAMPSITE_ID" BIGINT NOT NULL,
    "MEMBER_NO" BIGINT NOT NULL,
    "RESERVATION_DATE" TIMESTAMP NOT NULL,
    "CHECKIN_DATE" CHARACTER VARYING(255) NOT NULL,
    "CHECKOUT_DATE" CHARACTER VARYING(255) NOT NULL,
    "HEAD_COUNT" INTEGER NOT NULL,
    "TOTAL_PRICE" INTEGER NOT NULL,
    "RESERVATION_STATUS" CHARACTER VARYING(255)
);

CREATE CACHED TABLE "PUBLIC"."REVIEW"(
    "REVIEW_ID" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1 RESTART WITH 4) NOT NULL,
    "MEMBER_NO" BIGINT NOT NULL,
    "REVIEW_CAMPSITE" CHARACTER VARYING(255) NOT NULL,
    "REVIEW_TITLE" CHARACTER VARYING(255) NOT NULL,
    "REVIEW_CONTENT" CHARACTER VARYING(1000) NOT NULL,
    "REVIEW_WRITER" CHARACTER VARYING(255) NOT NULL,
    "REVIEW_VIEWS" INTEGER DEFAULT '0' NOT NULL
);

CREATE CACHED TABLE "PUBLIC"."REVIEW_IMG"(
    "REVIEW_IMG_ID" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1 RESTART WITH 10) NOT NULL,
    "REVIEW_ID" BIGINT NOT NULL,
    "REVIEW_IMG_ORIGINAL" CHARACTER VARYING(255) NOT NULL,
    "REVIEW_IMG_NAME" CHARACTER VARYING(255) NOT NULL,
    "REVIEW_IMG_PATH" CHARACTER VARYING(255) NOT NULL
);