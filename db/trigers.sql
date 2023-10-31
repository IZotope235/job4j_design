create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);

/*
Триггер должен срабатывать после вставки данных,
для любого товара и просто насчитывать налог на товар
*/

create or replace function tax_add()
	returns trigger as
$$
	BEGIN
		update products
		set price = price * 1.2
		where id = (select id from inserted);
		return new;
	END;
$$
LANGUAGE 'plpgsql';

create trigger tax_add_trigger
	after insert on products
	referencing new table as inserted
	for each statement
	execute procedure tax_add();

/*
Триггер должен срабатывать до вставки данных
и насчитывать налог на товар
*/

create or replace function tax_add_each_row()
	returns trigger as
$$
	BEGIN
		new.price = new.price * 1.2;
		return new;
	END;
$$
LANGUAGE 'plpgsql';


create trigger tax_add_each_row_trigger
	before insert on products
	for each row
	execute procedure tax_add_each_row();

/*
 Нужно написать триггер на row уровне,
 который сразу после вставки продукта в таблицу products,
 будет заносить имя, цену и текущую дату в таблицу history_of_price.
 */

create or replace function add_in_history()
	returns trigger as
$$
	BEGIN
		insert into history_of_price(name, price, date) values (new.name, new.price, current_timestamp);
		return new;
	END;
$$
LANGUAGE 'plpgsql';

create trigger add_in_history_trigger
	after insert on products
	for each row
	execute procedure add_in_history();