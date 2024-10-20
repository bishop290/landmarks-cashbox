--liquibase formatted sql

--changeset Grigorii_Kuznetsov:1
create schema if not exists orders;

--changeset Grigorii_Kuznetsov:2
create table orders.orders (
    id bigserial primary key,
    customer varchar(100) not null,
    visitors Integer not null,
    amount bigserial,
    landscape varchar(100) not null,
    attraction varchar(100) not null,
    status varchar(100) not null);


--changeset Grigorii_Kuznetsov:3
insert into orders.orders(customer, visitors, amount, landscape, attraction, status)
values
('Customer-1', 2, 5000, 'Landscape-1', 'CASTLE', 'NEW'),
('Customer-2', 1, 7000, 'Landscape-2', 'CASTLE', 'NEW'),
('Customer-3', 1, 8000, 'Landscape-3', 'CASTLE', 'NEW'),
('Customer-4', 1, 9000, 'Landscape-4', 'CASTLE', 'NEW'),
('Customer-5', 1, 4000, 'Landscape-5', 'CASTLE', 'NEW'),
('Customer-6', 1, 3000, 'Landscape-6', 'CASTLE', 'NEW');
