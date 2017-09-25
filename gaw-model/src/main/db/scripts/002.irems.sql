--liquibase formatted sql

--changeset iremstech:2
ALTER TABLE MESSAGE_TEMPLATE ADD UNIQUE (NAME);
--rollback;
