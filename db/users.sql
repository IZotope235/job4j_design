
create table users(
    id serial primary key,
    name varchar(255),
    rate int,
    lastActivity date);

insert into users(name, rate, lastActivity) values('Ivan Ivanov', 5000, '2023-06-30');

update users set rate = 7000;

delete from users;
