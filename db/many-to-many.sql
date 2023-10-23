create table pupil(
    id serial primary key,
    name varchar(255)
);

create table subject(
    id serial primary key,
    subject varchar(255)
);

create table pupil_subject(
    id serial primary key,
    pupil_id int references pupil(id),
    subject_id int references subject(id)
);

insert into pupil(name) values ('Ivan');
insert into pupil(name) values ('Peter');
insert into pupil(name) values ('John');

insert into subject(subject) values ('Math');
insert into subject(subject) values ('Geography');
insert into subject(subject) values ('Physics');
insert into subject(subject) values ('Chemistry');

insert into pupil_subject(pupil_id, subject_id) values (1, 1);
insert into pupil_subject(pupil_id, subject_id) values (1, 2);
insert into pupil_subject(pupil_id, subject_id) values (2, 1);
insert into pupil_subject(pupil_id, subject_id) values (2, 2);
insert into pupil_subject(pupil_id, subject_id) values (2, 3);
insert into pupil_subject(pupil_id, subject_id) values (3, 3);
insert into pupil_subject(pupil_id, subject_id) values (3, 4);
