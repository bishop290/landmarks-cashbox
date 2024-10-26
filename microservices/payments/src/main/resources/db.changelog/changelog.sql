--liquibase formatted sql

--changeset Grigorii_Kuznetsov:1
create table payments (
    id bigserial primary key,
    price bigint,
    order_id bigint,
    cancel boolean not null);


--changeset Grigorii_Kuznetsov:2
insert into payments(price, order_id, cancel)
values
(300, 0, TRUE),
(600, 0, TRUE),
(800, 0, TRUE),
(200, 0, TRUE);
