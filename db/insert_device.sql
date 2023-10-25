insert into devices(name, price) values ('iPhone', 49900.99);
insert into devices(name, price) values ('Sony-TV345', 110000.78);
insert into devices(name, price) values ('Oneplus 12', 56600.99);
insert into devices(name, price) values ('Xiaomi 14', 6550.99);

insert into people(name) values ('Ivan');
insert into people(name) values ('Peter');
insert into people(name) values ('John');
insert into people(name) values ('Kevin');

insert into devices_people(device_id, people_id) values (1, 1);
insert into devices_people(device_id, people_id) values (1, 2);
insert into devices_people(device_id, people_id) values (2, 2);
insert into devices_people(device_id, people_id) values (3, 2);
insert into devices_people(device_id, people_id) values (3, 3);
insert into devices_people(device_id, people_id) values (1, 4);
insert into devices_people(device_id, people_id) values (4, 4);

select avg(price) from devices;

select p.name as "Покупатель",
avg(d.price) as "Средняя цена"
from devices_people as dp
join devices as d
on dp.device_id = d.id
join people as p
on dp.people_id = p.id
group by p.name
order by avg(d.price) asc;

select p.name as "Покупатель",
avg(d.price) as "Средняя цена"
from devices_people as dp
join devices as d
on dp.device_id = d.id
join people as p
on dp.people_id = p.id
group by p.name
having avg(d.price)>5000
order by avg(d.price) asc;