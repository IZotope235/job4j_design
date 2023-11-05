CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

CREATE TABLE orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);

insert into customers(first_name, last_name, age, country) values
('John', 'Smith', 18, 'USA'),
('Kahl', 'Sagan', 18, 'Litva'),
('Mark', 'Twen', 54, 'USA'),
('Boris', 'Kuk', 32, 'Russia'),
('Nik', 'Frost', 44, 'Canada'),
('Pavel', 'Ivanov', 25, 'Kazakhstan'),
('Daniil', 'Gashek', 38, 'Cezch Republic'),
('Irina', 'Ivanova', 27, 'Russia'),
('Ivan', 'Ivanov', 30, 'Russia'),
('Peter', 'Petrov', 40, 'Russia');

insert into orders(amount, customer_id) values
(100, 1), (200, 2), (300, 3), (400, 4), (500, 5),
(600, 6), (700, 7), (10, 1), (20, 2), (30, 3),
(40, 4), (50, 5), (60, 6), (70, 7);

/*
Выполните запрос, который вернет список клиентов,
возраст которых является минимальным.
*/
select first_name, last_name, age, country from customers as c
where age = (select min(age) from customers);

/*
Выполнить запрос, который вернет список пользователей,
которые еще не выполнили ни одного заказа.
*/
select first_name, last_name, age, country from customers as c
where c.id not in (select customer_id from orders);




