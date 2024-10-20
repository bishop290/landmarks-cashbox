--liquibase formatted sql

--changeset Grigorii_Kuznetsov:1
create table payments (
    id bigserial primary key,
    amount bigserial not null);


--changeset Grigorii_Kuznetsov:2
insert into payments(amount)
values
(300),
(600),
(800),
(200),
(100),
(700);
