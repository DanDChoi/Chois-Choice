--postgresql
-- CREATE TABLE mbr
-- (
--     mbr_id SERIAL PRIMARY KEY,
--     mbr_nm VARCHAR(30);
--     mbr_sex VARCHAR(10);
--     mbr_email VARCHAR(100) NOT NULL;
--     mbr_pwd VARCHAR(50);
--     mobil_no VARCHAR(50);
--     mbr_post VARCHAR(30);
--     mbr_base_addr VARCHAR(50);
--     mbr_detail_addr VARCHAR(50);
--     mbr_brthdy VARCHAR(30);
--     join_date TIMESTAMP NOT NULL;
--     mbr_stat_cd VARCHAR(30);
-- );

CREATE TABLE mbr
(
    mbr_no          int(10) NOT NULL PRIMARY KEY,
    mbr_id          VARCHAR(10),
    mbr_nm          VARCHAR(30),
    mbr_sex         VARCHAR(10),
    mbr_email       VARCHAR(100) NOT NULL,
    mbr_pwd         VARCHAR(50),
    mobil_no        VARCHAR(50),
    mbr_post        VARCHAR(30),
    mbr_base_addr   VARCHAR(50),
    mbr_detail_addr VARCHAR(50),
    mbr_brthdy      VARCHAR(30),
    join_date       DATETIME DEFAULT NOW(),
    mbr_stat_cd     VARCHAR(30)
);