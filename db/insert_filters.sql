create table type(
    id serial primary key,
    name varchar(255)
);

create table product(
    id serial primary key,
    name varchar(255),
    type_id int references type(id),
    expired_date date,
    price decimal(10, 2)
);

insert into type(name) values('КОЛБАСА');
insert into type(name) values('СЫР');
insert into type(name) values('МОРОЖЕННОЕ');
insert into type(name) values('МОЛОКО');
insert into type(name) values('КОФЕ');
insert into type(name) values('ЧАЙ');

insert into product(name, type_id, expired_date, price) values('Natura', 2, '04.04.2024', 330.0);
insert into product(name, type_id, expired_date, price) values('Cыр плавленный', 2, '04.05.2024', 100.0);
insert into product(name, type_id, expired_date, price) values('Сыр моцарелла', 2, '04.04.2023', 330.0);
insert into product(name, type_id, expired_date, price) values('Колбаса сырокопченная', 1, '04.12.2023', 550.0);
insert into product(name, type_id, expired_date, price) values('Колбаса варенная', 1, '04.01.2024', 455.0);
insert into product(name, type_id, expired_date, price) values('Мороженное ванильное', 3, '04.05.2024', 120.0);
insert into product(name, type_id, expired_date, price) values('Мороженное шоколадное', 3, '04.03.2024', 130.0);
insert into product(name, type_id, expired_date, price) values('Мороженное эскимо', 3, '04.03.2024', 250.5);
insert into product(name, type_id, expired_date, price) values('Молоко Простоквашино', 4, '04.11.2023', 110.6);
insert into product(name, type_id, expired_date, price) values('Молоко Домик в деревне', 4, '04.12.2023', 120.6);
insert into product(name, type_id, expired_date, price) values('Кофе молотый', 5, '04.11.2024', 400.99);
insert into product(name, type_id, expired_date, price) values('Кофе в зернах', 5, '04.12.2024', 450.99);
insert into product(name, type_id, expired_date, price) values('Чай черный', 6, '04.12.2024', 260.99);
insert into product(name, type_id, expired_date, price) values('Чай зеленый', 6, '04.12.2025', 333.99);
insert into product(name, type_id, expired_date, price) values('Ветчина', 1, '04.06.2023', 550.0);


-- Запрос получение всех продуктов с типом "СЫР"
select p.name as "Наименование",
t.name as "Категория",
p.price as "Цена",
to_char(p.expired_date, 'dd.mm.yyyy') as "Срок годности"
from product as p
join type as t
on p.type_id = t.id
where lower(t.name) like 'сыр'
order by p.expired_date asc;

-- Запрос получения всех продуктов, у кого в имени есть слово "мороженое"
select p.name as "Наименование",
t.name as "Категория",
p.price as "Цена",
to_char(p.expired_date, 'dd.mm.yyyy') as "Срок годности"
from product as p
join type as t
on p.type_id = t.id
where lower(p.name) like '%мороженное%'
order by p.expired_date asc;

-- Запрос, который выводит все продукты, срок годности которых уже истек
select p.name as "Наименование",
t.name as "Категория",
p.price as "Цена",
to_char(p.expired_date, 'yyyy.mm.dd') as "Срок годности"
from product as p
join type as t
on p.type_id = t.id
where p.expired_date<now();
order by p.expired_date asc;

-- Запрос, который выводит самый дорогой продукт
select p.name as "Самый дорогое наименование",
t.name as "Категория",
p.price as "Цена",
to_char(p.expired_date, 'yyyy.mm.dd') as "Срок годности"
from product as p
join type as t
on p.type_id = t.id
where p.price = (select all max(product.price) from product);

-- Запрос, который выводит для каждого типа количество продуктов к нему принадлежащих
select t.name as "Категория",
count(p.type_id) as "Колличество"
from product as p
join type as t
on p.type_id = t.id
group by t.name;

-- Запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select p.name as "Наименование",
t.name as "Категория",
p.price as "Цена",
to_char(p.expired_date, 'dd.mm.yyyy') as "Срок годности"
from product as p
join type as t
on p.type_id = t.id
where lower(t.name) like 'сыр'
or lower(t.name) like 'молоко'
order by p.expired_date asc;

-- Запрос, который выводит тип продуктов, которых осталось меньше 10 штук
select t.name as "Категория",
count(p.type_id) as "Колличество"
from product as p
join type as t
on p.type_id = t.id
group by t.name
having count(p.type_id) < 10;

-- Вывести все продукты и их тип.
select p.name as "Наименование",
t.name as "Категория"
from product as p
join type as t
on p.type_id = t.id
order by t.name asc;