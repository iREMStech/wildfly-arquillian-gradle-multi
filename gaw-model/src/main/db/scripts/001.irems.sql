--liquibase formatted sql

--changeset iremstech:1
CREATE TABLE MESSAGE_TEMPLATE
(
  ID UUID DEFAULT uuid_generate_v4(),
  NAME VARCHAR(12) NOT NULL,
  TEMPLATE TEXT,
  RANK INTEGER,
  PRIMARY KEY (ID)
);

--rollback DROP TABLE IF EXISTS MESSAGE_TEMPLATE;
