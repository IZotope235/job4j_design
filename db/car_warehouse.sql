create table car_bodies(
    id serial primary key,
    name varchar(255)
);

create table car_engines(
    id serial primary key,
    name varchar(255)
);

create table car_transmissions(
    id serial primary key,
    name varchar(255)
);

create table cars(
    id serial primary key,
    name varchar(255),
    body_id int references car_bodies(id),
    engine_id int references car_engines(id),
    transmiission_id int references car_transmissions(id)
);

insert into car_bodies(name) values('Coupe');
insert into car_bodies(name) values('Sedan');
insert into car_bodies(name) values('Hatchback');
insert into car_bodies(name) values('Van');
insert into car_bodies(name) values('Crossover');
insert into car_bodies(name) values('Pickup');

insert into car_engines(name) values ('1.4');
insert into car_engines(name) values ('1.8');
insert into car_engines(name) values('V6 3.0');
insert into car_engines(name) values('V8 4.0');

insert into car_transmissions(name) values ('AT');
insert into car_transmissions(name) values ('CVT');
insert into car_transmissions(name) values ('MT');

insert into cars(name, body_id, engine_id, transmiission_id) values ('Prius', 1, 1, 1);
insert into cars(name, body_id, engine_id, transmiission_id) values ('Prius', 2, 2, 1);
insert into cars(name, body_id, engine_id, transmiission_id) values ('Prius', 2, 2, 2);
insert into cars(name, body_id, engine_id, transmiission_id) values ('Corolla', 2, 3, 1);
insert into cars(name, body_id, engine_id, transmiission_id) values ('Corolla', 2, 3, 2);
insert into cars(name, body_id, engine_id, transmiission_id) values ('Alphard', 4, 2, 1);
insert into cars(name, body_id, engine_id, transmiission_id) values ('Alphard', 4, 3, 2);
insert into cars(name, body_id, engine_id, transmiission_id) values ('RAV-4', 5, 1, 1);
insert into cars(name, body_id, engine_id, transmiission_id) values ('RAV-4', 5, 2, 1);
insert into cars(name, body_id, engine_id, transmiission_id) values ('RAV-4', 5, 2, 2);
insert into cars(name, engine_id, transmiission_id) values ('RAV-4', 2, 2);
insert into cars(name, body_id, transmiission_id) values ('RAV-4', 5, 2);
insert into cars(name, body_id, engine_id) values ('RAV-4', 5, 2);

--Вывести список всех машин и все привязанные к ним детали.
select c.id as "ID",
c.name as "Model",
cb.name as "Body",
ce.name as "Engine",
ct.name as "Transmission"
from cars as c
left join car_bodies as cb
on c.body_id = cb.id
left join car_engines as ce
on c.engine_id = ce.id
left join car_transmissions as ct
on c.transmiission_id = ct.id;

--Вывести кузова, которые не используются НИ в одной машине.
select c.id as "ID",
c.name as "Model",
cb.name as "Body",
ce.name as "Engine",
ct.name as "Transmission"
from cars as c
left join car_engines as ce
on c.engine_id = ce.id
left join car_transmissions as ct
on c.transmiission_id = ct.id
right join car_bodies as cb
on c.body_id = cb.id
where c.body_id is null;

--Вывести двигатели, которые не используются НИ в одной машине
select c.id as "ID",
c.name as "Model",
cb.name as "Body",
ce.name as "Engine",
ct.name as "Transmission"
from cars as c
left join car_bodies as cb
on c.body_id = cb.id
left join car_transmissions as ct
on c.transmiission_id = ct.id
right join car_engines as ce
on c.engine_id = ce.id
where c.engine_id is null;

--Вывести коробки передач, которые не используются НИ в одной машине
select c.id as "ID",
c.name as "Model",
cb.name as "Body",
ce.name as "Engine",
ct.name as "Transmission"
from cars as c
left join car_bodies as cb
on c.body_id = cb.id
left join car_engines as ce
on c.engine_id = ce.id
right join car_transmissions as ct
on c.transmiission_id = ct.id
where c.transmiission_id is null;