insert into fauna(name, avg_age, discovery_date) values('dog', 15, '15.12.0513');
insert into fauna(name, avg_age, discovery_date) values('cat', 10, '15.12.0202');
insert into fauna(name, avg_age, discovery_date) values('fish', 3, '15.12.0101');
insert into fauna(name, avg_age, discovery_date) values('elephant', 50, '15.12.0200');
insert into fauna(name, avg_age, discovery_date) values('chiken', 15, '15.12.0211');
insert into fauna(name, avg_age, discovery_date) values('mosquito', 0, '15.12.1290');
insert into fauna(name, avg_age, discovery_date) values('lion', 10, '15.12.0025');
insert into fauna(name, avg_age, discovery_date) values('monkey', 30, '15.12.0702');
insert into fauna(name, avg_age, discovery_date) values('panda', 45, '15.12.1000');
insert into fauna(name, avg_age, discovery_date) values('tiger', 33, '15.12.1003');
insert into fauna(name, avg_age) values('turtle', 10111);
insert into fauna(name, avg_age) values('dinosaur', 21111);
insert into fauna(name, avg_age, discovery_date) values('zebra', 21000, '01.01.1905');

select * from fauna where name like '%fish%';
select * from fauna where avg_age>=10000 and avg_age<=21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date<'01.01.1905';