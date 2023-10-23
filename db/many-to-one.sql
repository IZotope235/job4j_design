create table userType(
id serial primary key,
userType varchar(255)
);

create table users(
id serial primary key,
userName varchar(255),
userType_id int references userType(id)
);

insert into userType(usertype) values ('admin');
insert into users(username, usertype_id) values ('Ivan', 1);

insert into userType(usertype) values ('user');
insert into users(username, usertype_id) values ('John', 2);
