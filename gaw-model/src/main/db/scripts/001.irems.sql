--liquibase formatted sql

--changeset iremstech:1
CREATE TABLE MESSAGE_TEMPLATE
(
  id uuid DEFAULT uuid_generate_v4(),
  name VARCHAR(12) NOT NULL,
  template TEXT,
  rank INTEGER,
  PRIMARY KEY (id)
);

--rollback DROP TABLE IF EXISTS MESSAGE_TEMPLATE;
