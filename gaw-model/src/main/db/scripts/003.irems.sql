--liquibase formatted sql

--changeset iremstech:3
CREATE TABLE GUEST
(
  ID UUID DEFAULT uuid_generate_v4(),
  NAME VARCHAR(100) NOT NULL,
  ARRIVAL_DATE DATE,
  PRIMARY KEY (ID)
);
--rollback DROP TABLE GUEST;
