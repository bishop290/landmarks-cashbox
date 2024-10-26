--liquibase formatted sql

--changeset Grigorii_Kuznetsov:1
create table orders (
    id bigserial primary key,
    number_of_visitors bigint,
    price bigint,
    status varchar(100) not null,
    ticket bigint,
    cash_receipt bigint);


--changeset Grigorii_Kuznetsov:2
insert into orders(number_of_visitors, price, status, ticket, cash_receipt)
values
(2, 300, 'RESERVED', 0, 0),
(1, 400, 'RESERVED', 0, 0),
(3, 400, 'RESERVED', 0, 0),
(1, 200, 'RESERVED', 0, 0);
