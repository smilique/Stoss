create database stoss;

create table role
(
    ID   bigint auto_increment
        primary key,
    NAME varchar(45) null
)
    charset = utf8;

create table user
(
    id       bigint auto_increment
        primary key,
    login    varchar(45) unique,
    password varchar(45) null,
    name     varchar(45) null,
    points   bigint      null,
    balance  bigint      null,
    ROLE_ID  bigint      null,
    constraint user_role_1
        foreign key (ROLE_ID) references role (ID)
)
    charset = utf8;

create index ROLE_ID
    on user (ROLE_ID);

