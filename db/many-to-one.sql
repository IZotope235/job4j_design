create table userType(
    id serial primary key,
    userType varchar(255)
);

create table users(
    id serial primary key,
    username varchar(255),
    userType_id int references userType(id)
);

insert into userType(userType) values ('admin');
insert into userType(userType) values ('user');
insert into userType(userType) values ('guest');

insert into users(username, usertype_id) values ('Ivan', 1);
insert into users(username, usertype_id) values ('Peter', 2);
insert into users(username, usertype_id) values ('John', 2);
insert into users(username, usertype_id) values ('Kevin', 3);

select u.username as "Имя пользователя", ut.usertype as "Тип пользователя"
from users as u join usertype as ut on u.usertype_id = ut.id