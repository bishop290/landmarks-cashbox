--liquibase formatted sql

--changeset Grigorii_Kuznetsov:1
create table tickets (
    id bigserial primary key,
    order_id bigint);


--changeset Grigorii_Kuznetsov:2
insert into tickets(order_id)
values
(1),
(2);
