create table departments(
    id serial primary key,
    name varchar(255)
);

create table employees(
    id serial primary key,
    name varchar(255),
    department_id int references departments(id)
);

insert into departments(name) values ('Administration');
insert into departments(name) values ('Accounts');
insert into departments(name) values ('IT');
insert into departments(name) values ('HR');
insert into departments(name) values ('Seller');
insert into departments(name) values ('Delivery');

insert into employees(name, department_id) values ('Иванов И.И.', 1);
insert into employees(name, department_id) values ('Сидоров С.С.', 2);
insert into employees(name, department_id) values ('Гришин Г.Г.', 2);
insert into employees(name, department_id) values ('Андреев А.А.', 3);
insert into employees(name, department_id) values ('Борисов Б.Б.', 3);
insert into employees(name, department_id) values ('Владимиров В.В.', 3);
insert into employees(name, department_id) values ('Михайлова М.М.', 4);
insert into employees(name, department_id) values ('Николаев Н.Н.', 5);
insert into employees(name, department_id) values ('Петров П.П.', 5);
insert into employees(name) values ('Дмитриев Д.Д.');


-- Выполнить запросы с left, right, full, cross соединениями
select e.name as "Name", d.name as "Department"
from employees as e
left join departments as d
on department_id = d.id;

select e.name as "Name", d.name as "Department"
from employees as e
right join departments as d
on department_id = d.id;

select e.name as "Name", d.name as "Department"
from employees as e
full join departments as d
on department_id = d.id;

select e.name as "Name", d.name as "Department"
from employees as e
cross join departments as d;

-- Используя left join найти департаменты, у которых нет работников
select d.name as "Department",
e.name as "Name"
from departments as d
left join employees as e
on department_id = d.id
where department_id is null;

-- Используя left и right join написать запросы, которые давали бы одинаковый результат
select e.name as "Name", d.name as "Department"
from employees as e
left join departments as d
on department_id = d.id;

select e.name as "Name", d.name as "Department"
from departments as d
right join employees as e
on department_id = d.id;

select e.name as "Name", d.name as "Department"
from employees as e
right join departments as d
on department_id = d.id;

select e.name as "Name", d.name as "Department"
from departments as d
left join employees as e
on department_id = d.id;

/*
    Создать таблицу teens с атрибутами name, gender и заполнить ее.
    Используя cross join составить все возможные разнополые пары
*/

create table teens(
    id serial primary key,
    name varchar(255),
    gender varchar(1)
);

insert into teens(name, gender) values ('Ivan', 'M');
insert into teens(name, gender) values ('Adam', 'M');
insert into teens(name, gender) values ('Eva', 'F');
insert into teens(name, gender) values ('Maria', 'F');
insert into teens(name, gender) values ('John', 'M');
insert into teens(name, gender) values ('Kate', 'F');
insert into teens(name, gender) values ('Alex', 'M');
insert into teens(name, gender) values ('Alexandra', 'F');

select  t1.name,
t2.name
from teens t1
cross join teens t2
where t1.gender like 'M' and t2.gender like 'F';