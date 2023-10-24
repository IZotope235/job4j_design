insert into roles(name) values ('buyer');
insert into roles(name) values ('customer');

insert into rules(name) values ('vip');
insert into rules(name) values ('regular');
insert into rules(name) values ('beginner');

insert into rules_roles(rule_id, role_id) values (1, 1);
insert into rules_roles(rule_id, role_id) values (3, 2);

insert into users(name, role_id) values ('Ivan', 1);
insert into users(name, role_id) values ('Peter', 2);

insert into states(name) values ('accepted');
insert into states(name) values ('in process');
insert into states(name) values ('done');

insert into categories(name) values ('toys');
insert into categories(name) values ('electronics');
insert into categories(name) values ('smartphone');

insert into items(item, user_id, category_id, state_id) values ('lego-34553', 1, 1, 2);
insert into items(item, user_id, category_id, state_id) values ('iPhone', 1, 3, 1);
insert into items(item, user_id, category_id, state_id) values ('sony-345345', 2, 2, 3);

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









