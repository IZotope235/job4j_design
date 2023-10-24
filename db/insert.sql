insert into users(name) values ('Ivan');
insert into users(name) values ('Peter');

insert into items(item, user_id) values ('lego-34553', 1);
insert into items(item, user_id) values ('iPhone', 1);
insert into items(item, user_id) values ('sony-345345', 2);

insert into states(name, item_id) values ('accepted', 1);
insert into states(name, item_id) values ('in process', 2);
insert into states(name, item_id) values ('done', 3);

insert into categories(name, item_id) values ('toys', 1);
insert into categories(name, item_id) values ('constructor', 1);
insert into categories(name, item_id) values ('electronics', 2);
insert into categories(name, item_id) values ('smartphone', 2);
insert into categories(name, item_id) values ('electronics', 3);

insert into attachs(name, item_id) values ('lego-view-1.jpg', 1);
insert into attachs(name, item_id) values ('lego-view-2.jpg', 1);
insert into attachs(name, item_id) values ('iphone-view-1.jpg', 2);
insert into attachs(name, item_id) values ('iphone-view-2.jpg', 2);
insert into attachs(name, item_id) values ('sony-view-1.jpg', 3);
insert into attachs(name, item_id) values ('sony-view-2.jpg', 3);

insert into comments(comment, item_id) values ('OK', 1);
insert into comments(comment, item_id) values ('Super item', 2);
insert into comments(comment, item_id) values ('I love it', 2);
insert into comments(comment, item_id) values ('I hate it', 3);

insert into roles(name, user_id) values ('buyer', 1);
insert into roles(name, user_id) values ('customer', 2);

insert into rules(name) values ('vip');
insert into rules(name) values ('regular');
insert into rules(name) values ('beginner');

insert into rules_roles(rule_id, role_id) values (1, 1);
insert into rules_roles(rule_id, role_id) values (3, 2);







