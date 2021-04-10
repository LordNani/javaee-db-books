create table users
(
    id       int primary key auto_increment,
    login    varchar(30) not null,
    password varchar(40) not null,

    unique uniq_login (login)
);


create table permissions
(
    id         int primary key auto_increment,
    permission varchar(30) not null,
    unique uniq_permission (permission)
);

create table books
(
    id       int primary key auto_increment,
    isbn    varchar(255) not null,
    title varchar(255) not null,
    author varchar(255) not null
);

create table user_books
(
    id       int primary key auto_increment

);

create table user_to_permissions (
    user_id int not null,
    permission_id int not null,
    constraint fk_user_to_permission_user foreign key (user_id) references users(id),
    constraint fk_user_to_permission_permission foreign key (permission_id) references permissions(id)
);

insert into users (login, password) values
    ('admin', 'password'),
    ('user', 'password');

insert into permissions (permission) values
    ('ADMIN'),
    ('USER');

insert into user_to_permissions (user_id, permission_id) values
    ((select id from users where login = 'admin'), (select id from permissions where permission = 'ADMIN')),
    ((select id from users where login = 'admin'), (select id from permissions where permission = 'USER')),

    ((select id from users where login = 'user'), (select id from permissions where permission = 'USER'));