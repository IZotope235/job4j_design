create table students (
    id serial primary key,
    name varchar(50)
);

insert into students (name) values ('Иван Иванов');
insert into students (name) values ('Петр Петров');

create table authors (
    id serial primary key,
    name varchar(50)
);

insert into authors (name) values ('Александр Пушкин');
insert into authors (name) values ('Николай Гоголь');

create table books (
    id serial primary key,
    name varchar(200),
    author_id integer references authors(id)
);

insert into books (name, author_id) values ('Евгений Онегин', 1);
insert into books (name, author_id) values ('Капитанская дочка', 1);
insert into books (name, author_id) values ('Дубровский', 1);
insert into books (name, author_id) values ('Мертвые души', 2);
insert into books (name, author_id) values ('Вий', 2);

create table orders (
    id serial primary key,
    active boolean default true,
    book_id integer references books(id),
    student_id integer references students(id)
);

insert into orders (book_id, student_id) values (1, 1);
insert into orders (book_id, student_id) values (1, 1);
insert into orders (book_id, student_id) values (3, 1);
insert into orders (book_id, student_id) values (5, 2);
insert into orders (book_id, student_id) values (4, 1);
insert into orders (book_id, student_id) values (2, 2);
insert into orders (book_id, student_id) values (2, 2);
insert into orders (book_id, student_id) values (1, 2);
insert into orders (active, book_id, student_id) values (false, 2, 1);



create view show_stutents_with_more_then_one_same_book
as select s.name as "Student",
b.name as "Book",
a.name as "Author",
count(o.book_id) as "Count",
case o.active
when true then 'Active'
when false then 'Closed'
end as "Status"
from books as b
left join authors as a
on b.author_id = a.id
 join orders as o
on o.book_id = b.id
left join students as s
on o.student_id = s.id
group by (b.name, a.name, o.active, s.name)
having o.active = true and count(o.book_id) > 1
order by s.name asc;