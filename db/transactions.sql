create table books(
    id serial primary key,
    name varchar(255),
    cost int
);

insert into books(name, cost) values('book1', 100);
insert into books(name, cost) values('book2', 200);
insert into books(name, cost) values('book3', 300);

