create database stoss;

create table role
(
    ID   bigint auto_increment primary key,
    NAME varchar(45) null
)
    charset = utf8;

create table user
(
    id       bigint auto_increment primary key,
    login    varchar(45) null unique,
    password varchar(45) null,
    name     varchar(45) null,
    points   bigint      null default 0,
    balance  decimal(9,2) null default 0,
    userpic varchar(255) null default 'static/img/png/userpic.png',
    locale varchar(2) null default 'en',
    ROLE_ID  bigint      null default 2,
    constraint user_role_1
        foreign key (ROLE_ID) references role (ID)
)
    charset = utf8;

create index ROLE_ID
    on user (ROLE_ID);

create table news
(
    id bigint auto_increment primary key,
    caption varchar(45) null,
    text varchar(255) null,
    time datetime
)
charset = utf8;

