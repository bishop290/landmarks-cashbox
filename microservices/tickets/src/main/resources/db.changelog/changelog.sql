--liquibase formatted sql

--changeset Grigorii_Kuznetsov:1
create schema if not exists tickets;

--changeset Grigorii_Kuznetsov:2
create table tickets.tickets (
    id bigserial primary key,
    landscape varchar(100) not null,
    amount bigserial not null);


--changeset Grigorii_Kuznetsov:3
insert into tickets.tickets(landscape, amount)
values
('Landscape-1', 300),
('Landscape-2', 600),
('Landscape-3', 800),
('Landscape-4', 200),
('Landscape-5', 100),
('Landscape-6', 700);
