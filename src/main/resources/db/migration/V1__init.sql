
create table  IF NOT EXISTS  workouts
(
    id          bigserial primary key,
    url         varchar(255) not null,
    author      varchar(50)  not null,
    description varchar(500) not null
);

insert into workouts (url, author, description)
values ('https://www.youtube.com/embed/W_MCaFq7llw', 'Nikita', 'first example'),
       ('https://www.youtube.com/embed/WsWgV_6TYlQ', 'Nikita', '2nd example'),
       ('https://www.youtube.com/embed/d4BzD8jjWK4', 'Nikita', '3d example'),
       ('https://www.youtube.com/embed/9-zA_L00T_0', 'Nikita', '4th example'),
       ('https://www.youtube.com/embed/iH8MiV6mf4U', 'Nikita', '5th example'),
       ('https://www.youtube.com/embed/V1TFHW_L4TM', 'Nikita', '6th example');

create table IF NOT EXISTS users
(
    id                  bigserial primary key,
    username            varchar(30) not null,
    password            varchar(80) not null,
    email               varchar(50) unique,
    created_at          timestamp default current_timestamp,
    updated_at          timestamp default current_timestamp
);

create table IF NOT EXISTS roles (
    id                  bigserial primary key,
    name                varchar(50) not null,
    created_at          timestamp default current_timestamp,
    updated_at          timestamp default current_timestamp
);

create table users_roles (
    user_id             bigint not null references users(id),
    role_id            bigint not null references roles(id), primary key (user_id, role_id)
);

INSERT into roles(name) values
('ROLE_ADMIN'),
('ROLE_USER');

INSERT into users(username, password, email)  values
('first_admin', '$2a$12$UqwaHsTfonjfS0PT5mjqUOLIFJIvXQxS6YOEhx3djC.Ty1RfHD.mO', 'someadmin1@kukusmail.org'),
('first_user', '$2a$12$UqwaHsTfonjfS0PT5mjqUOLIFJIvXQxS6YOEhx3djC.Ty1RfHD.mO', 'some2@mail.com');


INSERT into users_roles (user_id, role_id) values
(1,1),
(2,2);