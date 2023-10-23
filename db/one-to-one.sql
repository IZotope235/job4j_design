create table client(
    id serial primary key,
    name varchar(255)
);

create table insurance(
    id serial primary key,
    insuranceNumber int
);

create table client_insurance(
    id serial primary key,
    client_id int references client(id) unique,
    insurance_id int references insurance(id) unique
);

insert into client(name) values ('Alex');
insert into insurance(insuranceNumber) values (87645345);
insert into client_insurance(client_id, insurance_id) values (1, 1);

insert into client(name) values ('John');
insert into insurance(insuranceNumber) values (345345345);
insert into client_insurance(client_id, insurance_id) values (2, 2);

