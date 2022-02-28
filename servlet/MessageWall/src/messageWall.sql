
drop database if exists message_wall;
create database message_wall;

use message_wall;
create table Information (
    `from` varchar(20),
    `to` varchar(20),
    `message`  varchar(1024)
);